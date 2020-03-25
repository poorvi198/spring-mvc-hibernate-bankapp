package org.bankapp.controller;

import org.bankapp.dao.BankAppDao;
import org.bankapp.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    BankAppDao dao;

    @PutMapping("/accounts/credit/{amount}/{accNo}")
    public String creditAccount(@PathVariable double amount, @PathVariable long accNo){
        return dao.creditAccount(accNo,amount);
    }

    @PutMapping("/accounts/debit/{amount}/{accNo}")
    public String debitAccount(@PathVariable double amount,@PathVariable long accNo){
        return dao.debitAccount(accNo,amount);
    }

    @GetMapping("/accounts/{customerId}")
    public Account getAccountDetails(@PathVariable int customerId)
    {
        return dao.getAccountDetails(customerId);
    }

    @DeleteMapping("/accounts/{accNo}")
    public void deleteAccount(@PathVariable Long accNo)
    {
        dao.deleteAccount(accNo);
    }
}
