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
<<<<<<< HEAD
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
=======
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
>>>>>>> 3c849adb0561eb95902fd0446094045dde6c0a28
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author duy
 */
@ManagedBean(name="loginController")
@SessionScoped
public class LoginController extends AbstractController {
    
    @ManagedProperty(value="#{LoginServices}")
    private ILoginServices loginServices;
    
    @ManagedProperty(value=UserController.INJECTION_NAME)
    private UserController userController;

    private String username;
    private String password;
    
    @PostConstruct
    public void init(){
        loginServices.initData();
    }

    public String validateLogin() {
        User user = loginServices.validateLogin(username, password);
	if(user != null) {
            if(user.getActivationStatus()){
                userController.setUser(user);
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                request.getSession().setAttribute("user", user);

                if(user.isAdmin()) {
                    return "admin_forward";
                }
                if(user.isSupplier()) {
                    return "supplier_forward";
                }
                return null;
            }
            displayErrorMessageToUser("Your account has not been activated, please check your email");
            return null;
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
