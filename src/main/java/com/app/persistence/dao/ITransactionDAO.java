/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.dao;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author duy
 */
public interface ITransactionDAO<T> {

    public boolean persistData(Object data);
    public boolean deleteData(Object data);
    public boolean updateData(Object data);
    
    public List<T> findAll(String strQuery);
    public List<T> findAllByCondition(String strQuery, HashMap parameters);
    public T findByCondition(String strQuery, HashMap<String, Object> parameters);
    
}
