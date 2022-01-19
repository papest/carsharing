package carsharing.command;

import carsharing.*;
import carsharing.dao.CarDao;
import carsharing.dao.CarDaoImpl;
import carsharing.dao.CustomerDao;
import carsharing.dao.CustomerDaoImpl;
import carsharing.menu.MenuItem;
import carsharing.menu.MenuList;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static carsharing.Main.dbService;

public class CarListCommand implements Command {
    private Company company;
    private Customer customer = null;


    @Override
    public void execute() {
        CarDao carDao = new CarDaoImpl(dbService.getConn());
        CustomerDao customerDao = new CustomerDaoImpl(dbService.getConn());
        List<Car> cars;
        Operation operation;
        if (customer == null) {
            cars = carDao.getAllCarOfCompany(company);
            if (cars.isEmpty()) {
                ConsoleHandler.write("The car list is empty!\n");
            } else {
                ConsoleHandler.write("'" + company.getName() + "' cars\n");
                int[] number = {1};
                ConsoleHandler.write(cars.stream().map(car -> String.format("%s. %s", number[0]++, car.getName()))
                        .collect(Collectors.joining("\n")) + "\n\n");
            }

        } else {
            cars = carDao.getFreeCarsOfCompany(company);
            if (cars.isEmpty()) {
                ConsoleHandler.write(String.format("No available cars in the '%s' company.\n", company.getName()));
            } else {
                ConsoleHandler.write("Choose a car:\n");
                MenuList<Car> menuList = new MenuList<>(new ArrayList<>(cars),
                        new ArrayList<>(List.of(new MenuItem(Operation.EXIT, 0))),
                        "exit");
                Object object = ConsoleHandler.askObjectOfMenuList(menuList);
                if (object instanceof Operation) {
                    operation = (Operation) object;
                    CommandExecutor.execute(operation);
                } else {
                    customerDao.rentCar(customer.getId(), ((Car) object).getId());
                    ConsoleHandler.write(String.format("You rented '%s'", ((Car) object).getName()));
                }

            }
        }
    }

    @Override
    public void setT(Table ... tables) {
        this.company = (Company) tables[0];
        if (tables.length > 1) {
          this.customer = (Customer) tables[1];
        }
    }

}
