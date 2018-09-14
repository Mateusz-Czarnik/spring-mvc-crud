package pl.mczarnik.springmvc.dao;


import pl.mczarnik.springmvc.entity.RoleEntity;

public interface RoleDao {

    public RoleEntity findRoleByName(String theRoleName);

}
