/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

import com.app.model.User;
import com.app.persistence.dao.ITransactionDAO;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author duy
 */
@Transactional(readOnly = true)
public class LoginServices implements ILoginServices{
    @Autowired
    ITransactionDAO transactionDAO;
    
    @Autowired
    QueryList queryList;

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
    
    public QueryList getQueryList() {
        return queryList;
    }

    public void setQueryList(QueryList queryList) {
        this.queryList = queryList;
    }
    
    public ITransactionDAO getTransactionDAO() {
        return transactionDAO;
    }

    public void setTransactionDAO(ITransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }
  
}
