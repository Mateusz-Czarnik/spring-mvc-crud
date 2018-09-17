package pl.mczarnik.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mczarnik.springmvc.dao.CustomerDAO;
import pl.mczarnik.springmvc.entity.customer.CustomerEntity;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    // Simply delegate call to customerDAO
    @Transactional("transactionManager")
    public CustomerEntity getCustomer(int id) {
        return customerDAO.getCustomer(id);
    }

    @Transactional("transactionManager")
    public List<CustomerEntity> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Transactional("transactionManager")
    public void saveOrUpdateCustomer(CustomerEntity customer) {
        customerDAO.saveOrUpdateCustomer(customer);
    }

    @Transactional("transactionManager")
    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }

    @Transactional("transactionManager")
    public List<CustomerEntity> searchCustomers(String name) {
        return customerDAO.searchCustomers(name);
    }
}
