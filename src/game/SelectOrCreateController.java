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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mattias
 */
public class SelectOrCreateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button create;
    @FXML
    private Button choose;
    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource().equals(create)) {

            switchScene(event, "CharCreation.fxml");

        } else if (event.getSource().equals(choose)) {

            switchScene(event, "ViewChar.fxml");

        } else if (event.getSource().equals(back)) {

            switchScene(event, "Login.fxml");

        }

    }

    public void switchScene(ActionEvent event, String Destination) {

        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Destination));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {

        }

    }

}
