package carsharing.dao;

import carsharing.Car;
import carsharing.Company;

import java.util.List;

public interface CarDao {
    void createCar(String car, int company);
    List<Car> getAllCarOfCompany(Company company);
    void deleteCar();
}
