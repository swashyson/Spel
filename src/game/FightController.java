/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


import Creature.*;
import DataStorage.*;
import java.awt.event.MouseListener;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class FightController implements Initializable,MouseListener {

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
    private Bear bear;
    private Scorpion scorpion;
    private Snake snake;
    private Spider spider;
    private Wolf wolf;
    AnchorPane creaturePane1;
    AnchorPane creaturePane2;
    AnchorPane creaturePane3;
    int attacking;

    @FXML
    public void goToCity(ActionEvent event) {

        heroChar.heroTimeStop();

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadEnemyStatsFromDataStorage();
        createEnemy();
        loadHeroStatsFromDataStorage();
        XPBAR();
        whatHeroToLoad();
        

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

       bear = EnemyBaseDataStorage.getInstance().getBear();
       scorpion = EnemyBaseDataStorage.getInstance().getScorpion();
       snake = EnemyBaseDataStorage.getInstance().getSnake();
       spider = EnemyBaseDataStorage.getInstance().getSpider();
       wolf = EnemyBaseDataStorage.getInstance().getWolf();
    }

    public void fight() {
        System.err.println("is pressed");
        if(creaturePane1.isPressed()){
            attacking = 1;
        } else if(creaturePane2.isPressed()){
            attacking = 2;
        } else if(creaturePane3.isPressed()){
            attacking = 3;
        }
        if(attacking != 0){
        heroChar.fightMonster(heroChar, attacking);
        }

    }

    public void createEnemy() {

        // Ska vara en random generator här beroende på vilken lvl osv man är
        String pic = "";
        Random rand = new Random();
        int numberCreature = rand.nextInt(2) + 1;
        System.out.print("antal djur" + numberCreature);
        for(int i =0; i <numberCreature;i++){
            int whatCreature = rand.nextInt(4)+1;
            
            
            if(whatCreature == 1 ){
                enemy = new Bear(EnemyBaseDataStorage.getInstance().getBear().getName(),EnemyBaseDataStorage.getInstance().getBear().getHp(),EnemyBaseDataStorage.getInstance().getBear().getMaxDmg(),EnemyBaseDataStorage.getInstance().getBear().getMinDmg(),EnemyBaseDataStorage.getInstance().getBear().getSpeed());
                pic = "Recourses/Bear.png";
            }
            else if(whatCreature == 2 ){
                enemy = new Scorpion(EnemyBaseDataStorage.getInstance().getScorpion().getName(),EnemyBaseDataStorage.getInstance().getScorpion().getHp(),EnemyBaseDataStorage.getInstance().getScorpion().getMaxDmg(),EnemyBaseDataStorage.getInstance().getScorpion().getMinDmg(),EnemyBaseDataStorage.getInstance().getScorpion().getSpeed());
                pic = "Recourses/Bear.png";
            }
            else if(whatCreature == 3 ){
                enemy = new Snake(EnemyBaseDataStorage.getInstance().getSnake().getName(),EnemyBaseDataStorage.getInstance().getSnake().getHp(),EnemyBaseDataStorage.getInstance().getSnake().getMaxDmg(),EnemyBaseDataStorage.getInstance().getSnake().getMinDmg(),EnemyBaseDataStorage.getInstance().getSnake().getSpeed());
                pic = "Recourses/Bear.png";
            }
            else if(whatCreature == 4 ){
                enemy = new Spider(EnemyBaseDataStorage.getInstance().getSpider().getName(),EnemyBaseDataStorage.getInstance().getSpider().getHp(),EnemyBaseDataStorage.getInstance().getSpider().getMaxDmg(),EnemyBaseDataStorage.getInstance().getSpider().getMinDmg(),EnemyBaseDataStorage.getInstance().getSpider().getSpeed());
                pic = "Recourses/Bear.png";
            }
            else if(whatCreature == 5 ){
                enemy = new Wolf(EnemyBaseDataStorage.getInstance().getWolf().getName(),EnemyBaseDataStorage.getInstance().getWolf().getHp(),EnemyBaseDataStorage.getInstance().getWolf().getMaxDmg(),EnemyBaseDataStorage.getInstance().getWolf().getMinDmg(),EnemyBaseDataStorage.getInstance().getWolf().getSpeed());
                pic = "Recourses/Bear.png";
            }
            switch(i){
                case 0: FightDataStorage.getInstance().setEnemy1(enemy);
                        spawnCreature(pic, 40, 60, 730, 500);
                    break;
                case 1: FightDataStorage.getInstance().setEnemy2(enemy);
                        spawnCreature(pic, 40, 60, 730, 400);
                    break;
                case 2: FightDataStorage.getInstance().setEnemy3(enemy);
                        spawnCreature(pic, 40, 60, 730, 300);
                    break;
            }
        }
        
        
    }

    public void createCreaturePane(ImageView creature, int creaturePaneWitdh, int creaturePaneHeight, int creaturePaneX, int creaturePaneY) {

        creaturePane1 =  new AnchorPane();
        creaturePane2 =  new AnchorPane();
        creaturePane3 =  new AnchorPane();
        System.err.println(FightDataStorage.getInstance().getEnemy1());
        if(FightDataStorage.getInstance().getEnemy1() != null)
        {
            
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

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) { 
            fight();
        }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    

    
}
