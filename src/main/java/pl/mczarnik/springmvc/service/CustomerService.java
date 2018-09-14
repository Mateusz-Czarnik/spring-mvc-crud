package pl.mczarnik.springmvc.service;

import pl.mczarnik.springmvc.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    CustomerEntity getCustomer(int id);

    List<CustomerEntity> getCustomers();

    void saveOrUpdateCustomer(CustomerEntity customerEntity);

    void deleteCustomer(int id);

    List<CustomerEntity> searchCustomers(String name);
}
