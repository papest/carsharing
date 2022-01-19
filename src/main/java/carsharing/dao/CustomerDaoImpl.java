package carsharing.dao;

import carsharing.Car;
import carsharing.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public CustomerDaoImpl(Connection connection) {
        this.connection = connection;

        try {
            this.statement = connection.createStatement();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createCustomer(String customer) {
        try {
            checkTableCustomer();
            statement.executeUpdate("INSERT INTO CUSTOMER (name) VALUES('" + customer + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkTableCustomer() throws SQLException {


        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM CUSTOMER");
        resultSet.next();
        if (resultSet.getInt("COUNT") == 0) {
            statement.executeUpdate("ALTER TABLE COMPANY ALTER COLUMN ID RESTART WITH 1");
        }

    }


    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> result = new ArrayList<>();

        try {
            resultSet = statement.executeQuery("SELECT ID, name FROM CUSTOMER ORDER BY ID");

            while (resultSet.next()) {
                result.add(new Customer(resultSet.getInt("ID"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }


    @Override
    public Integer rentedCar(int customerId) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT RENTED_CAR_ID FROM CUSTOMER WHERE ID = " + customerId);

            if (resultSet.next()) {
                return resultSet.getInt("RENTED_CAR_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void rentCar(int customerId, int carId) {
        try {
            statement.executeUpdate("UPDATE CUSTOMER SET RENTED_CAR_ID = " + carId + "WHERE ID =" + customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void returnCar(int customerId) {
        try {
            statement.executeUpdate("UPDATE CUSTOMER SET RENTED_CAR_ID = NULL WHERE ID =" + customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
