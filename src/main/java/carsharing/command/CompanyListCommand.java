package carsharing.command;

import carsharing.*;
import carsharing.dao.CompanyDao;
import carsharing.dao.CompanyDaoImpl;
import carsharing.menu.Menu;
import carsharing.menu.MenuItem;
import carsharing.menu.MenuList;

import java.util.ArrayList;
import java.util.List;


import static carsharing.Main.dbService;

public class CompanyListCommand implements Command {
    @Override
    public void execute() {

        CompanyDao companyDao = new CompanyDaoImpl(dbService.getConn());
        List<Company> companies = companyDao.getAllCompanies();
        if (companies.size() == 0) {
            ConsoleHandler.write("\nThe company list is empty!\n");
        } else {
            ConsoleHandler.write("Choose the company:\n");
            Operation operation = null;
            Object object;
            Table table = null;
            do {
                MenuList<Company> menuList = new MenuList<>(new ArrayList<>(companies), Operation.CAR_LIST,
                        new ArrayList<>(List.of(new MenuItem(Operation.EXIT, 0))), "exit");
                object = ConsoleHandler.askObjectOfMenuList(menuList);

                if (object instanceof Operation) {
                    operation = (Operation) object;
                    CommandExecutor.execute(operation);
                } else {
                    CarMenu((Company) object);
                    operation = Operation.EXIT;
                }

                table = null;
            }
            while (operation != Operation.EXIT);


        }


    }

    private void CarMenu(Company company) {
        Operation operation;
        do {

            ConsoleHandler.write("'" + company.getName() + "' company");
            Menu menu = new Menu(new ArrayList<>(List.of(new MenuItem(Operation.CAR_LIST, 1),
                    new MenuItem(Operation.CAR_CREATE, 2), new MenuItem(Operation.EXIT, 0))),
                    "car");
            operation = ConsoleHandler.askOperation(menu);
            if (operation != Operation.EXIT) {
                CommandExecutor.execute(operation, company);
            } else {
                CommandExecutor.execute(operation);
            }
        }
        while (operation != Operation.EXIT);
    }

    @Override
    public void setT(Table table) {

    }
}
