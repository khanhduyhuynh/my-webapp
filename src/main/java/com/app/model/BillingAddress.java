/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author duy
 */
@Entity
@Table(name = "billingAddress_table")
public class BillingAddress extends Address implements Serializable {
    // ======================================
    // =            Constructors            =
    // ======================================
    
    public BillingAddress() {
        
    }
    
    public BillingAddress(String street, String suburb, String postCode, String state) {
        super(street, suburb, postCode, state);
    }
}
