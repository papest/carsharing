package carsharing.command;

import carsharing.*;
import carsharing.menu.Menu;
import carsharing.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CarMenuCommand implements Command {
    Company company;
    @Override
    public void execute() {
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
    public void setT(Table ... table) {
        company = (Company) table[0];
    }
}

