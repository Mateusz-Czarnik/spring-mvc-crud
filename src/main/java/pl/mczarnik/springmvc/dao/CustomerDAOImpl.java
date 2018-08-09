package pl.mczarnik.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.mczarnik.springmvc.entity.Customer;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // Injecting session factory
    @Autowired
    private SessionFactory sessionFactory;

    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Customer.class, id);
    }

    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery("FROM Customer ORDER BY lastName", Customer.class);

        return query.getResultList();
    }

    public void saveOrUpdateCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(customer);
    }

    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery("DELETE FROM Customer WHERE id=:id");
        query.setParameter("id", id);

        query.executeUpdate();
    }

    public List<Customer> searchCustomers(String name) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Customer> query = null;
        if (name != null && name.trim().length() > 0) {

            query = currentSession.createQuery("FROM Customer WHERE LOWER(firstName) LIKE :name OR LOWER(lastName) LIKE :name", Customer.class);
            query.setParameter("name", "%" + name.toLowerCase() + "%");

        } else {
            query = currentSession.createQuery("FROM Customer", Customer.class);
        }

        return query.getResultList();
    }
}
