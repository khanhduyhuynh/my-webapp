/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.filter;

import com.app.model.User;
import com.app.persistence.service.ITransactionServices;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author duy
 */
public class ConfirmFilter extends AbstractFilter implements Filter {
    WebApplicationContext springContext;

    public void init(FilterConfig fc) throws ServletException {
        springContext = WebApplicationContextUtils.getWebApplicationContext(fc.getServletContext());
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        try {
            ITransactionServices transactionServices = (ITransactionServices)springContext.getBean("TransactionServices");
                
            HttpServletRequest req = (HttpServletRequest) request;
            String username = req.getParameter("username");
            String activationKey = req.getParameter("activationKey");
            
            HashMap hm = new HashMap();
            hm.put("username", username);
            hm.put("activationKey", activationKey);
            
            User user = (User)transactionServices.findByManyConditions("select u from User u where u.username = :username and u.activationKey = :activationKey", hm);
        
            if(user != null) {
                user.setActivationStatus(true);
                transactionServices.updateData(user);
            }
            else {
                System.out.print("noooooo");
            }
        }
        catch(Exception e)
        {
            System.out.print("Wrong casting");
        }   
    }

    public void destroy() {
        
    }
    
}
