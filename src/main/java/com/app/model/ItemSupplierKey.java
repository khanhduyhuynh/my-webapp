/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
<<<<<<< HEAD
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
=======
>>>>>>> 3c849adb0561eb95902fd0446094045dde6c0a28

/**
 *
 * @author duy
 */
@Embeddable
public class ItemSupplierKey implements Serializable {
    // ======================================
    // =             Attributes             =
    // ======================================
    
<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "item_fk", nullable = false)
    private Item item;
    
    @ManyToOne
    @JoinColumn(name = "supplier_fk", nullable = false)
    private Supplier supplier;
=======
    private Long itemId;
    private Long supplierId;
>>>>>>> 3c849adb0561eb95902fd0446094045dde6c0a28
    
    // ======================================
    // =            Constructors            =
    // ======================================

    public ItemSupplierKey() {

    }
    
    public ItemSupplierKey(Item item, Supplier supplier) {
<<<<<<< HEAD
        this.item = item;
        this.supplier = supplier;
=======
        this.itemId = item.getId();
        this.supplierId = supplier.getId();
>>>>>>> 3c849adb0561eb95902fd0446094045dde6c0a28
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
<<<<<<< HEAD

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
=======
    
    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
>>>>>>> 3c849adb0561eb95902fd0446094045dde6c0a28
    }
    
    
}