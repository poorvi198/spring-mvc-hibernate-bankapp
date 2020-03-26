package org.bankapp.dao;

import org.bankapp.entity.Account;
import org.bankapp.entity.Branch;
import org.bankapp.entity.Customer;

import java.util.List;

public interface CustomerDao {

    long createCustomer(Customer customer, String branchCode);

    Customer getCustomerDetails(long accNo);

    Customer updateCustomerDetails(Customer customer, int id);

    void deleteCustomer(int id);


}
