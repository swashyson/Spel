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

    SoundManager soundManager = new SoundManager(); // tar hand om alla ljud i spelet
    private String purchaseSound = "purchase";
    private String buttonClick = "button_click";

    private ConfigFile config = new ConfigFile();

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

        } else if (currentHealth == maxHealth) {
            currentHealth = maxHealth;
            health.setText(currentHealth + " / " + maxHealth);
            System.out.println("Current health restored to maximum");
            timeLine.stop();
            HeroDataStorage.getInstance().getHero().setHeroCurrentHP(currentHealth);
            fel.setText("Your health is now full.");
        }
    }

    public void back(ActionEvent event) {

        if (config.getSound() == 1) {
            soundManager.defineShortSound(buttonClick);
        }

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");
        HeroDataStorage.getInstance().getHero().setHeroCurrentHP(currentHealth);

        timeLine.stop();
    }

    public void restoreHealthpointsNow(ActionEvent event) {
        if (currentHealth < maxHealth) {
            if (config.getSound() == 1) {
                soundManager.defineShortSound(buttonClick);
            }
            if (heroGold < restoreHealthCost) {
                //Möjligtvis switcha bilder på knappen istället för att ändra en label.
                fel.setText("Not enough money...");
            } else {
                currentHealth = maxHealth;
                heroGold = heroGold - restoreHealthCost;
                health.setText(currentHealth + " / " + maxHealth);
                HeroDataStorage.getInstance().getHero().setHeroCurrentHP(currentHealth);
                System.out.println("Current health restored to maximum.");

                if (config.getSound() == 1) {
                    soundManager.defineShortSound(purchaseSound);
                }
            }
        } else if (currentHealth == maxHealth) {
            System.out.println("Current health already restored to maximum");
            fel.setText("Your health is now full.");
        }
    }
}
