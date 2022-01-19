package carsharing;

import carsharing.command.CommandExecutor;

public class Main {

    public static DBService dbService;

    public static void main(String[] args) {

        dbService = new DBService(args);
        CommandExecutor.execute(Operation.MAIN);

    }
}