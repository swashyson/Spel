/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Jonni
 */
public class SendMail extends Authenticator{

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public void Send(String email, String pword) throws Exception {
        
        try {
        // Get system properties
        String adress = "LandOfThiralia@gmail.com";
        String pass = "Thiralia";
        Properties props = System.getProperties();

        // Setup mail server
        props.put("mail.smtp.user", adress);
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session;
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(adress, pass);
            }
        }
        );
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(adress));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Password Retrival");
        message.setText("You'r password are " + pword);

        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", 465, adress, pass); //host, 25, "myemailhere", "mypasshere");
        message.saveChanges();
        transport.sendMessage(message, message.getAllRecipients());
        //Transport.send(message);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
