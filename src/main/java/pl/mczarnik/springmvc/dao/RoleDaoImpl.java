package pl.mczarnik.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.mczarnik.springmvc.entity.user.RoleEntity;

@Repository
public class RoleDaoImpl implements RoleDao {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    public RoleEntity findRoleByName(String roleName) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

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
}
