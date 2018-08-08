package pl.mczarnik.springmvc.service;

import pl.mczarnik.springmvc.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(int id);

    List<Customer> getCustomers();

    void saveOrUpdateCustomer(Customer customer);

    void deleteCustomer(int id);
}
