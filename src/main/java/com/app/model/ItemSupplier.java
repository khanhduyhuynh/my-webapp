/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author duy
 */
@Entity
@Table(name = "itemSupplier_table")
public class ItemSupplier implements Serializable {
    // ======================================
    // =             Attributes             =
    // ======================================
    
    @EmbeddedId
    private ItemSupplierKey itemSupplierKey;
    
    @Column(nullable = false)
    private int numInStock;
    @Column(nullable = false)
    private double price;
    
    // ======================================
    // =            Constructors            =
    // ======================================

    public ItemSupplier() {

    }
    
    public ItemSupplier(ItemSupplierKey itemSupplierKey, int numInStock, double price) {
        this.itemSupplierKey = itemSupplierKey;
        this.numInStock = numInStock;
        this.price = price;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    
    public ItemSupplierKey getItemSupplierKey() {
        return itemSupplierKey;
    }

    public void setItemSupplierKey(ItemSupplierKey itemSupplierKey) {
        this.itemSupplierKey = itemSupplierKey;
    }

    public int getNumInStock() {
        return numInStock;
    }

    public void setNumInStock(int numInStock) {
        this.numInStock = numInStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}