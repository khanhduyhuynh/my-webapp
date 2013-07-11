
package com.app.persistence.dao;

import com.app.model.User;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDAO<T> implements ITransactionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean persistData(Object data) {
        boolean check = true;
        try
        {
            sessionFactory.getCurrentSession().save(data);
        }
        catch(Exception e)
        {
            check = false;
        }
        return check;
    }
    
    public boolean deleteData(Object data) {
        boolean check = true;
        try
        {
            sessionFactory.getCurrentSession().delete(data);
        }
        catch(Exception e)
        {
            check = false;
        }
        return check;	
    }

    public boolean updateData(Object data) {
        boolean check = true;
        try
        {
            sessionFactory.getCurrentSession().update(data);
        }
        catch(Exception e)
        {
            check = false;
        }
        return check;
    }
    
    public User findDataByUsername(String username) {
	User user = null;
        try{
            Query query = sessionFactory.getCurrentSession().createQuery("select u from User u where u.username = :username")
                    .setParameter("username", username);
            
            user = (User)query.uniqueResult();
        }
        catch(Exception e){
            e.printStackTrace();
        }
	return user;
    }


    
    
}
