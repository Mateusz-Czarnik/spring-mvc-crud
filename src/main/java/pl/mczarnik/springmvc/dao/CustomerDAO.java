package pl.mczarnik.springmvc.dao;

import pl.mczarnik.springmvc.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    Customer getCustomer(int id);

    List<Customer> getCustomers();

    void saveOrUpdateCustomer(Customer customer);
}
