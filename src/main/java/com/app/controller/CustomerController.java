/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.model.BillingAddress;
import com.app.model.Customer;
import com.app.model.ShippingAddress;
import com.app.model.Supplier;
import com.app.model.User;
import com.app.persistence.service.ITransactionServices;
import com.app.persistence.service.QueryList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author duy
 */
@ManagedBean(name="customerController")
@ViewScoped
public class CustomerController extends AbstractController {
    @ManagedProperty(value="#{TransactionServices}")
    private ITransactionServices transactionServices;
    
    @ManagedProperty(value="#{QueryList}")
    private QueryList queryList;
    
    @ManagedProperty(value=UserController.INJECTION_NAME)
    private UserController userController;
    
    private Customer customer;
    private List<Customer> customers = new ArrayList<Customer>();
    
    private BillingAddress billingAddress;
    private ShippingAddress shippingAddress;
    private Supplier supplier;
    
    @PostConstruct
    public void init() {
        User user = userController.getUser();
        String strQuery = queryList.getQueryStr("findUserByUsername");
        supplier = (Supplier)transactionServices.findByOneCondition(strQuery, "username", user.getUsername());
        
    }
    
    public void createCustomer() {
        String strQuery = queryList.getQueryStr("findUserByUsername");

        if(transactionServices.findByOneCondition(strQuery, "username", customer.getUsername()) == null) {
            try {
                transactionServices.persistData(billingAddress);
                transactionServices.persistData(shippingAddress);

                String activationKey = UUID.randomUUID().toString();
                
                Customer c = new Customer(customer.getUsername(), customer.getPassword(), customer.getFirstName(), customer.getLastName(), customer.getPhone(), customer.getEmail(), false, activationKey);
                c.setBillingAddress(billingAddress);
                c.setShippingAddress(shippingAddress);
                c.setSupplier(supplier);
                transactionServices.persistData(c);
                
                //send mail here

                closeDialog();
                displayInfoMessageToUser("Created With Success");
                resetCustomer();
                loadCustomers();
            }
            catch(Exception e) {
                keepDialogOpen();
                displayErrorMessageToUser("Try again later, we could not create successfully");
                e.printStackTrace();
            }
        }
        else {
            keepDialogOpen();
            displayErrorMessageToUser("Please choose another username");
        }
    }
    
    public void updateCustomer() {
        try {
            transactionServices.updateData(billingAddress);
            transactionServices.updateData(shippingAddress);
            transactionServices.updateData(customer);
            closeDialog();
            displayInfoMessageToUser("Updated With Success");
            resetCustomer();
            loadCustomers();
        }
        catch(Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Try again later, we could not update successfully");
            e.printStackTrace();
        }
    }
    
    public void deleteCustomer() {
        try {
            transactionServices.deleteData(customer);
            closeDialog();
            displayInfoMessageToUser("Deleted With Success");
            resetCustomer();
            loadCustomers();
        }
        catch(Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Try again later, we could not delete successfully");
            e.printStackTrace();
        }
    }

    public ITransactionServices getTransactionServices() {
        return transactionServices;
    }

    public void setTransactionServices(ITransactionServices transactionServices) {
        this.transactionServices = transactionServices;
    }

    public QueryList getQueryList() {
        return queryList;
    }

    public void setQueryList(QueryList queryList) {
        this.queryList = queryList;
    }
    
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomers() {
        if(customer == null) {
            loadCustomers();
        }
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    
    private void loadCustomers()
    {
        String strQuery = queryList.getQueryStr("findCustomersBySupplier");
        HashMap hm = new HashMap();
        hm.put("supplier", supplier.getUsername());
        customers = transactionServices.findAllByCondition(strQuery, hm);
    }
    
    public void resetCustomer() {
        customer = new Customer();
        billingAddress = new BillingAddress();
        shippingAddress = new ShippingAddress();
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

}
