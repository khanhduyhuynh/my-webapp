/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author duy
 */
@Entity
@Table(name = "shippingAddress_table")
public class ShippingAddress extends Address implements Serializable {
    // ======================================
    // =            Constructors            =
    // ======================================
    
    public ShippingAddress() {
        
    }
    
    public ShippingAddress(String street, String suburb, String postCode, String state) {
        super(street, suburb, postCode, state);
    }
}
