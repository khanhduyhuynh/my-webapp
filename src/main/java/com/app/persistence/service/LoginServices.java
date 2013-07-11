/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

import com.app.model.User;
import com.app.persistence.dao.ITransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author duy
 */
@Transactional(readOnly = true)
public class LoginServices implements ILoginServices{
    @Autowired
    ITransactionDAO transactionDAO;

    public ITransactionDAO getTransactionDAO() {
        return transactionDAO;
    }

    public void setTransactionDAO(ITransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public User validateLogin(String username, String password) {
       User user = transactionDAO.findDataByUsername(username);
        
       if(user == null || !user.getPassword().equals(password)) {
           return null;
       }
       return user;
    }
    
}
