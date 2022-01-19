package carsharing.dao;

import carsharing.Company;

import java.util.List;

public interface CompanyDao {

    void createCompany(String company);

    List<Company> getAllCompanies();

    Company getCompany(int companyId);

    void deleteCompany(Company company);


}
