package carsharing;

import java.sql.Statement;

import static carsharing.Main.dbService;

public class CompanyCreateCommand implements Command {
    @Override
    public void execute() {
      ConsoleHandler.write("Enter the company name:\n");
      String companyName = ConsoleHandler.readString();
      CompanyDao companyDao = new CompanyDaoImpl(dbService.getConn());
      companyDao.createCompany(companyName);

    }
}
