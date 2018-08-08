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

    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery("FROM Customer", Customer.class);

        return query.getResultList();
    }

    public void addCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        session.save(customer);
    }
}
