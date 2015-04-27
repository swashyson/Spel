/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mattias
 */
public class CreateAccountController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField password;
    @FXML
    private Button create;
    @FXML
    private Button back;
    @FXML
    private TextField mail;

    private String typeName;
    private String typePassword;
    private String typeMail;
    private int surrogateKey = 5;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource().equals(create)) {

            create(event);

        } else if (event.getSource().equals(back)) {

            back(event);

        }

    }

    public void create(ActionEvent event) {

        try {

            DBConnect.connect();
            Connection c = DBConnect.getConnection();

            typeName = name.getText();
            typePassword = password.getText();
            typeMail = mail.getText();

            ResultSet RS = DBConnect.CreateSelectStatement("select max(userID) from game.login");

            while (RS.next()) {
                surrogateKey = RS.getInt("max(userID)");
                surrogateKey = surrogateKey + 1;
                System.out.println(surrogateKey);
            }

            System.out.println("INSERT INTO game.login (userID, userName, userPassword, userEmail)" + " VALUES('" + surrogateKey + "','" + typeName + "','" + typePassword + "','" + typeMail + "')");
            DBConnect.CreateInsertStatement("INSERT INTO game.login (userID, userName, userPassword, userEmail)" + " VALUES('" + surrogateKey + "','" + typeName + "','" + typePassword + "','" + typeMail + "')");

            System.out.println("Account skapat");
            DBConnect.close();

        } catch (SQLException ex) {
            System.out.println("Anvädarnamnet redan taget");
            ex.printStackTrace();
        }

    }

    public void back(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Login");
    }

    public void hoverIN(MouseEvent event) {

        if (event.getSource().equals(create)) {
            HoverMouse.inHover(create);
        } else if (event.getSource().equals(back)) {
            HoverMouse.inHover(back);
        }
    }

    public void hoverOUT(MouseEvent event) {

        if (event.getSource().equals(create)) {
            HoverMouse.outHover(create);
        } else if (event.getSource().equals(back)) {
            HoverMouse.outHover(back);
        }
    }

}
