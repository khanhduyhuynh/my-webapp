/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author duy
 */
@Entity
@Table(name = "supplier_table")
public class Supplier extends User implements Serializable {
    // ======================================
    // =             Attributes             =
    // ======================================
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "profile_fk", nullable = false)
    private Profile profile;
    
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.REMOVE)
    private List<Customer> customers = new ArrayList<Customer>();

    
    // ======================================
    // =            Constructors            =
    // ======================================
    
    public Supplier() {
        super.roleName = Role.SUPPLIER;
    }
    
    public Supplier(String username, String password, String firstName, String lastName, String phone, String email, boolean activationStatus, String activationKey) {
        super(username, password, firstName, lastName, phone, email, Role.SUPPLIER, activationStatus, activationKey);
    }
    
    public Supplier(String username, String password, String firstName, String lastName, String phone, String email, Date createdDate, boolean activationStatus, String activationKey) {
        super(username, password, firstName, lastName, phone, email, createdDate, Role.SUPPLIER, activationStatus, activationKey);
    }
    
    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
