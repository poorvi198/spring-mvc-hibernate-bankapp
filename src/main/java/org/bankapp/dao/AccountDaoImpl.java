package org.bankapp.dao;

import org.bankapp.entity.Account;
import org.bankapp.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    SessionFactory sessionFactory;

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
}
