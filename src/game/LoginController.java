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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private TextField name;
    @FXML
    private TextField password;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource().equals(login)) {
            
            logIn(event);
            
        } else if (event.getSource().equals(createAccount)) {
            
            createAccount(event);

        }
    }

    public void logIn(ActionEvent event) {

        try {
            DBConnect.connect();
            Connection c = DBConnect.getConnection();

            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select * from game.login");
            while (rs.next()) {
                String name1 = rs.getString("userName");
                String password1 = rs.getString("userPassword");
                if (name.getText().equals(name1) && password.getText().equals(password1)) {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectOrCreate.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("Accepted");
                    
                    DBConnect.close();

                }
            }
        } catch (Exception ex) {

        }
    }
    public void createAccount(ActionEvent event) {

        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
