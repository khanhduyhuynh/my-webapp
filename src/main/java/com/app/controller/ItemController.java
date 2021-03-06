/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.model.Item;
import com.app.model.ItemSupplier;
import com.app.model.ItemSupplierKey;
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
    
    private ItemSupplier itemSupplier;
    private List<ItemSupplier> itemSuppliers = new ArrayList<ItemSupplier>();
    private List<ItemSupplier> otherItemSuppliers = new ArrayList<ItemSupplier>();
    
    private Supplier supplier;
    
    @PostConstruct
    public void init() {
        User user = userController.getUser();
        String strQuery = queryList.getQueryStr("findUserByUsername");

        supplier = (Supplier)transactionServices.findByOneCondition(strQuery, "username", user.getUsername());
        
        loadItems();
        loadOtherItems();
    }
    
    public void createItem() {
        String strQuery = queryList.getQueryStr("findItemByName");

        if(transactionServices.findByOneCondition(strQuery, "name", item.getName()) == null) {
            try {
                Item i = new Item(item.getName(), item.getDescription());
                transactionServices.persistData(i);
                
                ItemSupplierKey itemSupplierKey = new ItemSupplierKey(i, supplier);
                ItemSupplier itemSupplierObj = new ItemSupplier(itemSupplierKey, itemSupplier.getNumInStock(), itemSupplier.getPrice());
                transactionServices.persistData(itemSupplierObj);
                
                closeDialog();
                displayInfoMessageToUser("Created With Success");
                resetItem();
                loadItems();
                //loadOtherItems();
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
    
    public void createExistedItem() {
        ItemSupplierKey itemSupplierKey = new ItemSupplierKey(item, supplier);
        ItemSupplier itemSupplierObj = new ItemSupplier(itemSupplierKey, itemSupplier.getNumInStock(), itemSupplier.getPrice());
        transactionServices.persistData(itemSupplierObj);
        
        displayInfoMessageToUser("Created With Success");
        resetItem();
        loadItems();
        loadOtherItems();
    }
    
    public void updateItem() {
        try {
            transactionServices.updateData(item);
            transactionServices.updateData(itemSupplier);
            
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
            //transactionServices.deleteData(item);
            transactionServices.deleteData(itemSupplier);
            closeDialog();
            displayInfoMessageToUser("Deleted With Success");
            resetItem();
            loadItems();
            loadOtherItems();
        }
        catch(Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Try again later, we could not delete successfully");
            e.printStackTrace();
        }
    }
    
    private void loadItems() {
        String strQuery = queryList.getQueryStr("findItemSuppliersBySupplier");
        HashMap hm = new HashMap();
        hm.put("supplierId", supplier.getId());
        itemSuppliers = transactionServices.findAllByCondition(strQuery, hm);
        
        items.clear();
        strQuery = queryList.getQueryStr("findItemById");
        for(ItemSupplier item_supplier : itemSuppliers) {
            Item itemObj = (Item)transactionServices.findByOneCondition(strQuery, "itemId", item_supplier.getItemSupplierKey().getItem().getId());
            items.add(itemObj);
        }
    }
    
    private void loadOtherItems() {
        otherItemSuppliers.clear();
        
        String strQuery = queryList.getQueryStr("findOtherItemSuppliers");
        HashMap hm = new HashMap();
        hm.put("supplierId", supplier.getId());
        List<ItemSupplier> lsItemSuppliers = transactionServices.findAllByCondition(strQuery, hm);
        for(int i = 0; i < lsItemSuppliers.size(); i++) {
            ItemSupplier itemSupplierObj = lsItemSuppliers.get(i);
            itemSupplierObj.setNumInStock(0);
            itemSupplierObj.setPrice(0.0);
            otherItemSuppliers.add(itemSupplierObj);
        }
    }
    
    public void resetItem() {
        item = new Item();
        itemSupplier = new ItemSupplier();
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
    
    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public ItemSupplier getItemSupplier() {
        return itemSupplier;
    }

    public void setItemSupplier(ItemSupplier itemSupplier) {
        this.itemSupplier = itemSupplier;
    }

    public List<ItemSupplier> getItemSuppliers() {
        return itemSuppliers;
    }

    public void setItemSuppliers(List<ItemSupplier> itemSuppliers) {
        this.itemSuppliers = itemSuppliers;
    }

    public List<ItemSupplier> getOtherItemSuppliers() {
        return otherItemSuppliers;
    }

    public void setOtherItemSuppliers(List<ItemSupplier> otherItemSuppliers) {
        this.otherItemSuppliers = otherItemSuppliers;
    }

}
