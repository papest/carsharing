package carsharing.command;

import carsharing.*;
import carsharing.dao.CustomerDao;
import carsharing.dao.CustomerDaoImpl;
import carsharing.menu.MenuItem;
import carsharing.menu.MenuList;

import java.util.ArrayList;
import java.util.List;

public class CustomerLoginCommand implements Command {
    @Override
    public void execute() {
        CustomerDao customerDao = new CustomerDaoImpl(Main.dbService.getConn());
        List<Customer> customers = customerDao.getAllCustomers();
        if (customers.isEmpty()) {
            ConsoleHandler.write("The customer list is empty!");
        } else {
            Operation operation = null;
            Object object;
            do {
                MenuList<Customer> menuList = new MenuList<>(new ArrayList<>(customers),
                        new ArrayList<>(List.of(new MenuItem(Operation.EXIT, 0))), "exit");
                object = ConsoleHandler.askObjectOfMenuList(menuList);
                if (object instanceof Operation) {
                    operation = (Operation) object;
                    CommandExecutor.execute(operation);
                } else {
                    operation = Operation.CUSTOMER_MENU;
                    CommandExecutor.execute(operation, (Table) object );
                }



            } while (operation != Operation.EXIT);

        }
    }



    @Override
    public void setT(Table ... table) {

    }
}
