/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

import com.app.model.User;

/**
 *
 * @author duy
 */
public interface ILoginServices {
    public void initData();
    public User validateLogin(String username, String password);
}
