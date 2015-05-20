/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import DataStorage.*;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author Johan Nilsson
 */


public class SettingsController implements Initializable {
    @FXML
    private Button backButton;
    private CheckBox cBSondOn;
   
    @FXML
    private void goBack(ActionEvent event){
        if(HeroDataStorage.getInstance().getHero() == null){
            SwitchScene sc = new SwitchScene();
            sc.change(event, "Login");
        }else{
            SwitchScene sc = new SwitchScene();
            sc.change(event, "Menu");
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
