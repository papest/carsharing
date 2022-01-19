package carsharing.command;

import carsharing.*;
import carsharing.dao.CustomerDao;
import carsharing.dao.CustomerDaoImpl;


public class RentCarCommand implements Command {
    Customer customer;
    Car car;

    @Override
    public void execute() {
        CustomerDao customerDao = new CustomerDaoImpl(Main.dbService.getConn());
        Integer rentedCarID = customerDao.rentedCar(customer.getId());
        if (rentedCarID != null && rentedCarID != 0) {
            ConsoleHandler.write("You've already rented a car!\n");
        } else {
            Operation operation = Operation.COMPANY_LIST;
            CommandExecutor.execute(operation, customer);
        }
    }

    @Override
    public void setT(Table[] t) {
        this.customer = (Customer) t[0];

    }


}
