/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.filter;

import com.app.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author duy
 */
public class LoginFilter extends AbstractFilter implements Filter {
    private static List<String> allowedURIs;
    
    @Override
    public void init(FilterConfig fc) throws ServletException {
        if(allowedURIs == null){
            allowedURIs = new ArrayList<String>();
            allowedURIs.add(fc.getInitParameter("loginActionURI"));
            allowedURIs.add("/NewProject/pages/public/accessDenied.xhtml");
            //allowedURIs.add("/NewProject/pages/public/testPage.xhtml");
            //allowedURIs.add("/NewProject/javax.faces.resource/test.css.xhtml");
			//allowedURIs.add("/NewProject/javax.faces.resource/theme.css.xhtml");
			//allowedURIs.add("/NewProject/javax.faces.resource/primefaces.js.xhtml");
			//allowedURIs.add("/NewProject/javax.faces.resource/primefaces.css.xhtml");
			//allowedURIs.add("/NewProject/javax.faces.resource/jquery/jquery.js.xhtml");
        }
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpSession session = req.getSession();

	if (session.isNew()) {
            doLogin(request, response, req);
            return;
        }

	User user = (User) session.getAttribute("user");

	if (user == null && !allowedURIs.contains(req.getRequestURI())) {
            System.out.println(req.getRequestURI());
            doLogin(request, response, req);
            return;
	}

	chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
