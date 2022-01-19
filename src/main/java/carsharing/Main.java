package carsharing;



import java.util.ArrayList;
import java.util.List;

public class Main {


    static DBService dbService;

    public static void main(String[] args) {

        dbService = new DBService(args);
        Operation operation;
        do {

            Menu menu = new Menu(new ArrayList<>(List.of(new MenuItem(Operation.LOGIN, 1),
                    new MenuItem(Operation.EXIT, 0))),
                    "login");
            operation = ConsoleHandler.askOperation(menu);
            CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);
    }
}