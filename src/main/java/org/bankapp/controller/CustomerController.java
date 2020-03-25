package org.bankapp.controller;

import org.bankapp.dao.BankAppDao;
import org.bankapp.dao.BankAppDaoImpl;
import org.bankapp.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    BankAppDao dao;

    @PostMapping(value = "/customers/{branchCode}")
    public long addCustomer(@RequestBody Customer customer,@PathVariable String branchCode){
        return dao.createNewUser(customer,branchCode);
    }

    @GetMapping(value = "/customers/{accNo}")
    public Customer getCustomer(@PathVariable long accNo)
    {
        return dao.getCustomerDetails(accNo);
    }

    @PutMapping(value = "/customers/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer)
    {
        return dao.updateCustomerDetails(customer,id);
    }

    @DeleteMapping(value = "/customers/{id}")
    public String deleteCustomer(@PathVariable int id){
        dao.deleteUser(id);
        return "deleted customer with id:"+id+" and associated account";
    }


}
