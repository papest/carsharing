package carsharing.dao;

import carsharing.Car;
import carsharing.Company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CarDaoImpl implements CarDao {
    Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public CarDaoImpl(Connection connection) {
        this.connection = connection;

        try {
            this.statement = connection.createStatement();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCar(String car, int companyId) {
        try {
            checkTableCar();
            statement.executeUpdate("INSERT INTO CAR (name, COMPANY_ID) VALUES ('" + car + "', " + companyId + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkTableCar() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM CAR");
        resultSet.next();
        if (resultSet.getInt("COUNT") == 0) {
            statement.executeUpdate("ALTER TABLE CAR ALTER COLUMN ID RESTART WITH 1");
        }
    }

    @Override
    public List<Car> getAllCarOfCompany(Company company) {
        List<Car> result = new ArrayList<>();
        int companyId = company.getId();

        try {
            resultSet = statement.executeQuery("SELECT ID, name FROM CAR where COMPANY_ID = " + companyId +
                    " ORDER BY ID");

            while (resultSet.next()) {
                result.add(new Car(resultSet.getInt("ID"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }


    @Override
    public void deleteCar() {

    }

    @Override
    public Car getCar(Integer rentedCarId) {
        try {
            resultSet = statement.executeQuery("SELECT name from CAR WHERE ID = " + rentedCarId);
            if (resultSet.next()) {
                return new Car(rentedCarId, resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getCompanyId(int carId) {
        try {
            resultSet = statement.executeQuery("SELECT COMPANY_ID FROM CAR WHERE ID = " + carId);

            resultSet.next();
            return resultSet.getInt("COMPANY_ID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> getFreeCarsOfCompany(Company company) {
        List<Car> result = new ArrayList<>();
        int companyId = company.getId();

        try {
            resultSet = statement.executeQuery("SELECT ID, name FROM CAR WHERE COMPANY_ID = " + companyId +
                    " AND ID NOT IN (SELECT RENTED_CAR_ID FROM CUSTOMER WHERE RENTED_CAR_ID IS NOT NULL )" +
                    " ORDER BY ID");

            while (resultSet.next()) {
                result.add(new Car(resultSet.getInt("ID"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

}
