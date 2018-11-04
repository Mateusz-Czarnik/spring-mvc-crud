package pl.mczarnik.springmvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mczarnik.springmvc.entity.customer.CustomerEntity;
import pl.mczarnik.springmvc.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerEntity> getCustomers() {
        return customerService.getCustomers();
    }
}
