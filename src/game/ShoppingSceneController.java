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
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Mohini
 */
public class ShoppingSceneController implements Initializable {

    @FXML
    private ImageView equippedArmor;
    
    @FXML
    private ImageView equippedWeapon;
    
    @FXML
    private Button weapon1;
    
    @FXML
    private Button weapon2;
    
    @FXML
    private Button weapon3;
    
    @FXML
    private Button armor1;
    
    @FXML
    private Button armor2;
    
    @FXML
    private Button armor3;
    
    @FXML
    private Button back;
    
    @FXML
    private Button buy;
    
    public void back(ActionEvent event){
        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");
    }
    
    public void selectArmorOrWeapon(ActionEvent event){
        // adda alla vapen och rustningar här, så att du kan välja vilka du ska köpa
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
