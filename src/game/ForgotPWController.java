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
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class ForgotPWController implements Initializable {
 
    private String question, answer;
 
    @FXML
    private Button getQuestion;
 
    @FXML
    private Button getPassword;
 
    @FXML
    private Button back;
 
    @FXML
    private TextField password;
 
    @FXML
    private TextField username;
 
    @FXML
    private TextField secretAnswer;
 
    @FXML
    private TextField secretQuestion;
 
    @FXML
    public void getQuestion(ActionEvent event) throws Exception {
           
       // INGEN FELHANTERING OM USER INTE FINNS! FUNKAR DOCK ÄNDÅ
        DBConnect.connect();
 
        ResultSet rs = DBConnect.CreateSelectStatement("select * from game.login where login.userName = '" + username.getText() + "'");
        while (rs.next()) {
 
 
                question = rs.getString("userQuestion");
                secretQuestion.setText(question + "?");
 
           
        }
        DBConnect.close();
 
    }
 
    @FXML
   
    public void getPassword(ActionEvent event) throws Exception {       // ANDRA RETURN KNAPPEN
        DBConnect.connect();
        ResultSet rs = DBConnect.CreateSelectStatement("select * from game.login where login.userName = '" + username.getText() + "'");
        while (rs.next()) {
 
            answer = rs.getString("userAnswer");
            String pass = rs.getString("userPassword");
 
            if (answer.equals(secretAnswer.getText())) {
 
                password.setText(pass);
 
            } else {
                secretAnswer.setText("WRONG!");
            }
 
        }
        DBConnect.close();
    }
 
    @FXML
    public void back(ActionEvent event) {       //BACK TO LOGIN
 
        SwitchScene sc = new SwitchScene();
        sc.change(event, "Login");
 
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
 
}
