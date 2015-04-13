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

        try {
            if (event.getSource().equals(login)) {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                // String URL = "jdbc:mysql://194.47.47.18:3306/YOUR_DATABASE_NAME?user=YOUR_USER_NAME&password=YOUR_PASSWORD";
                
                
                // SKRIV IN ER DATABAS HÄR, MIN HETER game med user som root och password som root  //
                String URL = "jdbc:mysql://127.0.0.1:3306/game?user=root&password=root";
                Connection c = DriverManager.getConnection(URL);

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

                    } else {
                        label.setText("Fel inloggning");
                    }
                }
                c.close();
            }
            if (event.getSource().equals(createAccount)) {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception ex) {
            System.err.println("Fel: " + ex);
        
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
