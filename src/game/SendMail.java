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
public class SendMail {
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
         private static final String SMTP_PORT = "465";
          private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    public void Send(String email,String pword)throws Exception{
            // Get system properties
       
        boolean debug = true;
        Properties props = System.getProperties();

        // Setup mail server
        props.put("mail.smtp.user", "landofthiralia@gmail.com");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        //props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
            Session session;
            session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("LandOfThiralia@gmail.com", "Thiralia");
                }}
                    
                    );
            session.setDebug(debug);
        
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("LandOfThiralia@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Password Retrival");
        message.setText("You'r password are "+ pword);
        
        Transport transport = session.getTransport("smtps");
        transport.connect(SMTP_HOST_NAME, 465, "LandOfThiralia@gmail.com", "Thiralia");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        //Transport.send(message);
        }
}
    
    
        
    
