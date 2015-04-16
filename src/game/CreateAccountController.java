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

    private String typeName;
    private String typePassword;
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
            Statement st = c.createStatement();

            typeName = name.getText();
            typePassword = password.getText();

            ResultSet RS = st.executeQuery("select max(userID) from game.login");

            while (RS.next()) {
                surrogateKey = RS.getInt("max(userID)");
                surrogateKey = surrogateKey + 1;
                System.out.println(surrogateKey);
            }

            System.out.println("INSERT INTO game.login (userID, userName, userPassword)" + " VALUES('" + surrogateKey + "','" + typeName + "','" + typePassword + "')");
            st.executeUpdate("INSERT INTO game.login (userID, userName, userPassword)" + " VALUES(" + surrogateKey + ",'" + typeName + "','" + typePassword + "')");

            System.out.println("Account skapat");
            DBConnect.close();

        } catch (SQLException ex) {
            System.out.println("AnvädarNamnet redan taget");
        }

    }

    public void back(ActionEvent event) {

        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root;
            root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("test");
        }
    }

}
