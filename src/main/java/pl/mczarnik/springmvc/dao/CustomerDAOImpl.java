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
}
