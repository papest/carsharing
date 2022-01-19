package carsharing.command;

import carsharing.Car;
import carsharing.Company;
import carsharing.ConsoleHandler;
import carsharing.Main;
import carsharing.dao.CarDao;
import carsharing.dao.CarDaoImpl;


import java.util.List;
import java.util.stream.Collectors;

public class CarListCommand implements Command<Company> {
    private Company company;


    public CarListCommand(){


    }



    @Override
    public void execute() {
        CarDao carDao = new CarDaoImpl(Main.dbService.getConn());
        List<Car> cars = carDao.getAllCarOfCompany(company);
        if (cars.isEmpty()) {
            ConsoleHandler.write("The car list is empty!\n");
        } else {
            ConsoleHandler.write("'" + company.getName() + "' cars\n");
            int[]number = {1};
            ConsoleHandler.write(cars.stream().map(car -> String.format("%s. %s", number[0]++, car.getName()))
                    .collect(Collectors.joining("\n")) + "\n\n");
        }

    }

    @Override
    public void setT(Company company) {
        this.company = company;
    }

}
