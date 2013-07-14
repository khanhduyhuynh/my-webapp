/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.model.User;
import com.app.persistence.service.ILoginServices;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author duy
 */
@ManagedBean(name="loginController")
@RequestScoped
public class LoginController extends AbstractController {
    
    @ManagedProperty(value="#{LoginServices}")
    private ILoginServices loginServices;
    
    @ManagedProperty(value=UserController.INJECTION_NAME)
    private UserController userController;

    private String username;
    private String password;
    
    @PostConstruct
    public void init(){
        loginServices.createAdmin();
    }
    
    public String validateLogin() {
        User user = loginServices.validateLogin(username, password);
	if(user != null) {
           userController.setUser(user);
           FacesContext context = FacesContext.getCurrentInstance();
           HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
           request.getSession().setAttribute("user", user);
           return "success";   
	}
        displayErrorMessageToUser("Check your email/password");
	return null;
    }
   
    public ILoginServices getLoginServices() {
        return loginServices;
    }

    public void setLoginServices(ILoginServices loginServices) {
        this.loginServices = loginServices;
    }
    
    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
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
