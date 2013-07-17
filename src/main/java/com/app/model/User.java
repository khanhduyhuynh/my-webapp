package com.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    // ======================================
    // =             Attributes             =
    // ======================================
    
    @Id
    @GeneratedValue
    protected Long id;
    @Column(nullable = false, unique = true)
    protected String username;
    @Column(nullable = false)
    protected String password;
    @Column(nullable = false)
    protected String firstName;
    @Column(nullable = false)
    protected String lastName;
    @Column(nullable = false)
    protected String phone;
    @Column(nullable = false)
    protected String email;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Role roleName;
    
    @Column(nullable = false)
    protected boolean activationStatus;
    @Column(nullable = false)
    protected String activationKey;

    // ======================================
    // =            Constructors            =
    // ======================================
    
    public User() {
        
    }

    public User(String username, String password, String firstName, String lastName, String phone, String email, Role role, boolean activationStatus, String activationKey){
        this.username = username;
	this.password = password;
        this.firstName= firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.createdDate = new Date();
        this.roleName = role;
        this.activationStatus = activationStatus;
        this.activationKey = activationKey;
    }
    
    public User(String username, String password, String firstName, String lastName, String phone, String email, Date createdDate, Role role, boolean activationStatus, String activationKey){
        this.username = username;
	this.password = password;
        this.firstName= firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.createdDate = createdDate;
        this.roleName = role;
        this.activationStatus = activationStatus;
        this.activationKey = activationKey;
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
    
    public String getUsername()
    {
	return username;
    }

    public void setUsername(String username)
    {
    	this.username = username;
    }

    public String getPassword()
    {
	return password;
    }

    public void setPassword(String password)
    {
    	this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName(Role role) {
        this.roleName = role;
    }
    
    public boolean isAdmin() {
        return Role.ADMIN.equals(roleName);
    }

    public boolean isSupplier() {
        return Role.SUPPLIER.equals(roleName);
    }
    
    public boolean isCustomer() {
        return Role.CUSTOMER.equals(roleName);
    }

    public boolean getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }
    
}