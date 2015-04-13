/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private void handleButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        try {
            if (event.getSource().equals(create)) {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                
                
                // SKRIV IN ER DATABAS HÄR, MIN HETER game med user som root och password som root  //
                
                String URL = "jdbc:mysql://127.0.0.1:3306/game?user=root&password=root";
                Connection c = DriverManager.getConnection(URL);
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
                c.close();
            } else if (event.getSource().equals(back)) {

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

        } catch (SQLException ex) {
            //ex.printStackTrace();
            System.out.println("Anvädarnamnet är redan taget");
        }
    }

}
