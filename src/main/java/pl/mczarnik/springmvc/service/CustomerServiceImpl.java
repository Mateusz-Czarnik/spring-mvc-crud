package pl.mczarnik.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mczarnik.springmvc.dao.CustomerDAO;
import pl.mczarnik.springmvc.entity.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    // Simply delegate call to customerDAO
    @Transactional
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Transactional
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }
}
