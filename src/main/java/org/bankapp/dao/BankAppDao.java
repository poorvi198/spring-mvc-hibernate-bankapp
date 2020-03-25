package org.bankapp.dao;

import org.bankapp.entity.Account;
import org.bankapp.entity.Branch;
import org.bankapp.entity.Customer;

import java.util.List;

public interface BankAppDao {

    long createNewUser(Customer customer,String branchCode);

    Customer getCustomerDetails(long accNo);

    Customer updateCustomerDetails(Customer customer, int id);

    void deleteUser(int id);

    String creditAccount(long accNo, double amount);

    String  debitAccount(long accNo, double amount);

    Account getAccountDetails(int customerId);

    void deleteAccount(long accNo);

    Branch createBranch(Branch branch);

    void deleteBranch(String code);

    List<Customer> getAllCustomers(String branchCode);
}
