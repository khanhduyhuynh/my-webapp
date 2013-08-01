/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author duy
 */
@Entity
@Table(name = "admin_table")
public class Admin extends User implements Serializable {
    
    // ======================================
    // =            Constructors            =
    // ======================================
    
    public Admin() {
        
    }
    
    public Admin(String username, String password, String firstName, String lastName, String phone, String email){
        super(username, password, firstName, lastName, phone, email, Role.ADMIN, true, "randomString");
    }
    
    public Admin(String username, String password, String firstName, String lastName,String phone, String email, Date createdDate){
        super(username, password, firstName, lastName, phone, email, createdDate, Role.ADMIN, true, "randomString");
    }
    
}
