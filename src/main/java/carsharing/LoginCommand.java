package carsharing;

import java.util.ArrayList;
import java.util.List;

public class LoginCommand implements Command {
    @Override
    public void execute() {
        Operation operation;
        do {
            Menu menu = new Menu(new ArrayList<>(List.of(new MenuItem(Operation.COMPANY_LIST, 1),
                    new MenuItem(Operation.COMPANY_CREATE, 2),
                    new MenuItem(Operation.EXIT, 0))),
                    "company");
            operation = ConsoleHandler.askOperation(menu);
            CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);
    }
}
