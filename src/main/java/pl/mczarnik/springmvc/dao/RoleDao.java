package pl.mczarnik.springmvc.dao;


import pl.mczarnik.springmvc.entity.user.RoleEntity;

public interface RoleDao {

    public RoleEntity findRoleByName(String theRoleName);

}
