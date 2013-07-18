/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author duy
 */
@Entity
@Table(name = "item_table")
public class Item implements Serializable {
    // ======================================
    // =             Attributes             =
    // ======================================
    
    @Id
    @GeneratedValue
    protected Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Lob
    private String description;
    
    // ======================================
    // =            Constructors            =
    // ======================================
    public Item() {
        
    }
    
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public Item(String name, String description, int unit) {
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
