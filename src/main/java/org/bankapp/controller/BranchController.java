package org.bankapp.controller;

import org.bankapp.dao.BankAppDao;
import org.bankapp.entity.Branch;
import org.bankapp.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BranchController {

    @Autowired
    BankAppDao dao;

    @PostMapping("/branch")
    public Branch addBranch(@RequestBody Branch branch)
    {
        return dao.createBranch(branch) ;
    }

    @GetMapping(value = "/branch/{branchCode}")
    public List<Customer> getAllCustomers(@PathVariable String branchCode)
    {
        return dao.getAllCustomers(branchCode);
    }

    @DeleteMapping(value = "/branch/{branchCode}")
    public void deleteBranch(@PathVariable String branchCode){
        dao.deleteBranch(branchCode);
    }

}
