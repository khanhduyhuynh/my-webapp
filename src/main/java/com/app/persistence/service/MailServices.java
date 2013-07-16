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
    public boolean sendMail(String to, String subject, String body) 
    {
        boolean check = true;
        try {
        Properties propsTLS = new Properties();
			propsTLS.put("mail.transport.protocol", "smtp");
			propsTLS.put("mail.smtp.host", "smtp.gmail.com");
			propsTLS.put("mail.smtp.auth", "true");
			propsTLS.put("mail.smtp.starttls.enable", "true"); // GMail requires STARTTLS

			Session sessionTLS = Session.getInstance(propsTLS);
			sessionTLS.setDebug(true);

			Message messageTLS = new MimeMessage(sessionTLS);
			messageTLS.setFrom(new InternetAddress("trialapp2084@gmail.com", "Duy Huynh"));
			messageTLS.setRecipients(Message.RecipientType.TO, InternetAddress.parse("khanhduyhuynhit@gmail.com")); // real recipient
			messageTLS.setSubject("Test mail using TLS");
			messageTLS.setText("This is test email sent to Your account using TLS.");

			Transport transportTLS = sessionTLS.getTransport();
			transportTLS.connect("smtp.gmail.com", 587, "trialapp2084@gmail.com", "trialapp"); // account used
			transportTLS.sendMessage(messageTLS, messageTLS.getAllRecipients());
			transportTLS.close();

			System.out.println("TLS done.");
			System.out.println("------------------------------------------------------------------------");

        }
        catch(Exception e) {
            check = false;
        }
        return check;
    }
 
    
}
