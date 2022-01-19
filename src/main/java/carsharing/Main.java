package carsharing;



import carsharing.command.CommandExecutor;
import carsharing.menu.Menu;
import carsharing.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static DBService dbService;

    public static void main(String[] args) {

        dbService = new DBService(args);
        Operation operation;
        do {

            Menu menu = new Menu(new ArrayList<>(List.of(new MenuItem(Operation.LOGIN,1),
                    new MenuItem(Operation.EXIT, 0) )),
                    "login");
            operation = ConsoleHandler.askOperation(menu);
            CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);
    }
}