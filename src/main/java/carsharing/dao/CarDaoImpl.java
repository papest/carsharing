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
                    " ORDER BY id");

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
}
