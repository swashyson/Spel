/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
 
import java.awt.Font;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
 
/**
 *
 * @author Mattias
 */
 
 
public class LoginController implements Initializable {
 
    @FXML
    private Label label;
    @FXML
    private Button createAccount;
    @FXML
    private Button login;
    @FXML
    private Button forgot;
    @FXML
    private TextField name;
    @FXML
    private TextField password;
 
    public void logIn(ActionEvent event) {
 
        try {
            DBConnect.connect();
            Connection c = DBConnect.getConnection();
 
            //Statement st = c.createStatement();
            //ResultSet rs = st.executeQuery("select * from game.login");
            ResultSet rs = DBConnect.CreateSelectStatement("select * from game.login");
 
            while (rs.next()) {
                String name1 = rs.getString("userName");
                String password1 = rs.getString("userPassword");
                if (name.getText().equals(name1) && password.getText().equals(password1)) {
 
                    SwitchScene sc = new SwitchScene();
                    sc.change(event, "SelectOrCreate");
 
                    DBConnect.close();
                   
                    return;
 
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
    public void createAccount(ActionEvent event) {
 
        SwitchScene sc = new SwitchScene();
        sc.change(event, "CreateAccount");
    }
 
    public void forgot(ActionEvent event) {
 
        SwitchScene sc = new SwitchScene();
        sc.change(event, "ForgotPW");
 
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }
 
}