package carsharing.command;

import carsharing.ConsoleHandler;
import carsharing.Table;
import carsharing.dao.CustomerDao;
import carsharing.dao.CustomerDaoImpl;

import static carsharing.Main.dbService;

public class CustomerCreateCommand implements Command {
    @Override
    public void execute() {
        ConsoleHandler.write("Enter the customer name:\n");
        String customerName = ConsoleHandler.readString();
        CustomerDao customerDao = new CustomerDaoImpl(dbService.getConn());
        customerDao.createCustomer(customerName);
        ConsoleHandler.write("The customer was added!\n");
    }

    @Override
    public void setT(Table ... table) {

    }
}
