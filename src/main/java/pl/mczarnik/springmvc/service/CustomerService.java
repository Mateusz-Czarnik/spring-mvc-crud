package pl.mczarnik.springmvc.service;

import pl.mczarnik.springmvc.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    void addCustomer(Customer customer);
}
