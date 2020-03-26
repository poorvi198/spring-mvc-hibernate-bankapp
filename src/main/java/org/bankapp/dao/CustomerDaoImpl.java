package org.bankapp.dao;

import org.bankapp.entity.Account;
import org.bankapp.entity.Branch;
import org.bankapp.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public long createCustomer(Customer customer, String branchCode) {
        Session session = sessionFactory.getCurrentSession();
        Branch branch = session.get(Branch.class,branchCode);
        Random random = new Random();
        long accNo = Long.parseLong(branchCode.substring(0,4)+(random.nextInt(9000)+1000));
        Account account = new Account(accNo,0);
        account.setCustomer(customer);
        branch.addCustomers(customer);
        session.save(account);
        return accNo;
    }

    @Override
    @Transactional
    public Customer getCustomerDetails(long accNo) {
        Session session =sessionFactory.getCurrentSession();
        Account account = session.get(Account.class,accNo);
        Customer customer = account.getCustomer();
        return new Customer(customer.getName(),customer.getUsername(),customer.getPassword(),customer.getContact());
    }

    @Override
    @Transactional
    public Customer updateCustomerDetails(Customer customer, int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Customer set name= :name , username= :username, password= :password," +
                "contact= :contact where id= :id");
        query.setParameter("name",customer.getName());
        query.setParameter("username",customer.getUsername());
        query.setParameter("password",customer.getPassword());
        query.setParameter("contact",customer.getContact());
        query.setParameter("id",id);
        query.executeUpdate();
        Customer customer1 = session.get(Customer.class,id);
        return new Customer(customer1.getName(),customer1.getUsername(),customer1.getPassword(),customer1.getContact());
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class,id);
        session.delete(customer);
    }
}
