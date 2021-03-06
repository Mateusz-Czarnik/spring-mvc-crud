package pl.mczarnik.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.mczarnik.springmvc.entity.user.RoleEntity;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    // need to inject the session factory
    @Autowired
    private SessionFactory securitySessionFactory;

    @Override
    public RoleEntity findRoleByName(String roleName) {

        // get the current hibernate session
        Session currentSession = securitySessionFactory.getCurrentSession();

        // now retrieve/read from database using name
        Query<RoleEntity> theQuery = currentSession.createQuery("from RoleEntity where name=:roleName", RoleEntity.class);
        theQuery.setParameter("roleName", roleName);

        RoleEntity role = null;

        try {
            role = theQuery.getSingleResult();
        } catch (Exception e) {
            role = null;
        }

        return role;
    }

    @Override
    public List<RoleEntity> getRoles() {
        Session currentSession = securitySessionFactory.getCurrentSession();

        Query<RoleEntity> query = currentSession.createQuery("from RoleEntity", RoleEntity.class);

        return query.getResultList();
    }
}
