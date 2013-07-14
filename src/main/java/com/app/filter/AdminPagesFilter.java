/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.filter;

import com.app.model.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author duy
 */
public class AdminPagesFilter extends AbstractFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
	User user = (User) req.getSession(true).getAttribute("user");

	if (!user.isSupplier()) {
            accessDenied(request, response, req);
            return;
	}

	chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
    
}
