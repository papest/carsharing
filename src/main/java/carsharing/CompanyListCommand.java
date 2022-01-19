package carsharing;

import java.util.List;
import java.util.stream.Collectors;

import static carsharing.Main.dbService;

public class CompanyListCommand implements Command {
    @Override
    public void execute() {

        CompanyDao companyDao = new CompanyDaoImpl(dbService.getConn());
        List<Company> companies = companyDao.getAllCompanies();
        if (companies.size() == 0) {
            ConsoleHandler.write("\nThe company list is empty!\n");
        } else {
            ConsoleHandler.write("Company list:\n" + companies.stream()
                    .map(Company::toString)
                    .collect(Collectors.joining("\n")) + "\n\n");
        }


    }
}
