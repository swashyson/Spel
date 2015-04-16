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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mattias
 */
public class CharCreationController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private Button hero1;
    @FXML
    private Button hero2;
    @FXML
    private Button hero3;
    @FXML
    private Button create;
    @FXML
    private Button back;

    @FXML
    public void back(ActionEvent event) {
        SwitchScene sc = new SwitchScene();
        sc.change(event, "SelectOrCreate");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
