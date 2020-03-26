package org.bankapp.controller;

import org.bankapp.dao.BranchDao;
import org.bankapp.dao.CustomerDao;
import org.bankapp.entity.Branch;
import org.bankapp.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    BranchDao dao;

    @PostMapping("/")
    public Branch addBranch(@RequestBody Branch branch)
    {
        return dao.createBranch(branch) ;
    }

    @GetMapping(value = "/{branchCode}")
    public List<Customer> getAllCustomers(@PathVariable String branchCode)
    {
        return dao.getAllCustomers(branchCode);
    }

    @DeleteMapping(value = "/{branchCode}")
    public void deleteBranch(@PathVariable String branchCode){
        dao.deleteBranch(branchCode);
    }

}
