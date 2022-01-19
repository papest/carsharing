package carsharing;

import java.util.List;

public interface CompanyDao {
    void createCompany(String company);
    List<Company> getAllCompanies();
    Company getCompany(Company company);
    void deleteCompany(Company company);


}
