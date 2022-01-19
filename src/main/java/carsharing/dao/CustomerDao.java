package carsharing.dao;



import carsharing.Customer;

import java.util.List;

public interface CustomerDao {
    void createCustomer(String customer);

    List<Customer> getAllCustomers();

    Integer rentedCar(int customerId);

    void rentCar(int customerId, int carId);

    void returnCar(int customerId);
}
