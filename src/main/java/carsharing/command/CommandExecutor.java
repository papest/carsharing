package carsharing.command;

import carsharing.Operation;
import carsharing.Table;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {

    private final static Map<Operation, Command> allKnownCommandsMap = new HashMap<>();
    private static Table table;

    static {
        allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
        allKnownCommandsMap.put(Operation.COMPANY_LIST, new CompanyListCommand());
        allKnownCommandsMap.put(Operation.COMPANY_CREATE, new CompanyCreateCommand());
        allKnownCommandsMap.put(Operation.CAR_CREATE, new CarCreateCommand());
        allKnownCommandsMap.put(Operation.CAR_LIST, new CarListCommand());
        allKnownCommandsMap.put(Operation.CAR_MENU, new CarMenuCommand());
        allKnownCommandsMap.put(Operation.CUSTOMER_CREATE, new CustomerCreateCommand());
        allKnownCommandsMap.put(Operation.CUSTOMER_LOGIN, new CustomerLoginCommand());
        allKnownCommandsMap.put(Operation.CUSTOMER_MENU, new CustomerMenuCommand());
        allKnownCommandsMap.put(Operation.MAIN, new MainCommand());
        allKnownCommandsMap.put(Operation.MY_RENTED_CAR, new MyRentedCarCommand());
        allKnownCommandsMap.put(Operation.RENT_CAR, new RentCarCommand());
        allKnownCommandsMap.put(Operation.RETURN_CAR, new ReturnCarCommand());

    }

    private CommandExecutor() {
    }

    public static void execute(Operation operation, Table... tables) {

        Command command = allKnownCommandsMap.get(operation);
        command.setT(tables);
        command.execute();


    }
}
