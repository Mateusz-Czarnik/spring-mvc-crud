package pl.mczarnik.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.mczarnik.springmvc.entity.customer.CustomerEntity;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // Injecting session factory
    @Autowired
    private SessionFactory sessionFactory;

    public CustomerEntity getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(CustomerEntity.class, id);
    }

    public List<CustomerEntity> getCustomers() {
        Session session = sessionFactory.getCurrentSession();

        Query<CustomerEntity> query = session.createQuery("FROM CustomerEntity ORDER BY lastName", CustomerEntity.class);

        return query.getResultList();
    }

    public void saveOrUpdateCustomer(CustomerEntity customer) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(customer);
    }

    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query<CustomerEntity> query = session.createQuery("DELETE FROM CustomerEntity WHERE id=:id", CustomerEntity.class);
        query.setParameter("id", id);

        query.executeUpdate();
    }

    public List<CustomerEntity> searchCustomers(String name) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<CustomerEntity> query = null;
        if (name != null && name.trim().length() > 0) {

            query = currentSession.createQuery("FROM CustomerEntity WHERE LOWER(firstName) LIKE :name OR LOWER(lastName) LIKE :name", CustomerEntity.class);
            query.setParameter("name", "%" + name.toLowerCase() + "%");

        } else {
            query = currentSession.createQuery("FROM CustomerEntity", CustomerEntity.class);
        }

        return query.getResultList();
    }
}
