package carsharing.command;

import carsharing.Company;
import carsharing.Operation;
import carsharing.Table;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {

    private final static Map<Operation, Command> allKnownCommandsMap = new HashMap<>();
    private static Table table;

    static {
        allKnownCommandsMap.put(Operation.LOGIN,new LoginCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
        allKnownCommandsMap.put(Operation.COMPANY_LIST, new CompanyListCommand());
        allKnownCommandsMap.put(Operation.COMPANY_CREATE, new CompanyCreateCommand());
        allKnownCommandsMap.put(Operation.CAR_LIST,  new CarListCommand());
        allKnownCommandsMap.put(Operation.CAR_CREATE, new CarCreateCommand());
    }

    private CommandExecutor() {
    }

    public static void execute(Operation operation, Table ... tables) {
        if (tables.length == 0) {
            allKnownCommandsMap.get(operation).execute();
        } else {
            table = tables[0];
            Command command =allKnownCommandsMap.get(operation);
            command.setT(table);
            command.execute();

        }
    }
}
