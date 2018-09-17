package pl.mczarnik.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.mczarnik.springmvc.entity.user.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory securitySessionFactory;

    public UserEntity findByUserName(String userName) {
        // get the current hibernate session
        Session currentSession = securitySessionFactory.getCurrentSession();

        // now retrieve/read from database using username
        Query<UserEntity> theQuery = currentSession.createQuery("FROM UserEntity WHERE userName=:uName", UserEntity.class);
        theQuery.setParameter("uName", userName);
        UserEntity user = null;
        try {
            user = theQuery.getSingleResult();
        } catch (Exception e) {
            user = null;
        }

        return user;
    }

    public void save(UserEntity theUser) {
        Session currentSession = securitySessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(theUser);
    }
}
