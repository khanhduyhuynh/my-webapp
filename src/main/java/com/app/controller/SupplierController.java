/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.model.Address;
import com.app.model.Profile;
import com.app.model.Supplier;
import com.app.persistence.service.IQueryList;
import com.app.persistence.service.ITransactionServices;
import com.app.persistence.service.MailServices;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author duy
 */
@ManagedBean(name="supplierController")
@ViewScoped
public class SupplierController extends AbstractController {
    @ManagedProperty(value="#{TransactionServices}")
    private ITransactionServices transactionServices;
    
    @ManagedProperty(value="#{QueryList}")
    private IQueryList queryList;
    
    @ManagedProperty(value="#{MailServices}")
    private MailServices mailServices;
    
    private Supplier supplier;
    private List<Supplier> suppliers = new ArrayList<Supplier>();
    
    private Profile profile;
    private Address address;
    
    public void createSupplier() {
        String strQuery = queryList.getQueryStr("findUserByUsername");
        if(transactionServices.findByOneCondition(strQuery, "username", supplier.getUsername()) == null) {
            strQuery = queryList.getQueryStr("findSupplierByBusinessName");
            if(transactionServices.findByOneCondition(strQuery, "businessName", profile.getBusinessName()) == null) {
                strQuery = queryList.getQueryStr("findSupplierByTradingName");
                if(transactionServices.findByOneCondition(strQuery, "tradingName", profile.getTradingName()) == null) {
                    try {
                        transactionServices.persistData(address);
                        profile.setAddress(address);
                        transactionServices.persistData(profile);

                        String activationKey = UUID.randomUUID().toString();

                        Supplier s = new Supplier(supplier.getUsername(), supplier.getPassword(), supplier.getFirstName(), supplier.getLastName(), supplier.getPhone(), supplier.getEmail(), false, activationKey);
                        s.setProfile(profile);
                        transactionServices.persistData(s);

                        mailServices.sendMail(supplier.getEmail(), supplier.getUsername(), activationKey);

                        closeDialog();
                        displayInfoMessageToUser("Created With Success");
                        resetSupplier();
                        loadSuppliers();
                    }
                    catch(Exception e) {
                        keepDialogOpen();
                        displayErrorMessageToUser("Try again later, we could not create successfully");
                        e.printStackTrace();
                    }
                }
                else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Please choose another trading name");
                }
            }
            else {
                keepDialogOpen();
                displayErrorMessageToUser("Please choose another business name");
            }
        }
        else {
            keepDialogOpen();
            displayErrorMessageToUser("Please choose another username");
        }
    }
    
    public void updateSupplier() {
        try {
            transactionServices.updateData(profile);
            transactionServices.updateData(supplier);
            closeDialog();
            displayInfoMessageToUser("Updated With Success");
            resetSupplier();
            loadSuppliers();
        }
        catch(Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Try again later, we could not update successfully");
            e.printStackTrace();
        }
    }
    
    public void deleteSupplier() {
        try {
            transactionServices.deleteData(supplier);
            closeDialog();
            displayInfoMessageToUser("Deleted With Success");
            resetSupplier();
            loadSuppliers();
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

    public IQueryList getQueryList() {
        return queryList;
    }

    public void setQueryList(IQueryList queryList) {
        this.queryList = queryList;
    }
    
    
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Supplier> getSuppliers() {
        if(supplier == null) {
            loadSuppliers();
        }
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
    
    private void loadSuppliers()
    {
        String strQuery = queryList.getQueryStr("findAllSuppliers");
        suppliers = transactionServices.findAll(strQuery);
    }
    
    public void resetSupplier() {
        supplier = new Supplier();
        profile = new Profile();
        address = new Address();
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public MailServices getMailServices() {
        return mailServices;
    }

    public void setMailServices(MailServices mailServices) {
        this.mailServices = mailServices;
    }
}
