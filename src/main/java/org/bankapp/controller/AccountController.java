package org.bankapp.controller;

import org.bankapp.dao.AccountDao;
import org.bankapp.dao.CustomerDao;
import org.bankapp.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountDao dao;

    @PutMapping("/credit/{accNo}")
    public String creditAccount(@PathVariable long accNo,@RequestParam(value = "amount") double amount){
        return dao.creditAccount(accNo,amount);
    }

    @PutMapping("/debit/{accNo}")
    public String debitAccount(@PathVariable long accNo,@RequestParam(value = "amount") double amount){
        return dao.debitAccount(accNo,amount);
    }

    @GetMapping("/{customerId}")
    public Account getAccountDetails(@PathVariable int customerId)
    {
        return dao.getAccountDetails(customerId);
    }

    @DeleteMapping("/{accNo}")
    public void deleteAccount(@PathVariable Long accNo)
    {
        dao.deleteAccount(accNo);
    }
}
