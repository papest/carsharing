package carsharing.dao;


import carsharing.Company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public CompanyDaoImpl(Connection connection) {
        this.connection = connection;

        try {
            this.statement = connection.createStatement();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCompany(String company) {
        try {
            checkTableCompany();
            statement.executeUpdate("INSERT INTO COMPANY (name) VALUES('" + company + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkTableCompany() throws SQLException {


        ResultSet  resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM COMPANY");
        resultSet.next();
        if (resultSet.getInt("COUNT") == 0) {
            statement.executeUpdate("ALTER TABLE COMPANY ALTER COLUMN ID RESTART WITH 1");
        }

    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> result = new ArrayList<>();

        try {
            resultSet = statement.executeQuery("SELECT ID, name FROM COMPANY ORDER BY ID");

            while (resultSet.next()) {
                result.add(new Company(resultSet.getInt("ID"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public Company getCompany(int companyId) {
        try {
            resultSet = statement.executeQuery("SELECT * FROM COMPANY WHERE ID = " + companyId);

        resultSet.next();
        return new Company(resultSet.getInt("ID"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void deleteCompany(Company company) {

    }


}
