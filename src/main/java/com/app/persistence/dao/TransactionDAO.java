
package com.app.persistence.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    
    public List<T> findAll(String strQuery) {
        return sessionFactory.getCurrentSession().createQuery(strQuery).list();
    }
    
    public List<T> findAllByCondition(String strQuery, HashMap parameters) {
        List<T> result = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(strQuery);
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            result = query.list();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
	return result;
    }
    
    public T findByOneCondition(String strQuery, String parameterKey, Object parameterValue) {
        T result = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(strQuery);
            query.setParameter(parameterKey, parameterValue);
            result = (T)query.uniqueResult();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
	return result;
    }
    
    public T findByManyConditions(String strQuery, HashMap parameters) {
        T result = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(strQuery);
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            result = (T)query.uniqueResult();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
	return result;
    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
   
}
