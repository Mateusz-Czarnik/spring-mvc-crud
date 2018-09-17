package pl.mczarnik.springmvc.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.mczarnik.springmvc.entity.user.UserEntity;
import pl.mczarnik.springmvc.model.UserModel;

public interface UserService extends UserDetailsService {
    UserEntity findByUserName(String userName);

    void save(UserModel user);
}
