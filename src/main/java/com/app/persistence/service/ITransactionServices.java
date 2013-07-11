/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

/**
 *
 * @author duy
 */
public interface ITransactionServices<T> {
    public boolean persistData(T data);
    public boolean deleteData(T data);
    public boolean updateData(T data);
}
