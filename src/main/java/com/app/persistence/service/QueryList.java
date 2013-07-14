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
