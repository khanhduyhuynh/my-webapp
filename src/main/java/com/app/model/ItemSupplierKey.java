/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author duy
 */
@Embeddable
public class ItemSupplierKey implements Serializable {
    // ======================================
    // =             Attributes             =
    // ======================================
    
    @ManyToOne
    @JoinColumn(name = "item_fk", nullable = false)
    private Item item;
    
    @ManyToOne
    @JoinColumn(name = "supplier_fk", nullable = false)
    private Supplier supplier;
    
    // ======================================
    // =            Constructors            =
    // ======================================

    public ItemSupplierKey() {

    }
    
    public ItemSupplierKey(Item item, Supplier supplier) {
        this.item = item;
        this.supplier = supplier;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    
}