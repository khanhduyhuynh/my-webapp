/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.model.Address;
import com.app.model.Profile;
import com.app.model.Supplier;
import com.app.persistence.service.ITransactionServices;
import com.app.persistence.service.QueryList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private QueryList queryList;
    
    private Supplier supplier;
    private List<Supplier> suppliers = new ArrayList<Supplier>();
    
    private Profile profile;
    private Address address;
    
    public void createSupplier() {
        String strQuery = queryList.getQueryStr("findUserByUsername");
        HashMap hm = new HashMap();
        hm.put("username", supplier.getUsername());
        if(transactionServices.findByCondition(strQuery, hm) == null) {
            try {
                transactionServices.persistData(address);
                profile.setAddress(address);
                transactionServices.persistData(profile);

                Supplier s = new Supplier(supplier.getUsername(), supplier.getPassword(), supplier.getFirstName(), supplier.getLastName(), supplier.getPhone(), supplier.getEmail());
                s.setProfile(profile);
                transactionServices.persistData(s);

                closeDialog();
                displayInfoMessageToUser("Created With Success");
                loadSuppliers();
                resetSupplier();
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

    public QueryList getQueryList() {
        return queryList;
    }

    public void setQueryList(QueryList queryList) {
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

}
