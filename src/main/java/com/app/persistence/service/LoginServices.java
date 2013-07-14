/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

import com.app.model.Admin;
import com.app.model.User;
import com.app.persistence.dao.ITransactionDAO;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author duy
 */

public class LoginServices implements ILoginServices{
    @Autowired
    ITransactionDAO transactionDAO;
    
    @Autowired
    IQueryList queryList;
    
    @Transactional(readOnly = false)
    public void createAdmin() {
        String strQuery = queryList.getQueryStr("findAllAdmins");
        if(transactionDAO.findAll(strQuery).isEmpty()) {
            Admin admin = new Admin("admin", "admin", "John", "Lee", "0987876545", "admin@gmail.com");
            boolean c = transactionDAO.persistData(admin);
        }

    }

    @Transactional
    public User validateLogin(String username, String password) {
       String strQuery = queryList.getQueryStr("findUserByUsername");
       HashMap hm = new HashMap();
       hm.put("username", username);
       User user = (User)transactionDAO.findByCondition(strQuery, hm);
        
       if(user == null || !user.getPassword().equals(password)) {
           return null;
       }
       return user;
    }
    
    public ITransactionDAO getTransactionDAO() {
        return transactionDAO;
    }

    public void setTransactionDAO(ITransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public IQueryList getQueryList() {
        return queryList;
    }
    
    public void setQueryList(IQueryList queryList) {
        this.queryList = queryList;
    }
    
}
