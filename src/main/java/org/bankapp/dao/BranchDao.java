package org.bankapp.dao;

import org.bankapp.entity.Branch;
import org.bankapp.entity.Customer;
import java.util.List;

public interface BranchDao {

    Branch createBranch(Branch branch);

    void deleteBranch(String code);

    List<Customer> getAllCustomers(String branchCode);

}
