package carsharing.command;

import carsharing.ConsoleHandler;
import carsharing.Customer;
import carsharing.Table;
import carsharing.dao.*;

import static carsharing.Main.dbService;

public class ReturnCarCommand implements Command {
    Customer customer;
    @Override
    public void execute() {
        CustomerDao customerDao = new CustomerDaoImpl(dbService.getConn());
        Integer rentedCarId = customerDao.rentedCar(customer.getId());
        if (rentedCarId == null || rentedCarId == 0) {
            ConsoleHandler.write("You didn't rent a car!\n");
        } else {
            customerDao.returnCar(customer.getId());
            ConsoleHandler.write("You've returned a rented car!\n");
        }
    }

    @Override
    public void setT(Table ... table) {

        customer = (Customer) table[0];

    }
}
