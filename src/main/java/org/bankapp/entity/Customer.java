package org.bankapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "name")
   public String name;

    @Column(name = "userName")
    public String username;

    @Column(name = "password")
    public String password;

    @Column(name = "contact")
    public Long contact;

    @ManyToOne
    @JoinColumn(name = "branch_code")
    public Branch branch;

    @OneToOne(cascade = CascadeType.ALL,
    mappedBy = "customer")
    public Account account;

    public Customer() {
    }

    public Customer(String name, String username, String password, Long contact) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", contact=" + contact +
                ", branch=" + branch +
                ", account=" + account +
                '}';
    }
}
