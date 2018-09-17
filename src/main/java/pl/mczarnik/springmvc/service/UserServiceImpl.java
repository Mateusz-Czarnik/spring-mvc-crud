package pl.mczarnik.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mczarnik.springmvc.dao.RoleDao;
import pl.mczarnik.springmvc.dao.UserDao;
import pl.mczarnik.springmvc.entity.user.RoleEntity;
import pl.mczarnik.springmvc.entity.user.UserEntity;
import pl.mczarnik.springmvc.model.UserModel;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional("securityTransactionManager")
    public UserEntity findByUserName(String userName) {
        // check the database if the user already exists
        return userDao.findByUserName(userName);
    }

    @Transactional("securityTransactionManager")
    public void save(UserModel userModel) {
        UserEntity user = new UserEntity();

        // assign user details to the user object
        user.setUserName(userModel.getUserName());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());

        // give user default role of "employee"
        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

        String role = userModel.getRole();

        // save user in the database
        userDao.save(user);
    }

    @Transactional("securityTransactionManager")
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userDao.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
            mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
