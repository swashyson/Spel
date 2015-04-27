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
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
            ResultSet rs = DBConnect.CreateSelectStatement("select * from game.login where login.userName =  '" + name.getText() + "' and login.userPassword = '" + password.getText() + "'");
            //System.out.println("select * from game.login where login.userName =  '" + name.getText() + "' and login.userPassword = '" + password.getText() + "'");

            if (rs.first()) {

                int ID;
                SwitchScene sc = new SwitchScene();
                sc.change(event, "SelectOrCreate");
                ID = rs.getInt("userID");
                DataStorage.getInstance().setUserID(ID);

                DBConnect.close();
            } else {
                System.out.println("Fel användarnamn/lösenord");
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

    @FXML
    public void hoverIN(MouseEvent event) {

        if (event.getSource().equals(createAccount)) {
            HoverMouse.inHover(createAccount);
        } else if (event.getSource().equals(login)) {
            HoverMouse.inHover(login);
        } else if (event.getSource().equals(forgot)) {
            HoverMouse.inHover(forgot);
        }

    }

    @FXML
    public void hoverOUT(MouseEvent event) {

        if (event.getSource().equals(createAccount)) {
            HoverMouse.outHover(createAccount);
        } else if (event.getSource().equals(login)) {
            HoverMouse.outHover(login);
        } else if (event.getSource().equals(forgot)) {
            HoverMouse.outHover(forgot);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
