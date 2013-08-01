/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controller;

import com.app.util.JSFMessageUtil;
import org.primefaces.context.RequestContext;

/**
 *
 * @author duy
 */
public class AbstractController {
    private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

    public AbstractController() {

    }

    protected void displayErrorMessageToUser(String message) {
	JSFMessageUtil messageUtil = new JSFMessageUtil();
	messageUtil.sendErrorMessageToUser(message);
    }
	
    protected void displayInfoMessageToUser(String message) {
	JSFMessageUtil messageUtil = new JSFMessageUtil();
	messageUtil.sendInfoMessageToUser(message);
    }
	
    protected void closeDialog(){
    	getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
    }
	
    protected void keepDialogOpen(){
	getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
    }
	
    protected RequestContext getRequestContext(){
    	return RequestContext.getCurrentInstance();
    }
}
