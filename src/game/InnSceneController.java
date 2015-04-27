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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Mohini
 */
public class InnSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button restoreHealth;
    
    @FXML
    private Button back;
    
    @FXML
    private Label health;
    
    private int currentHealth;
    private int maxHealth;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void back(){
        
    }
    
    public void restoreHealth(){
        
    }
    
}
