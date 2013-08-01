/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author duy
 */
public class JSFMessageUtil {
    public void sendInfoMessageToUser(String message) {
	FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message);
	addMessageToJsfContext(facesMessage);
    }

    public void sendErrorMessageToUser(String message) {
	FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_WARN, message);
	addMessageToJsfContext(facesMessage);
    }

    private FacesMessage createMessage(Severity severity, String mensagemErro) {
	return new FacesMessage(severity, mensagemErro, mensagemErro);
    }

    private void addMessageToJsfContext(FacesMessage facesMessage) {
	FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }    
}
