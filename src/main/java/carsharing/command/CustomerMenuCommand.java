package carsharing.command;

import carsharing.ConsoleHandler;
import carsharing.Customer;
import carsharing.Operation;
import carsharing.Table;
import carsharing.menu.Menu;
import carsharing.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CustomerMenuCommand implements Command {
    Customer customer;
    @Override
    public void execute() {
        Operation operation;
        Menu menu = new Menu(new  ArrayList<>(List.of(new MenuItem(Operation.RENT_CAR, 1),
                new MenuItem(Operation.RETURN_CAR, 2), new MenuItem(Operation.MY_RENTED_CAR, 3),
                new MenuItem(Operation.EXIT, 0))),
                "carrent");
        do {
            operation = ConsoleHandler.askOperation(menu);
            CommandExecutor.execute(operation, customer);
        } while (operation != Operation.EXIT);

    }

    @Override
    public void setT(Table... t) {
        customer = (Customer) t[0];
    }
}
