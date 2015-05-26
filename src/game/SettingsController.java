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
    @FXML
    private CheckBox cBSoundOn;

    private final ConfigFile cF = new ConfigFile();
    
    SoundManager soundManager = new SoundManager();
    private String buttonClick = "button_click";
    
    @FXML
    private void goBack(ActionEvent event) {
        if (HeroDataStorage.getInstance().getHero() == null) {
            soundManager.defineSound(buttonClick);
            SwitchScene sc = new SwitchScene();
            sc.change(event, "Login");
        } else {
            soundManager.defineSound(buttonClick);
            SwitchScene sc = new SwitchScene();
            sc.change(event, "Menu");
        }

    }

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         HoverMouse.getInstance().inHover(backButton);
        HoverMouse.getInstance().outHover(backButton);
        
        loadSettings();
    }

    public void loadSettings() {
        cF.readConfigFile();
        if (cF.getSound() == 1) {
            cBSoundOn.selectedProperty().set(true);
        } else if (cF.getSound() == 0) {
            cBSoundOn.selectedProperty().set(false);
            System.err.println(cF.getSound());
        } else {
            cBSoundOn.selectedProperty().set(true);
        }
    }

    public void changeSoundSettings() {
        if (cBSoundOn.selectedProperty().get() == true) {
            cF.setSound(1);
        } else if (cBSoundOn.selectedProperty().get() == false) {
            cF.setSound(0);
        }
    }
}
