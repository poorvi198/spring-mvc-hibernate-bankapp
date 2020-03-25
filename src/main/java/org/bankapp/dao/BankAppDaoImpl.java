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

import java.util.List;
import java.util.Random;

@Repository
public class BankAppDaoImpl implements BankAppDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public long createNewUser(Customer customer,String branchCode) {
        Session session = sessionFactory.getCurrentSession();
        Branch branch = session.get(Branch.class,branchCode);
        Random random = new Random();
        String tempValue = customer.getContact().toString().substring(6,10);
        long accNo = Long.parseLong(""+(random.nextInt(90000)+10000)+tempValue);
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
    public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class,id);
        session.delete(customer);
    }

    @Override
    @Transactional
    public String creditAccount(long accNo, double amount) {
        Session session = sessionFactory.getCurrentSession();
        Query query1 = session.createQuery("select balance from Account where accountNo = :accNo");
        query1.setParameter("accNo",accNo);
        double bal = (Double) query1.getSingleResult();
        if(amount>bal)
            return "insufficient balance";
        Query query = session.createQuery("update Account set balance = :bal where accountNo = :accNo");
        query.setParameter("bal",bal-amount);
        query.setParameter("accNo",accNo);
        query.executeUpdate();
        return "credited";
    }


    @Override
    @Transactional
    public String debitAccount(long accNo, double amount) {
        Session session = sessionFactory.getCurrentSession();
        Query query1 = session.createQuery("select balance from Account where accountNo = :accNo");
        query1.setParameter("accNo",accNo);
        double bal = (Double) query1.getSingleResult();
        Query query = session.createQuery("update Account set balance = :bal where accountNo = :accNo");
        query.setParameter("bal",bal+amount);
        query.setParameter("accNo",accNo);
        query.executeUpdate();
        return "debited";
    }

    @Override
    @Transactional
    public Account getAccountDetails(int customerId) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class,customerId);
        Account account = customer.getAccount();
        return new Account(account.getAccountNo(),account.getBalance());
    }

    @Override
    @Transactional
    public void deleteAccount(long accNo) {
        Session session = sessionFactory.getCurrentSession();
        Account account = session.get(Account.class,accNo);
        session.delete(account);
    }

    @Override
    @Transactional
    public Branch createBranch(Branch branch) {
        Session session = sessionFactory.getCurrentSession();
        session.save(branch);
        return branch;
    }

    @Override
    @Transactional
    public void deleteBranch(String code) {
        Session session = sessionFactory.getCurrentSession();
        Branch branch = session.get(Branch.class,code);
        session.delete(branch);
    }

    @Override
    @Transactional
    public List<Customer> getAllCustomers(String branchCode) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Branch ");
        List<Customer> customers = query.list();
        System.out.println(customers);
        return customers;
    }


}
