package carsharing;


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

            statement.executeUpdate("INSERT into COMPANY (name) VALUES('" + company + "')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> result = new ArrayList<>();

        try {
            resultSet = statement.executeQuery("SELECT * FROM COMPANY");

            while (resultSet.next()) {
                result.add(new Company(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public Company getCompany(Company company) {
        return null;
    }


    @Override
    public void deleteCompany(Company company) {

    }


}
