/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.model.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author duy
 */
@SessionScoped
@ManagedBean(name="userController")
public class UserController {
    public static final String INJECTION_NAME = "#{userController}";
    
    private User user;

    public boolean isAdmin() {
	return user.isAdmin();
    }

    public boolean isSupplier() {
	return user.isSupplier();
    }
    
    public boolean isCustomer() {
	return user.isCustomer();
    }

    public String logOut() {
	getRequest().getSession().invalidate();
	return "/pages/public/login.xhtml?faces-redirect=true";
    }

    private HttpServletRequest getRequest() {
	return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }
}

