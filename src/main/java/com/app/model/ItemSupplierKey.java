/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author duy
 */
@Embeddable
public class ItemSupplierKey implements Serializable {
    // ======================================
    // =             Attributes             =
    // ======================================
    
    private Long itemId;
    private Long supplierId;
    
    // ======================================
    // =            Constructors            =
    // ======================================

    public ItemSupplierKey() {

    }
    
    public ItemSupplierKey(Item item, Supplier supplier) {
        this.itemId = item.getId();
        this.supplierId = supplier.getId();
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    
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
    }
    
    
}
