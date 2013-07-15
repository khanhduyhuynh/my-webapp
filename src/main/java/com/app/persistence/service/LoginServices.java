/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

import com.app.model.Address;
import com.app.model.Admin;
import com.app.model.BillingAddress;
import com.app.model.Customer;
import com.app.model.Item;
import com.app.model.Profile;
import com.app.model.ShippingAddress;
import com.app.model.Supplier;
import com.app.model.User;
import com.app.persistence.dao.ITransactionDAO;
import java.util.HashMap;
import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author duy
 */

public class LoginServices implements ILoginServices{
    @Autowired
    ITransactionDAO transactionDAO;
    
    @Autowired
    IQueryList queryList;
    
    @Transactional(readOnly = false)
    public void createAdmin() {
        String strQuery = queryList.getQueryStr("findAllAdmins");
        if(transactionDAO.findAll(strQuery).isEmpty()) {
            Admin admin = new Admin("admin", "admin", "John", "Lee", "0987876545", "admin@gmail.com");
            transactionDAO.persistData(admin);
            
            Address address = new Address("2 Surrey St", "Marrickville", "2204", "NSW");
            transactionDAO.persistData(address);

            Profile profile = new Profile("ABC Company", "ABC Company", "0406051784", "abccompany@gmail.com");
            profile.setAddress(address);
            transactionDAO.persistData(profile);

            Supplier supplier = new Supplier("supplier", "supplier", "Frank", "Terry", "7666776677", "abc@gmail.com");
            supplier.setProfile(profile);
            transactionDAO.persistData(supplier);

            BillingAddress billingAddress = new BillingAddress("2 Surrey St", "Marrickville", "2204", "NSW");
            transactionDAO.persistData(billingAddress);

            ShippingAddress shippingAddress = new ShippingAddress("2 Surrey St", "Marrickville", "2204", "NSW");
            transactionDAO.persistData(shippingAddress);

            Customer customer = new Customer("customer", "customer", "Tom", "Hank", "0490986734", "xyz@gmail.com");
            customer.setBillingAddress(billingAddress);
            customer.setShippingAddress(shippingAddress);
            customer.setSupplier(supplier);
            transactionDAO.persistData(customer);
            
            Item item = new Item("IPhone5", "This is the lastest model");
            item.getSuppliers().add(supplier);
            transactionDAO.persistData(item);
        }

    }

    @Transactional
    public User validateLogin(String username, String password) {
       String strQuery = queryList.getQueryStr("findUserByUsername");
       HashMap hm = new HashMap();
       hm.put("username", username);
       User user = (User)transactionDAO.findByCondition(strQuery, hm);
        
       if(user == null || !user.getPassword().equals(password)) {
           return null;
       }
       return user;
    }
    
    public ITransactionDAO getTransactionDAO() {
        return transactionDAO;
    }

    public void setTransactionDAO(ITransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public IQueryList getQueryList() {
        return queryList;
    }
    
    public void setQueryList(IQueryList queryList) {
        this.queryList = queryList;
    }
    
}
