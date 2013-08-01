/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.persistence.service;

/**
 *
 * @author duy
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailServices 
{
 
    /**
     * This method will send compose and send the message 
     * */
    public boolean sendMail(String to, String username, String activationKey) 
    {
        boolean check = true;
        try {
            String subject = "Please confirm account";
            
            String body = "Hello there, " + "\n\n" + "Please click on the below link to activate / confirm your account."    
                          + "\n\n"   
                          + "Please confirm: http://localhost:8080/NewProject/pages/public/confirmAccount.xhtml?username=" + username + "&activationKey=" + activationKey   
                          + "\n\n"  
                          + "Thank you...";
            
            Properties propsTLS = new Properties();
            propsTLS.put("mail.transport.protocol", "smtp");
            propsTLS.put("mail.smtp.host", "smtp.gmail.com");
            propsTLS.put("mail.smtp.auth", "true");
            propsTLS.put("mail.smtp.starttls.enable", "true"); // GMail requires STARTTLS

            Session sessionTLS = Session.getInstance(propsTLS);
            sessionTLS.setDebug(true);

            Message messageTLS = new MimeMessage(sessionTLS);
            messageTLS.setFrom(new InternetAddress("trialapp2084@gmail.com", "Duy Huynh"));
            messageTLS.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // real recipient
            messageTLS.setSubject(subject);
            messageTLS.setText(body);

            Transport transportTLS = sessionTLS.getTransport();
            transportTLS.connect("smtp.gmail.com", 587, "trialapp2084@gmail.com", "trialapp"); // account used
            transportTLS.sendMessage(messageTLS, messageTLS.getAllRecipients());
            transportTLS.close();
        }
        catch(Exception e) {
            check = false;
        }
        return check;
    }
 
    
}
