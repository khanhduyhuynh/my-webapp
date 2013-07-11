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
    
    @OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.REMOVE)
    @JoinColumn(name = "profile_fk", nullable = false)
    private Profile profile;
    
    // ======================================
    // =            Constructors            =
    // ======================================
    
    public Supplier() {
        
    }
    
    public Supplier(String username, String password, String firstName, String lastName, String phone, String email) {
        super(username, password, firstName, lastName, phone, email, Role.SUPPLIER);
    }
    
    public Supplier(String username, String password, String firstName, String lastName, String phone, String email, Date createdDate) {
        super(username, password, firstName, lastName, phone, email, createdDate, Role.SUPPLIER);
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
    
}
