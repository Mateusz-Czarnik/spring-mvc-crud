package pl.mczarnik.springmvc.dao;

import pl.mczarnik.springmvc.entity.customer.CustomerEntity;

import java.util.List;

public interface CustomerDAO {
    CustomerEntity getCustomer(int id);

    List<CustomerEntity> getCustomers();

    void saveOrUpdateCustomer(CustomerEntity customer);

    void deleteCustomer(int id);

    List<CustomerEntity> searchCustomers(String name);
}
