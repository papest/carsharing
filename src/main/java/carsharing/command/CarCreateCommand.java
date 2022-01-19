package carsharing.command;

import carsharing.Company;
import carsharing.ConsoleHandler;
import carsharing.Table;
import carsharing.dao.CarDao;
import carsharing.dao.CarDaoImpl;

import static carsharing.Main.dbService;

public class CarCreateCommand implements Command<Company> {
    Company company;

    public CarCreateCommand(){
    }

    @Override
    public void execute() {
       ConsoleHandler.write("Enter the car name:\n");
       String carName = ConsoleHandler.readString();
       CarDao carDao = new CarDaoImpl(dbService.getConn());
       carDao.createCar(carName, company.getId());
       ConsoleHandler.write("The car was added!\n");
    }

    @Override
    public void setT(Company company) {
        this.company = company;
    }


}
