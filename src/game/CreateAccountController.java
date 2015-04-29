/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
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
    @FXML
    private Label fel;

    private String typeName;
    private String typePassword;
    private String typeMail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HoverMouse.inHover(create);
        HoverMouse.outHover(create);
        HoverMouse.inHover(back);
        HoverMouse.outHover(back);

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource().equals(create)) {

            create(event);

        } else if (event.getSource().equals(back)) {

            back(event);

        }

    }

    public void create(ActionEvent event)  {

        DBConnect.connect();
        typeName = name.getText();
        typePassword = password.getText();
        typeMail = mail.getText();
        if (!typeMail.contains("@")) {
            
            fel.setText("Not a valid mail");
            return;
        }
        System.out.println("INSERT INTO game.login (userName, userPassword, userEmail)" + " VALUES('" + typeName + "','" + typePassword + "','" + typeMail + "')");
        DBConnect.CreateInsertStatement("INSERT INTO game.login (userName, userPassword, userEmail)" + " VALUES('" + typeName + "','" + typePassword + "','" + typeMail + "')", fel, "User already exists");
        System.out.println("Account skapat");
        DBConnect.close();

    }

    public void back(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Login");
    }

}
