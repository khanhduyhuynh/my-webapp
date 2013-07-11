/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author duy
 */
@Entity
@Table(name = "address_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class Address implements Serializable {
    // ======================================
    // =             Attributes             =
    // ======================================
    
    @Id
    @GeneratedValue
    protected Long id;
    @Column(nullable = false)
    protected String street;
    @Column(nullable = false)
    protected String suburb;
    @Column(nullable = false)
    protected String postCode;
    @Column(nullable = false)
    protected String state;

    // ======================================
    // =            Constructors            =
    // ======================================
    
    public Address() {
        
    }
    
    public Address(String street, String suburb, String postCode, String state) {
        this.street = street;
	this.suburb = suburb;
        this.postCode = postCode;
	this.state = state;
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
    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
}
