/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

import com.app.persistence.dao.ITransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author duy
 */
@Service
public class TransactionServices<T> implements ITransactionServices {
    // UserDAO is injected...
    @Autowired
    ITransactionDAO transactionDAO;

    public ITransactionDAO getTransactionDAO() {
        return transactionDAO;
    }

    public void setTransactionDAO(ITransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }
	
    @Transactional
    public boolean persistData(Object data) {
    	return transactionDAO.persistData(data);
    }

    @Transactional
    public boolean deleteData(Object data) 
    {
	return transactionDAO.deleteData(data);
    }

    @Transactional
    public boolean updateData(Object data)
    {
	return transactionDAO.updateData(data);
    }
}
