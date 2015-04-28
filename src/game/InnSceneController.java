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

    private int currentHealth;
    private int maxHealth;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentHealth = DataStorage.getInstance().getHeroCurrentHP();
        maxHealth = DataStorage.getInstance().getHeroBaseHP();
        // kan bugga något
        // behöver nog någon sorts timer för att uppdatera kontinuerligt
        // tills max health är uppnådd
        if (currentHealth < maxHealth) {
            currentHealth++;
            health.setText(currentHealth + " / " + maxHealth);
        } else {
            health.setText(currentHealth + " / " + maxHealth);
        }
    }

    public void back(ActionEvent event) {
        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");
    }

    public void restoreHealthpoints(ActionEvent event) {
        // dessa rader kan bugga, iom att vi inte har
        // provat att använda mindre currentHP jämfört
        // med maxHP
        if (currentHealth < maxHealth) {
            currentHealth = maxHealth;
            health.setText(currentHealth + " / " + maxHealth);
            System.out.println("Current health restored to maximum.");
        }
        else if(currentHealth == maxHealth){
            System.out.println("Current health already restored to maximum");
        }
    }
}
