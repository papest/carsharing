package carsharing.command;

import carsharing.ConsoleHandler;
import carsharing.Table;
import carsharing.dao.CompanyDao;
import carsharing.dao.CompanyDaoImpl;

import static carsharing.Main.dbService;

public class CompanyCreateCommand implements Command {
    @Override
    public void execute() {

      ConsoleHandler.write("Enter the company name:\n");
      String companyName = ConsoleHandler.readString();
      CompanyDao companyDao = new CompanyDaoImpl(dbService.getConn());
      companyDao.createCompany(companyName);
      ConsoleHandler.write("The company was created!\n");

    }

    @Override
    public void setT(Table table) {

    }
}
