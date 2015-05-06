/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.sun.deploy.util.SessionProperties;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * FXML Controller class
 *
 * @author Mattias
 */
public class ForgotPWController implements Initializable {

    @FXML
    private Button getItBack;
    @FXML
    private Button back;
    @FXML
    private TextField mail;
    @FXML
    private TextField name;

    String userMail;
    String username;
    String userPassword;
    SendMail s;

    @FXML
    public void getItBack(ActionEvent event) throws Exception {
        this.s = new SendMail();
//        try {
//            DBConnect.connect();
//            Connection c = DBConnect.getConnection();
//            Statement st = c.createStatement();
//            ResultSet rs = st.executeQuery("select * from game.login where userName = '" + name.getText() +"'");
//            if(rs.getRow()==0){
//                System.out.print("Finns ingen användare med namnet " + name.getText());
//            }
//            else if(rs.getRow() == 1){    
//         
//                
//                    userMail = rs.getString("userEmail");
//                    userPassword = rs.getString("userPassword");
//                    
//                System.out.print("har hämtat"+ userMail + " och "+ userPassword);
//                s.SendMail(userMail,userPassword);
//                System.out.print("funkade");
//            }
//            else{
//                System.out.print("Fler än 2 rade");
//            }
//            DBConnect.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        s.Send("johancn83@gmail.com", "blabla");
    }

   //** public void sendMail() {
    ////////////////////Man måste ha en Mail hanterar program för att få det till att funka... detta var ju lätt...///////////////////////////
    //////////////////// Behöver två nya libraries///// JAVAX-MAIL-API-1.5.3.jar/////////JAVAX.MAIL-1.5.3.jar////////////
//        try {
//
//            String to = typegetItBack;
//            String from = "gul_homer@hotmail.com";
//            String host = "localhost";
//            Properties properties = System.getProperties();
//            properties.setProperty("mail.smtp.host", host);
//            Session session = Session.getDefaultInstance(properties);
//
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(to));
//            message.setSubject("Password Reset");
//            message.setContent("<h1>Ditt lösenord är blalba</h1>",
//                    "text/html");
//            Transport.send(message);
//            System.out.println("Medelande skickat!");
//
//        } catch (MessagingException ex) {
//            ex.printStackTrace();
//        }
//    }
    @FXML
    public void back(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Login");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
