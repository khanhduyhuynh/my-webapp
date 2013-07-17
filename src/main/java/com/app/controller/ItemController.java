/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.model.Item;
import com.app.model.Supplier;
import com.app.model.User;
import com.app.persistence.service.IQueryList;
import com.app.persistence.service.ITransactionServices;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author duy
 */
@ManagedBean(name="itemController")
@ViewScoped
public class ItemController extends AbstractController {
    @ManagedProperty(value="#{TransactionServices}")
    private ITransactionServices transactionServices;
    
    @ManagedProperty(value="#{QueryList}")
    private IQueryList queryList;
    
    @ManagedProperty(value=UserController.INJECTION_NAME)
    private UserController userController;
    
    private Item item;
    private List<Item> items = new ArrayList<Item>();
    
    private Supplier supplier;
    
    @PostConstruct
    public void init() {
        User user = userController.getUser();
        String strQuery = queryList.getQueryStr("findUserByUsername");

        supplier = (Supplier)transactionServices.findByOneCondition(strQuery, "username", user.getUsername());
        
    }
    
    public void createItem() {
        String strQuery = queryList.getQueryStr("findItemByName");

        if(transactionServices.findByOneCondition(strQuery, "name", item.getName()) == null) {
            try {
                Item i = new Item(item.getName(), item.getDescription());
                i.getSuppliers().add(supplier);
                transactionServices.persistData(i);

                closeDialog();
                displayInfoMessageToUser("Created With Success");
                resetItem();
                loadItems();
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
    
    public void updateItem() {
        try {
            transactionServices.updateData(item);
            closeDialog();
            displayInfoMessageToUser("Updated With Success");
            resetItem();
            loadItems();
        }
        catch(Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Try again later, we could not update successfully");
            e.printStackTrace();
        }
    }
    
    public void deleteItem() {
        try {
            transactionServices.deleteData(item);
            closeDialog();
            displayInfoMessageToUser("Deleted With Success");
            resetItem();
            loadItems();
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
    
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItems() {
        if(item == null) {
            loadItems();
        }
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    private void loadItems()
    {
        String strQuery = queryList.getQueryStr("findItemsBySupplier");
        HashMap hm = new HashMap();
        hm.put("supplier", supplier.getUsername());
        items = transactionServices.findAllByCondition(strQuery, hm);
    }
    
    public void resetItem() {
        item = new Item();
    }

    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

}
