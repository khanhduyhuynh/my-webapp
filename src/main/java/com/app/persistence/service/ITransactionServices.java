/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author duy
 */
public interface ITransactionServices<T> {
    public boolean persistData(T data);
    public boolean deleteData(T data);
    public boolean updateData(T data);
    
    public List<T> findAll(String strQuery);
    public List<T> findAllByCondition(String strQuery, HashMap parameters);
    
    public T findByOneCondition(String strQuery, String parameterKey, Object parameterValue);
    public T findByManyConditions(String strQuery, HashMap parameters);
}
