package pl.mczarnik.springmvc.dao;


import pl.mczarnik.springmvc.entity.user.RoleEntity;

import java.util.List;

public interface RoleDao {
    RoleEntity findRoleByName(String theRoleName);

    List<RoleEntity> getRoles();
}
