package carsharing.command;

import carsharing.*;
import carsharing.dao.*;

import static carsharing.Main.*;

public class MyRentedCarCommand implements Command {
    Customer customer;

    @Override
    public void execute() {
        CustomerDao customerDao = new CustomerDaoImpl(dbService.getConn());
        CarDao carDao = new CarDaoImpl(dbService.getConn());
        CompanyDao companyDao = new CompanyDaoImpl(dbService.getConn());
        Integer rentedCarId = customerDao.rentedCar(customer.getId());
        if (rentedCarId == null || rentedCarId == 0 ) {
            ConsoleHandler.write("You didn't rent a car!\n");
        } else {
            ConsoleHandler.write("Your rented car:\n" +
                    carDao.getCar(rentedCarId).getName() +
                    "\nCompany:\n" +
                    companyDao.getCompany(carDao.getCompanyId(rentedCarId)).getName() +"\n");
        }
    }

    @Override
    public void setT(Table ... customer) {
        this.customer = (Customer) customer[0];
    }


}
