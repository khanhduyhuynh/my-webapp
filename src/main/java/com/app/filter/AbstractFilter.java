/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.filter;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author duy
 */
public class AbstractFilter {
    public AbstractFilter() {

    }

    protected void doLogin(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
	RequestDispatcher rd = req.getRequestDispatcher("/pages/public/login.xhtml");
	rd.forward(request, response);
    }
	
    protected void accessDenied(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
	RequestDispatcher rd = req.getRequestDispatcher("/pages/public/accessDenied.xhtml");
	rd.forward(request, response);
    }
}
