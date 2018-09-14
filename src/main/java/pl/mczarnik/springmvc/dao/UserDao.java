package pl.mczarnik.springmvc.dao;

import pl.mczarnik.springmvc.entity.UserEntity;

public interface UserDao {

    UserEntity findByUserName(String userName);

    void save(UserEntity user);

}
