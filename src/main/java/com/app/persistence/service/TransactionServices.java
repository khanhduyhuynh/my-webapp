/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

import com.app.persistence.dao.ITransactionDAO;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author duy
 */

public class TransactionServices<T> implements ITransactionServices {
    // UserDAO is injected...
    @Autowired
    ITransactionDAO transactionDAO;
	
    @Transactional(readOnly = false)
    public boolean persistData(Object data) {
    	return transactionDAO.persistData(data);
    }

    @Transactional(readOnly = false)
    public boolean deleteData(Object data) 
    {
	return transactionDAO.deleteData(data);
    }

    @Transactional(readOnly = false)
    public boolean updateData(Object data)
    {
	return transactionDAO.updateData(data);
    }
    
    @Transactional(readOnly = true)
    public List<T> findAll(String strQuery) {
        return transactionDAO.findAll(strQuery);
    }

    @Transactional(readOnly = true)
    public T findByCondition(String strQuery, HashMap parameters) {
       return (T)transactionDAO.findByCondition(strQuery, parameters);
    }
    
    public ITransactionDAO getTransactionDAO() {
        return transactionDAO;
    }

    public void setTransactionDAO(ITransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }
  
}
