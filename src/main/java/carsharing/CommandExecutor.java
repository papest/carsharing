package carsharing;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {

    private final static Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    static {
        allKnownCommandsMap.put(Operation.LOGIN,new LoginCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
        allKnownCommandsMap.put(Operation.COMPANY_LIST, new CompanyListCommand());
        allKnownCommandsMap.put(Operation.COMPANY_CREATE, new CompanyCreateCommand());
    }

    private CommandExecutor() {
    }

    public static void execute(Operation operation) {
        allKnownCommandsMap.get(operation).execute();
    }
}
