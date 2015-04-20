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

/**
 * FXML Controller class
 *
 * @author Mattias
 */
public class ViewCharController implements Initializable {

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
