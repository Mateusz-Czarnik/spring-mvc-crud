package pl.mczarnik.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mczarnik.springmvc.entity.customer.CustomerEntity;
import pl.mczarnik.springmvc.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getCustomers());

        return "list-customers";
    }

    @GetMapping("/add")
    public String addCustomer(Model model) {
        // Create pl.mczarnik.springmvc.model attribute to bind form data
        CustomerEntity customer = new CustomerEntity();

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @GetMapping("/update")
    public String updateCustomer(@RequestParam("id") int id, Model model) {
        CustomerEntity customer = customerService.getCustomer(id);

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int id) {

        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

    @PostMapping("/process-form")
    public String processForm(@ModelAttribute("customer") CustomerEntity customer) {
        customerService.saveOrUpdateCustomer(customer);

        return "redirect:/customer/list";
    }

    @PostMapping("/search")
    public String searchCustomers(@RequestParam("name") String name, Model model) {

        List<CustomerEntity> customers = customerService.searchCustomers(name);

        model.addAttribute("customers", customers);

        return "list-customers";
    }
}
