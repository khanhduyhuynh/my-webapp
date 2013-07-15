/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author duy
 */
@Entity
@Table(name = "customer_table")
public class Customer extends User implements Serializable {
    // ======================================
    // =             Attributes             =
    // ======================================
    
    @OneToOne(fetch = FetchType.EAGER, cascade= CascadeType.REMOVE)
    @JoinColumn(name = "billingAddress_fk", nullable = false)
    private BillingAddress billingAddress;
    
    @OneToOne(fetch = FetchType.EAGER, cascade= CascadeType.REMOVE)
    @JoinColumn(name = "shippingAddress_fk", nullable = false)
    private ShippingAddress shippingAddress;
    
    @ManyToOne
    @JoinColumn(name = "supplier_fk", nullable = false)
    private Supplier supplier;

    // ======================================
    // =            Constructors            =
    // ======================================
    
    public Customer() {
        
    }
    
    public Customer(String username, String password, String firstName, String lastName, String phone, String email) {
        super(username, password, firstName, lastName, phone, email, Role.CUSTOMER);
    }
    
    public Customer(String username, String password, String firstName, String lastName, String phone, String email, Date createdDate) {
        super(username, password, firstName, lastName, phone, email, createdDate, Role.CUSTOMER);
    }
    
    // ======================================
    // =          Getters & Setters         =
    // ======================================
    
    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
