/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

import com.app.model.Address;
import com.app.model.Admin;
import com.app.model.BillingAddress;
import com.app.model.Customer;
import com.app.model.Item;
import com.app.model.Profile;
import com.app.model.ShippingAddress;
import com.app.model.Supplier;
import com.app.model.User;

import com.app.persistence.service.ITransactionServices;
import com.app.persistence.service.MailServices;
import com.app.persistence.service.QueryList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author duy
 */
@ContextConfiguration
public class UserServicesTest extends AbstractTransactionalJUnit4SpringContextTests {
    
   @Autowired
   private ITransactionServices transactionServices;
   
   @Autowired
    QueryList queryList;
   

    /**
     * Test of addUserManagement method, of class TransactionServices.
     */
    @Test
    public void testPersistUser() {
     /*   
        Admin admin = new Admin("admin1", "admin1", "John", "Lee", "0987876545", "admin@gmail.com");
        boolean checkAdmin = transactionServices.persistData(admin);
        Assert.assertEquals(true, checkAdmin);
        
        Address address = new Address("2 Surrey St", "Marrickville", "2204", "NSW");
        boolean checkAddress = transactionServices.persistData(address);
        Assert.assertEquals(true, checkAddress);
        
        Profile profile = new Profile("ABC1 Company", "ABC1 Company", "0406051784", "abccompany@gmail.com");
        profile.setAddress(address);
        boolean checkProfile = transactionServices.persistData(profile);
        Assert.assertEquals(true, checkProfile);
        
        Supplier supplier = new Supplier("ABC", "abc", "Frank", "Terry", "7666776677", "abc@gmail.com");
        supplier.setProfile(profile);
        boolean checkSupplier = transactionServices.persistData(supplier);
        Assert.assertEquals(true, checkSupplier);
        
        BillingAddress billingAddress = new BillingAddress("2 Surrey St", "Marrickville", "2204", "NSW");
        boolean checkBillingAddress = transactionServices.persistData(billingAddress);
        Assert.assertEquals(true, checkBillingAddress);
        
        ShippingAddress shippingAddress = new ShippingAddress("2 Surrey St", "Marrickville", "2204", "NSW");
        boolean checkShippingAddress = transactionServices.persistData(shippingAddress);
        Assert.assertEquals(true, checkShippingAddress);
        
        Customer customer = new Customer("xyz", "xyz", "Tom", "Hank", "0490986734", "xyz@gmail.com");
        customer.setBillingAddress(billingAddress);
        customer.setShippingAddress(shippingAddress);
        customer.setSupplier(supplier);
        boolean checkCustomer = transactionServices.persistData(customer);
        Assert.assertEquals(true, checkCustomer);
    */    
        Address address = new Address("2 Surrey St", "Marrickville", "2204", "NSW");
        boolean checkAddress = transactionServices.persistData(address);
        Assert.assertEquals(true, checkAddress);
        
        Profile profile = new Profile("ABC1 Company", "ABC1 Company", "0406051784", "abccompany@gmail.com");
        profile.setAddress(address);
        boolean checkProfile = transactionServices.persistData(profile);
        Assert.assertEquals(true, checkProfile);
        
        Supplier supplier = new Supplier("ABC", "abc", "Frank", "Terry", "7666776677", "abc@gmail.com", true, "randomString");
        supplier.setProfile(profile);
        boolean checkSupplier = transactionServices.persistData(supplier);
        Assert.assertEquals(true, checkSupplier);
        
        Item item = new Item("IPhone5", "This is the lastest model");
        //List<Supplier> s = new ArrayList<Supplier>();
        //s.add(supplier);
        //supplier.getItems().add(item);
        //item.setSuppliers(s);
        boolean checkItem = transactionServices.persistData(item);
        Assert.assertEquals(true, checkItem);
        
        
        //boolean check = mailservices.sendMail("khanhduyhuynhit@gmail.com", "Test", "Please check here");
        //Assert.assertEquals(true, check);
      /*  
        String strQuery = queryList.getQueryStr("findUserByUsername");

       Customer c = (Customer)transactionServices.findByOneCondition(strQuery, "username", "customer");
       //String s = c.getSupplier().getProfile().getBusinessName();
       String s = c.getPassword();
       Assert.assertEquals("customer", s);
        
        item.getSuppliers().add(supplier);
        supplier.getItems().add(item);
        
        
        String strQuery = queryList.getQueryStr("findUserByUsername");
       HashMap hm = new HashMap();
       hm.put("username", "xyz");
       Customer c = (Customer)transactionServices.findByCondition(strQuery, hm);
       String s = c.getSupplier().getProfile().getBusinessName();
       Assert.assertEquals("ABC1 Company", s);
       
       
       Customer customer1 = new Customer("xyz1", "xyz1", "Tom", "Hank", "0490986734", "xyz@gmail.com");
        customer1.setBillingAddress(billingAddress);
        customer1.setShippingAddress(shippingAddress);
        customer1.setSupplier(supplier);
       supplier.getCustomers().add(customer);
       supplier.getCustomers().add(customer1);
       
       int i = supplier.getCustomers().size();
       Assert.assertEquals(2, i);
       */
    }
   
}