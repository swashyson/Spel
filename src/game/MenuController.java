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

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class MenuController implements Initializable {

    @FXML
    Button backToCity;
    Button logout;
    Button changeCharacter;
    Button settings;
    
    @FXML
    private Button mute;
    
    private boolean muteUnMute;
    
    SoundManager soundManager;

    @FXML
    public void goToCity(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");

    }

    @FXML
    public void logout(ActionEvent event) {

        DBConnect.saveToDB();
        SwitchScene sc = new SwitchScene();
        sc.change(event, "Login");

    }
    @FXML 
    public void settings(ActionEvent event){
        SwitchScene sc = new SwitchScene();
        sc.change(event, "Settings");
    }

    @FXML
    public void goToCharacter(ActionEvent event) {
        DBConnect.saveToDB();
        SwitchScene sc = new SwitchScene();
        sc.change(event, "ViewChar");

    }
    
    
    // ha kvar eller ta bort????
    @FXML
    public void muteUnMute(ActionEvent event) {
        
        muteUnMute = soundManager.getMuteSounds();
        System.out.println(muteUnMute);
        
        if(!muteUnMute){
//            muteUnMute = false;
//            System.out.println(muteUnMute);
//            soundManager.setMuteSounds(muteUnMute);
            soundManager.setMuteSounds(muteUnMute);
            System.out.println(soundManager);
        }
        else if(muteUnMute){
//            muteUnMute = true;
//            System.out.println(muteUnMute);
//            soundManager.setMuteSounds(muteUnMute);
            soundManager.setMuteSounds(!muteUnMute);
            System.out.println(muteUnMute);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
