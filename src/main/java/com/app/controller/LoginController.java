/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.model.Admin;
import com.app.model.User;
import com.app.persistence.service.ILoginServices;
import com.app.persistence.service.ITransactionServices;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author duy
 */
@ManagedBean(name="loginController")
@RequestScoped
public class LoginController {
    
    @ManagedProperty(value="#{LoginServices}")
    private ILoginServices loginServices;

    private String username;
    private String password;
    
    public String validateLogin() {

        User user = loginServices.validateLogin(username, password);
	if(user != null){
           return "success";   
	}
        
	return null;
    }
   
    public ILoginServices getLoginServices() {
        return loginServices;
    }

    public void setLoginServices(ILoginServices loginServices) {
        this.loginServices = loginServices;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
