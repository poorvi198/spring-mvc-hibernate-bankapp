package org.bankapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @Column(name = "code")
    private String branchCode;

    @Embedded
    private Address address;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
    mappedBy = "branch")
    private List<Customer> customers;

    public Branch() {
    }

    public Branch(String branchCode, Address address) {
        this.branchCode = branchCode;
        this.address = address;
    }


    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomers(Customer customer)
    {
        if(customers == null)
        {
            customers = new ArrayList<Customer>();
        }
        customers.add(customer);
        customer.setBranch(this);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchCode='" + branchCode + '\'' +
                ", address=" + address +
                ", customers=" + customers +
                '}';
    }
}
