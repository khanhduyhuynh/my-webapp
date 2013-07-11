/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
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
    
    // ======================================
    // =            Constructors            =
    // ======================================

    public ItemSupplier() {

    }
    
    public ItemSupplier(ItemSupplierKey itemSupplierKey) {
        this.itemSupplierKey = itemSupplierKey;
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
}
