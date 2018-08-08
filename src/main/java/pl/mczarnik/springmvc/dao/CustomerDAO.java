package pl.mczarnik.springmvc.dao;

import pl.mczarnik.springmvc.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomers();

    void addCustomer(Customer customer);
}
