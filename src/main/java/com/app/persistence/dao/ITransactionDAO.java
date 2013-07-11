/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.dao;

import com.app.model.User;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author duy
 */
public interface ITransactionDAO<T> {
    public SessionFactory getSessionFactory();
    public boolean persistData(Object data);
    public boolean deleteData(Object data);
    public boolean updateData(Object data);

    public User findDataByUsername(String username);
}
