/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author duy
 */
@Entity
@Table(name = "profile_table")
public class Profile implements Serializable {
    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue
    protected Long id;
    @Column(nullable = false, unique = true)
    private String businessName;
    @Column(nullable = false, unique = true)
    private String tradingName;
    @Column(nullable = false)
    private String businessPhone;
    @Column(nullable = false)
    private String businessEmail;
    
    @OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.REMOVE)
    @JoinColumn(name = "address_fk", nullable = false)
    private Address address;
    
    // ======================================
    // =            Constructors            =
    // ======================================

    public Profile() {

    }

    public Profile(String businessName, String tradingName, String businessPhone, String businessEmail) {
	this.businessName = businessName;
	this.tradingName = tradingName;
	this.businessPhone = businessPhone;
	this.businessEmail = businessEmail;
        //this.address = new Address(street, suburb, postCode, state);
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }
    
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
