package pl.mczarnik.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mczarnik.springmvc.dao.RoleDao;
import pl.mczarnik.springmvc.entity.user.RoleEntity;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional("securityTransactionManager")
    public List<RoleEntity> getRoles() {
        System.out.println("HEllo WOELR");
        return roleDao.getRoles();
    }
}
