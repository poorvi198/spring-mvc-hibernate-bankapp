package org.bankapp.dao;

import org.bankapp.entity.Account;

public interface AccountDao {

    String creditAccount(long accNo, double amount);

    String  debitAccount(long accNo, double amount);

    Account getAccountDetails(int customerId);

    void deleteAccount(long accNo);
}
