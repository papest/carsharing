package carsharing.command;

import carsharing.ConsoleHandler;
import carsharing.Operation;
import carsharing.Table;
import carsharing.menu.Menu;
import carsharing.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements Command{
    @Override
    public void execute() {
        Operation operation;
        do {

            Menu menu = new Menu(new ArrayList<>(List.of(new MenuItem(Operation.LOGIN,1),
                    new MenuItem(Operation.CUSTOMER_LOGIN, 2),
                    new MenuItem(Operation.CUSTOMER_CREATE, 3),
                    new MenuItem(Operation.EXIT, 0) )),
                    "login");
            operation = ConsoleHandler.askOperation(menu);
            CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);
    }

    @Override
    public void setT(Table ... table) {

    }
}
