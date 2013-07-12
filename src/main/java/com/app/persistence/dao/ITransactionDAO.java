/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author duy
 */
public interface ITransactionDAO<T> {

    public boolean persistData(Object data);
    public boolean deleteData(Object data);
    public boolean updateData(Object data);
    
    public List<T> findAll(String strQuery);
    public T findByCondition(String strQuery, HashMap<String, Object> parameters);
    
}
