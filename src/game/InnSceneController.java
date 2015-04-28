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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class InnSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button restoreHitpoint;
    
    @FXML
    private Button restoreHealth;
    
    @FXML
    private Button back;
    
    @FXML
    private Label health;

//    kunna restora hitpoints?
//    @FXML
//    private Label hitpoint;
    
    private int currentHealth;
    private int maxHealth;
    
    
//    kunna restora hitpoints?    
//    private int currentHitpoint;
//    private int maxHitpoint;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void back(ActionEvent event){
        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");
    }
    
//    kunna restora hitpoints?    
//    public void restoreHitpoint(ActionEvent event){
//        
//    }
    
    public void restoreHealthpoints(ActionEvent event){
        
    }
}
