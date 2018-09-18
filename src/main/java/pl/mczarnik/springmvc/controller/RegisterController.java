package pl.mczarnik.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.mczarnik.springmvc.entity.user.RoleEntity;
import pl.mczarnik.springmvc.entity.user.UserEntity;
import pl.mczarnik.springmvc.model.UserModel;
import pl.mczarnik.springmvc.service.RoleService;
import pl.mczarnik.springmvc.service.UserService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private Logger logger = Logger.getLogger(getClass().getName());

    private Map<String, String> roles;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostConstruct
    protected void loadRoles() {
        roles = new LinkedHashMap<String, String>();
        List<RoleEntity> rolesFromDb = roleService.getRoles();
        // using hashmap, could also read this info from a database

        for (RoleEntity role : rolesFromDb) {
            String roleName = role.getName();
            String readableName = roleName.substring(roleName.lastIndexOf("_") + 1);
            readableName = readableName.substring(0, 1).toUpperCase() + readableName.substring(1).toLowerCase();

            roles.put(roleName, readableName);
        }
    }

    @GetMapping("/show")
    public String showRegister(Model model) {
        model.addAttribute("User", new UserModel());
        model.addAttribute("roles", roles);

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
            model.addAttribute("roles", roles);

            return "register";
        }

        // check the database if user already exists
        UserEntity existing = userService.findByUserName(userName);
        if (existing != null) {
            model.addAttribute("user", new UserModel());
            model.addAttribute("roles", roles);
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
