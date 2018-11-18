package pl.mczarnik.springmvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mczarnik.springmvc.entity.customer.CustomerEntity;
import pl.mczarnik.springmvc.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    // GET
    @GetMapping("/customers")
    public List<CustomerEntity> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public CustomerEntity getCustomers(@PathVariable int customerId) {

        CustomerEntity customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        return customer;
    }

    // POST
    @PostMapping("/customers")
    public CustomerEntity addCustomer(@RequestBody CustomerEntity customer) {
        customer.setId(0);
        customerService.saveOrUpdateCustomer(customer);

        return customer;
    }

    // PUT
    @PutMapping("/customers")
    public CustomerEntity updateCustomer(@RequestBody CustomerEntity customer) {

        customerService.saveOrUpdateCustomer(customer);
        return customer;
    }

    // DELETE
    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {

        CustomerEntity customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer with id " + customerId + " was not found!");
        }

        customerService.deleteCustomer(customerId);
        return "Deleted customer with Id " + customerId;
    }
}
