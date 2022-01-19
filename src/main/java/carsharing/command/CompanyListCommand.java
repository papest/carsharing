package carsharing.command;

import carsharing.*;
import carsharing.dao.CompanyDao;
import carsharing.dao.CompanyDaoImpl;
import carsharing.menu.MenuItem;
import carsharing.menu.MenuList;

import java.util.ArrayList;
import java.util.List;


import static carsharing.Main.dbService;

public class CompanyListCommand implements Command {
    Customer customer = null;

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
                MenuList<Company> menuList = new MenuList<>(new ArrayList<>(companies),
                        new ArrayList<>(List.of(new MenuItem(Operation.EXIT, 0))), "exit");
                object = ConsoleHandler.askObjectOfMenuList(menuList);

                if (object instanceof Operation) {
                    operation = (Operation) object;
                    CommandExecutor.execute(operation);
                } else {
                    if (customer == null) {
                        operation = Operation.CAR_MENU;
                        CommandExecutor.execute(operation, (Company) object);

                    } else {
                        operation = Operation.CAR_LIST;
                        CommandExecutor.execute(operation, (Company) object, customer);
                    }
                    operation = Operation.EXIT;
                }

                table = null;
            }
            while (operation != Operation.EXIT);


        }


    }


    @Override
    public void setT(Table... table) {
        if (table != null && table.length != 0) {
            customer = (Customer) table[0];
        }
    }
}
