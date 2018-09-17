package pl.mczarnik.springmvc.service;

import pl.mczarnik.springmvc.entity.user.RoleEntity;
import java.util.List;

public interface RoleService {
    List<RoleEntity> getRoles();
}
