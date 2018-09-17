package pl.mczarnik.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import pl.mczarnik.springmvc.entity.user.UserEntity;
import pl.mczarnik.springmvc.model.UserModel;
import pl.mczarnik.springmvc.service.UserService;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/show")
    public String showRegister(Model model) {
        model.addAttribute("User", new UserModel());

        return "register";
    }

    @PostMapping("/process")
    public String processRegister(
            @Valid @ModelAttribute("User") UserModel user,
            BindingResult bindingResult,
            Model model) {

        String userName = user.getUserName();
        logger.info("Processing registration form for: " + userName);

        // form validation
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // check the database if user already exists
        UserEntity existing = userService.findByUserName(userName);
        if (existing != null) {
            model.addAttribute("user", new UserModel());
            model.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "register";
        }
        // create user account
        userService.save(user);

        logger.info("Successfully created user: " + userName);

        return "registration-confirmation";
    }
}
