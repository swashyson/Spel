/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


import Creature.Hero;
import Creature.Scorpion;
import Creature.Wolf;
import Creature.*;
import DataStorage.HeroDataStorage;
import DataStorage.EnemyDataStorage;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class FightController implements Initializable {

    @FXML
    Button backToCity;
    @FXML
    private ImageView XP;

    @FXML
    private AnchorPane pane;

    @FXML

    private int heroEXP;
    public int timerCounter = 0;
    public String[] fightOrder;
    private Hero heroChar;
    private Enemy enemy;
    private Enemy[] enemys;
    private Bear bear;
    private Scorpion scorpion;
    private Snake snake;
    private Spider spider;
    private Wolf wolf;
    AnchorPane creaturePane1;
    AnchorPane creaturePane2;
    AnchorPane creaturePane3;

    @FXML
    public void goToCity(ActionEvent event) {

        heroChar.heroTimeStop();

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        createEnemy();
        loadHeroStatsFromDataStorage();
        XPBAR();
        whatHeroToLoad();
        fight();
        

    }

    public void XPBAR() {

        XP.setScaleX(heroEXP);
        XP.setX(XP.getScaleX() / 2);

    }

    public void spawnCreature(String URL, int creaturePaneWitdh, int creaturePaneHeight, int creaturePaneX, int creaturePaneY) {

        ImageView creature = new ImageView();
        Image creatureDisplay = new Image(getClass().getResourceAsStream(URL));
        creature.setImage(creatureDisplay);
        
            createCreaturePane(creature, creaturePaneWitdh, creaturePaneHeight, creaturePaneX, creaturePaneY);
            pane.getChildren().add(creaturePane1);
            pane.getChildren().add(creaturePane2);
            pane.getChildren().add(creaturePane3);
        

    }

    public void loadHeroStatsFromDataStorage() {

        heroChar = HeroDataStorage.getInstance().getHero();
        heroEXP = HeroDataStorage.getInstance().getHero().getEXP();

    }

    public void loadEnemyStatsFromDataStorage() {

       bear = EnemyDataStorage.getInstance().getBear();
       scorpion = EnemyDataStorage.getInstance().getScorpion();
       snake = EnemyDataStorage.getInstance().getSnake();
       spider = EnemyDataStorage.getInstance().getspider();
       wolf = EnemyDataStorage.getInstance().getWolf();
    }

    public void fight() {
        heroChar.fightMonster(heroChar, enemy);
        heroChar.heroTimeStart();

    }

    public void createEnemy() {

        // Ska vara en random generator här beroende på vilken lvl osv man är
        
        Random rand = new Random();
        int numberCreature = rand.nextInt(2)+1;
        System.out.print("antal djur" + numberCreature);
        for(int i =0; i <numberCreature;i++){
            int whatCreature = rand.nextInt(4)+1;
            
            
                if(whatCreature == 1 ){
                    enemy = new Bear("Bear",1,1,1,1);
                }
                else if(whatCreature == 2 ){
                    enemy = new Scorpion("Scorpion",1,1,1,1);
                }
                else if(whatCreature == 3 ){
                    enemy = new Snake("Snake",1,1,1,1);
                }
                else if(whatCreature == 4 ){
                    enemy = new Spider("Spider",1,1,1,1);
                }
                else if(whatCreature == 5 ){
                    enemy = new Wolf("Wolf",1,1,1,1);
                }
                enemys[i] = enemy;
        }
        
        
    }

    public void createCreaturePane(ImageView creature, int creaturePaneWitdh, int creaturePaneHeight, int creaturePaneX, int creaturePaneY) {

        creaturePane1 =  new AnchorPane();
        creaturePane2 =  new AnchorPane();
        creaturePane3 =  new AnchorPane();
        for(int i = 0; i < enemys.length;i++){
        if(creaturePane1.getChildren().isEmpty()){
        creaturePane1.prefWidth(creaturePaneWitdh);
        creaturePane1.prefHeight(creaturePaneHeight); //Storlek på pane
        creaturePane1.setLayoutX(creaturePaneX);
        creaturePane1.setLayoutY(creaturePaneY);
        ImageView hpBarCreature1 = new ImageView();
        Image imageHealthCreature1 = new Image(getClass().getResourceAsStream("Recourses/HealthBar.png"));
        hpBarCreature1.setImage(imageHealthCreature1);

        creaturePane1.getChildren().add(hpBarCreature1);
        creaturePane1.getChildren().add(creature);

        hpBarCreature1.setScaleX(healthPaneScaler());
        hpBarCreature1.setX(healthPaneScaler() / 2);
        }
        else if(creaturePane2.getChildren().isEmpty()){
            
            creaturePane2.prefWidth(creaturePaneWitdh);
            creaturePane2.prefHeight(creaturePaneHeight); //Storlek på pane
            creaturePane2.setLayoutX(creaturePaneX);
            creaturePane2.setLayoutY(creaturePaneY+ 100);
            ImageView hpBarCreature2 = new ImageView();
            Image imageHealthCreature2 = new Image(getClass().getResourceAsStream("Recourses/HealthBar.png"));
            hpBarCreature2.setImage(imageHealthCreature2);

            creaturePane2.getChildren().add(hpBarCreature2);
            creaturePane2.getChildren().add(creature);

            hpBarCreature2.setScaleX(healthPaneScaler());
            hpBarCreature2.setX(healthPaneScaler() / 2);
        }
        else if(creaturePane3.getChildren().isEmpty()){
            creaturePane3.prefWidth(creaturePaneWitdh);
            creaturePane3.prefHeight(creaturePaneHeight); //Storlek på pane
            creaturePane3.setLayoutX(creaturePaneX);
            creaturePane3.setLayoutY(creaturePaneY+200);
            ImageView hpBarCreature3 = new ImageView();
            Image imageHealthCreature3 = new Image(getClass().getResourceAsStream("Recourses/HealthBar.png"));
            hpBarCreature3.setImage(imageHealthCreature3);

            creaturePane3.getChildren().add(hpBarCreature3);
            creaturePane3.getChildren().add(creature);

            hpBarCreature3.setScaleX(healthPaneScaler());
            hpBarCreature3.setX(healthPaneScaler() / 2);
        }
        }
    }

    public int healthPaneScaler() {

        int currentHP = HeroDataStorage.getInstance().getHero().getHeroCurrentHP();
        int maxHP = HeroDataStorage.getInstance().getHero().getHp();
        int maxImageView = 50;

        int calculate;

        calculate = (currentHP * maxImageView) / maxHP; // Fullt fungerande, bara till alla kalla metoden varje tick
        return calculate;
    }

    public void healthPaneSetPosX() {

        //Ska flytta scaleX hit från createCreaturePan
    }

    public void whatHeroToLoad() {

        if (HeroDataStorage.getInstance().getHero().getHeroType() == 1) {

            spawnCreature("Recourses/WarriorChar.png", 40, 60, 30, 500);

        } else if (HeroDataStorage.getInstance().getHero().getHeroType() == 2) {

            spawnCreature("Recourses/RangerChar.png", 40, 60, 30, 500);

        } else if (HeroDataStorage.getInstance().getHero().getHeroType() == 3) {

            spawnCreature("Recourses/MageChar.png", 40, 60, 30, 500);

        }

    }

}
