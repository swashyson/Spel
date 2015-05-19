/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import DataStorage.HeroDataStorage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class InnSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //@FXML
    //private Button restoreHitpoint;
    //@FXML
    //private Button restoreHealth;
    //@FXML
    //private Button back;
    @FXML
    private Label health;

    @FXML
    private Label fel;

    private int currentHealth;
    private int maxHealth;
    
    private int heroGold;
    private int restoreHealthCost = 15; //enkelt att modifiera senare...

    private Timeline timeLine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentHealth = HeroDataStorage.getInstance().getHero().getHeroCurrentHP();
        maxHealth = HeroDataStorage.getInstance().getHero().getHp();
        heroGold = HeroDataStorage.getInstance().getHero().getGold();

        System.out.println("Amount of gold: " + heroGold);
        
        health.setText(currentHealth + " / " + maxHealth);

        //tiden för att HP:n ska gå mot max är något som kan modifieras senare
        timeLine = new Timeline(new KeyFrame(Duration.millis(1000), ae -> handleTime()));
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }

    public void handleTime() {
        if (currentHealth < maxHealth) {
            currentHealth++;
            health.setText(currentHealth + " / " + maxHealth);
            System.out.println("Current health: " + currentHealth + " / " + maxHealth);
            HeroDataStorage.getInstance().getHero().setHeroCurrentHP(currentHealth);
            System.out.println("current health in InnSceneController: " + currentHealth);
            saveHPToDataBase();
            
        } else if (currentHealth == maxHealth) {
            currentHealth = maxHealth;
            health.setText(currentHealth + " / " + maxHealth);
            System.out.println("Current health restored to maximum");
            timeLine.stop();
            saveHPToDataBase();
            fel.setText("Your health is now full.");
        }
    }

    public void back(ActionEvent event) {
        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");
    }

    public void restoreHealthpointsNow(ActionEvent event) {
        if (currentHealth < maxHealth /*&& heroGold > restoreHealthCost*/) {
            if (heroGold < restoreHealthCost) {
                //Möjligtvis switcha bilder på knappen istället för att ändra en label.
                fel.setText("Not enough money...");
            } else {
                currentHealth = maxHealth;
                heroGold = heroGold - restoreHealthCost;
                health.setText(currentHealth + " / " + maxHealth);
                saveHPToDataBase();
                System.out.println("Current health restored to maximum.");
            }
        } else if (currentHealth == maxHealth) {
            System.out.println("Current health already restored to maximum");
            fel.setText("Your health is now full.");
        }
    }

    public void saveHPToDataBase() {

        int heroID = HeroDataStorage.getInstance().getHero().getHeroID();

        DBConnect.connect();
        try {
            DBConnect.CreateAlterStatement("UPDATE `game`.`hero` SET `heroGold`='" + heroGold + "', `heroCurrentHP`='" + currentHealth + "' WHERE `idHero`='" + heroID + "';");
            System.out.println("UPDATE `game`.`hero` SET `heroGold`='" + heroGold + "', `heroCurrentHP`='" + currentHealth + "' WHERE `idHero`='" + heroID + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBConnect.close();
    }
}
