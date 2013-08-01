/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author duy
 */

public class QueryList implements IQueryList{
    private HashMap hm = new HashMap();
    
    public QueryList() {
        hm.put("findAllUsers", "select u from User u");
        hm.put("findUserByUsername", "select u from User u where u.username = :username");
        
        hm.put("findAllAdmins", "select a from Admin a");
        
        hm.put("findAllSuppliers", "select s from Supplier s");
        hm.put("findSupplierByBusinessName", "select s from Supplier s where s.profile.businessName = :businessName");
        hm.put("findSupplierByTradingName", "select s from Supplier s where s.profile.tradingName = :tradingName");
        
        hm.put("findCustomersBySupplier", "select c from Customer c where c.supplier.username = :supplier");
        
        //hm.put("findAllItems", "select i from Item i");
        hm.put("findItemSuppliersBySupplier", "select item_supplier from ItemSupplier item_supplier where item_supplier.itemSupplierKey.supplier.id = :supplierId");
        hm.put("findItemSupplierByItemAndSupplier", "select item_supplier from ItemSupplier item_supplier where item_supplier.itemSupplierKey.item.id = :itemId and item_supplier.itemSupplierKey.supplier.id = :supplierId");
        hm.put("findItemById", "select i from Item i where i.id = :itemId");
        hm.put("findItemByName", "select i from Item i where i.name = :name");
        hm.put("findOtherItemSuppliers", "select item_supplier from ItemSupplier item_supplier where item_supplier.itemSupplierKey.item.id NOT IN (select item_supplier_table.itemSupplierKey.item.id from ItemSupplier item_supplier_table where item_supplier_table.itemSupplierKey.supplier.id = :supplierId)");
    }
    
    public String getQueryStr(String strKey) {
        Set<Map.Entry<String, String>> set = hm.entrySet();

        for (Map.Entry<String, String> query : set) {
            if(query.getKey().equals(strKey)) {
                return query.getValue();
            }
        }
        return null;
    }
    
    
}
