/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Repository;

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
        
        hm.put("findCustomersBySupplier", "select c from Customer c where c.supplier.username = :supplier");
        
        //hm.put("findAllItems", "select i from Item i");
        hm.put("findItemsBySupplier", "select i from Item i join i.suppliers s where s.username = :supplier");
        hm.put("findItemByName", "select i from Item i where i.name = :name");
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
