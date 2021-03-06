/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import Creature.*;
import Creature.Hero;
import DataStorage.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class FightController implements Initializable {

    @FXML
    private Button backToCity;
    @FXML
    private Button fightAgain;
    @FXML
    private Button revive;
    @FXML
    private Button special;
    @FXML
    private Label experienceGained;
    @FXML
    private Label goldGained;
    @FXML
    private ImageView XP;
    @FXML
    private ImageView victoryPicture;
    @FXML
    private ImageView defeatPicture;
    @FXML
    private ImageView forestFight;
    @FXML
    private ImageView desertFight;
    @FXML
    private ImageView mountainsFight;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label levelLabel;
    @FXML
    private Label combatMessage1;
    @FXML
    private Label combatMessage2;
    @FXML
    private Label combatMessage3;

    private int heroEXP;
    public int timerCounter = 0;
    public String[] fightOrder;
    // ALLA CREATURES//
    private Hero heroChar;
    private Enemy enemy;
    private Bear bear;
    private Scorpion scorpion;
    private Snake snake;
    private Spider spider;
    private Wolf wolf;

    // Anchor panes // 
    private final AnchorPane creaturePane1 = new AnchorPane();
    private final AnchorPane creaturePane2 = new AnchorPane();
    private final AnchorPane creaturePane3 = new AnchorPane();
    private final AnchorPane creaturePane4 = new AnchorPane();

    //HP BARS //
    private ImageView hpBarCreature1 = new ImageView();
    private ImageView hpBarCreature2 = new ImageView();
    private ImageView hpBarCreature3 = new ImageView();
    private ImageView hpBarCreature4 = new ImageView();

    private String attackSelect;
    private final ArrayList<String> attackOrder = new ArrayList();
    private int currentXP;
    private int heroExpToLevel;
    private int goldLost;
    private Timeline timeline;
    private int numberCreature;
    private int backGroundPicture;
    private int damageDisplay;
    private int specialAttack = 0;

    private final SoundManager soundManager = new SoundManager(); // tar hand om alla ljud i spelet

    private final ConfigFile config = new ConfigFile();

    private final String fightBackgroundSound = "Fight";
    private final String levelUpSound = "level_up";
    private final String buttonClick = "button_click";
    private final String heroAttacking = "hero_attacking";
    private final String heroSpecialAttack1 = "hero_special_1";
    private final String heroSpecialAttack2 = "hero_special_2";
    private final String heroSpecialAttack3 = "hero_special_3";
    private final String heroHurt = "hero_being_hit";
    private final String heroDeath = "heroDeath";
    private final String enemyDeath = "enemy_death";
    private final String victory = "victory";
    private final String thatwaseasy = "thatwaseasy";
    private final String applause = "applause";
    private final String gameOver = "gameover";
    private final String bearAttack = "bear_attack";
    private final String wolfAttack = "wolf_attack";
    private final String snakeAttack = "snake_attack";
    private final String scorpionAttack = "scorpion_attack";
    private final String spiderAttack = "spider_attack";
    private final String primarySound = "primary";
    private final String secondarySound = "secondary";
    private final String backgroundSound = "background";

    private boolean victoryDeath = false;

    @FXML
    public void goToCity(ActionEvent event) {

        stopWorldTime();
        FightDataStorage.getInstance().setEnemy1(null);
        FightDataStorage.getInstance().setEnemy2(null);
        FightDataStorage.getInstance().setEnemy3(null);
        attackSelect = null;

        if(victoryDeath == true){
            soundManager.stopTheSound(secondarySound);
            victoryDeath = false;
        }
        soundManager.stopTheSound(backgroundSound);
        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");

    }

    @FXML
    public void revive(ActionEvent event) { // om man dör så ska man kunna reviva

        stopWorldTime();
        FightDataStorage.getInstance().setEnemy1(null);
        FightDataStorage.getInstance().setEnemy2(null);
        FightDataStorage.getInstance().setEnemy3(null);
        attackSelect = null;

        if(victoryDeath == true){
            soundManager.stopTheSound(secondarySound);
            victoryDeath = false;
        }
        
        soundManager.stopTheSound(backgroundSound);
        soundManager.defineSound(buttonClick);

        HeroDataStorage.getInstance().getHero().setHeroCurrentHP(1);
        SwitchScene sc = new SwitchScene();
        sc.change(event, "InnScene");

    }

    @FXML
    public void fightAgain(ActionEvent event) { //slåss igen

        stopWorldTime();
        FightDataStorage.getInstance().setEnemy1(null);
        FightDataStorage.getInstance().setEnemy2(null);
        FightDataStorage.getInstance().setEnemy3(null);
        attackSelect = null;

        if (victoryDeath == true) {
            soundManager.stopTheSound(secondarySound);
            victoryDeath = false;
        }
        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Fight");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) { //ladda alla metoder
        loadEnemysToDataStorage(); // ladda alla enemys in från databasen
        changeBackGround(); //välj bakgrund
        worldTime(); //starta world time
        loadEnemyStatsFromDataStorage(); //ladda in till datastorage
        loadHeroStatsFromDataStorage(); //ladda hero till datastorage
        whatHeroToLoad(); //kolla vilken hero man ska ladda
        XPBAR(); //kör xpbar
        createEnemy(); // skapa enemys
        calculateAttackOrder(); //räknar ut tur ordningen baserat på speed
        selectEnemy(); // kollar vilken enemy man selectar
        //ALLA KNAPPAR OCH LABELS//
        HoverMouse.getInstance().inHover(revive);
        HoverMouse.getInstance().outHover(revive);
        HoverMouse.getInstance().inHover(fightAgain);
        HoverMouse.getInstance().outHover(fightAgain);
        HoverMouse.getInstance().inHover(backToCity);
        HoverMouse.getInstance().outHover(backToCity);
        HoverMouse.getInstance().inHover(special);
        HoverMouse.getInstance().outHover(special);

        System.out.println("heroEXP" + HeroDataStorage.getInstance().getHero().getEXP());

        if(victoryDeath == true){
            soundManager.stopTheSound(secondarySound);
            victoryDeath = false;
        }

        soundManager.defineSound(fightBackgroundSound);;

    }

    public void XPBAR() { //XPBAR CALC

        int maxXpWidth = 763;
        currentXP = HeroDataStorage.getInstance().getHero().getEXP();
        heroExpToLevel = HeroDataStorage.getInstance().getHero().getLevel() * 100;
        XP.setScaleX((HeroDataStorage.getInstance().getHero().getEXP() * maxXpWidth) / heroExpToLevel);
        XP.setX(XP.getScaleX() / 2);
        levelLabel.setText("Your level is " + HeroDataStorage.getInstance().getHero().getLevel());

        if (currentXP >= heroExpToLevel) {
            heroLevelUp();

            XPBAR();

        }

    }

    public void spawnCreature(String URL, int creaturePaneWitdh, int creaturePaneHeight, int creaturePaneX, int creaturePaneY, String ID) { //Spawna creature

        ImageView creature = new ImageView();
        Image creatureDisplay = new Image(getClass().getResourceAsStream(URL));
        creature.setImage(creatureDisplay);

        createCreaturePane(creature, creaturePaneWitdh, creaturePaneHeight, creaturePaneX, creaturePaneY, ID);

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

    public void createEnemy() { //SKAPA RANDOM FIENDER

        // Ska vara en random generator här beroende på vilken lvl osv man är
        String pic = "";
        Random rand = new Random();
        numberCreature = rand.nextInt(3) + 1;
        System.out.print("antal djur" + numberCreature);
        for (int i = 1; i <= numberCreature; i++) {
            int whatCreature = rand.nextInt(5) + 1;

            if (whatCreature == 1) {
                enemy = new Bear(EnemyBaseDataStorage.getInstance().getBear().getName(), EnemyBaseDataStorage.getInstance().getBear().getHp(), EnemyBaseDataStorage.getInstance().getBear().getMaxDmg(), EnemyBaseDataStorage.getInstance().getBear().getMinDmg(), EnemyBaseDataStorage.getInstance().getBear().getSpeed());
                pic = "Recourses/Bear.png";
            } else if (whatCreature == 2) {
                enemy = new Scorpion(EnemyBaseDataStorage.getInstance().getScorpion().getName(), EnemyBaseDataStorage.getInstance().getScorpion().getHp(), EnemyBaseDataStorage.getInstance().getScorpion().getMaxDmg(), EnemyBaseDataStorage.getInstance().getScorpion().getMinDmg(), EnemyBaseDataStorage.getInstance().getScorpion().getSpeed());
                pic = "Recourses/Scorpion.png";
            } else if (whatCreature == 3) {
                enemy = new Snake(EnemyBaseDataStorage.getInstance().getSnake().getName(), EnemyBaseDataStorage.getInstance().getSnake().getHp(), EnemyBaseDataStorage.getInstance().getSnake().getMaxDmg(), EnemyBaseDataStorage.getInstance().getSnake().getMinDmg(), EnemyBaseDataStorage.getInstance().getSnake().getSpeed());
                pic = "Recourses/Snake.png";
            } else if (whatCreature == 4) {
                enemy = new Spider(EnemyBaseDataStorage.getInstance().getSpider().getName(), EnemyBaseDataStorage.getInstance().getSpider().getHp(), EnemyBaseDataStorage.getInstance().getSpider().getMaxDmg(), EnemyBaseDataStorage.getInstance().getSpider().getMinDmg(), EnemyBaseDataStorage.getInstance().getSpider().getSpeed());
                pic = "Recourses/Spider.png";
            } else if (whatCreature == 5) {
                enemy = new Wolf(EnemyBaseDataStorage.getInstance().getWolf().getName(), EnemyBaseDataStorage.getInstance().getWolf().getHp(), EnemyBaseDataStorage.getInstance().getWolf().getMaxDmg(), EnemyBaseDataStorage.getInstance().getWolf().getMinDmg(), EnemyBaseDataStorage.getInstance().getWolf().getSpeed());
                pic = "Recourses/Wolf.png";
            }
            switch (i) {
                case 1:
                    pane.getChildren().add(creaturePane2);
                    FightDataStorage.getInstance().setEnemy1(enemy);
                    spawnCreature(pic, 40, 60, 730, 500, "1");
                    break;
                case 2:
                    pane.getChildren().add(creaturePane3);
                    FightDataStorage.getInstance().setEnemy2(enemy);
                    spawnCreature(pic, 40, 60, 730, 400, "2");
                    break;
                case 3:
                    pane.getChildren().add(creaturePane4);
                    FightDataStorage.getInstance().setEnemy3(enemy);
                    spawnCreature(pic, 40, 60, 730, 300, "3");
                    break;
            }
        }

    }

    public void createCreaturePane(ImageView creature, int creaturePaneWitdh, int creaturePaneHeight, int creaturePaneX, int creaturePaneY, String ID) { // SKAPA ALLA PANES

        switch (ID) {
            case "0": {
                creaturePane1.setPrefWidth(creaturePaneWitdh);
                creaturePane1.setPrefHeight(creaturePaneHeight); //Storlek på pane
                creaturePane1.setLayoutX(creaturePaneX);
                creaturePane1.setLayoutY(creaturePaneY);
                hpBarCreature1 = new ImageView();
                Image imageHealthCreature1 = new Image(getClass().getResourceAsStream("Recourses/HealthBar.png"));
                hpBarCreature1.setImage(imageHealthCreature1);
                creaturePane1.setId(ID);
                creaturePane1.getChildren().add(hpBarCreature1);
                creaturePane1.getChildren().add(creature);
                hpBarCreature1.setScaleX(healthPaneHeroScaler());
                hpBarCreature1.setX(healthPaneHeroScaler() / 2);
                System.out.println("Creature pane 0, Hero");
                break;
            }
            case "1": {
                creaturePane2.setPrefWidth(creaturePaneWitdh);
                creaturePane2.setPrefHeight(creaturePaneHeight); //Storlek på pane
                creaturePane2.setLayoutX(creaturePaneX);
                creaturePane2.setLayoutY(creaturePaneY);
                hpBarCreature2 = new ImageView();
                Image imageHealthCreature1 = new Image(getClass().getResourceAsStream("Recourses/HealthBar.png"));
                hpBarCreature2.setImage(imageHealthCreature1);
                creaturePane2.setId(ID);
                creaturePane2.getChildren().add(hpBarCreature2);
                creaturePane2.getChildren().add(creature);
                hpBarCreature2.setScaleX(healthPaneCreatureScaler());
                hpBarCreature2.setX(healthPaneCreatureScaler() / 2);
                System.out.println("Creature pane 1, Enemy");
                break;
            }
            case "2": {
                creaturePane3.setPrefWidth(creaturePaneWitdh);
                creaturePane3.setPrefHeight(creaturePaneHeight); //Storlek på pane
                creaturePane3.setLayoutX(creaturePaneX);
                creaturePane3.setLayoutY(creaturePaneY);
                hpBarCreature3 = new ImageView();
                Image imageHealthCreature1 = new Image(getClass().getResourceAsStream("Recourses/HealthBar.png"));
                hpBarCreature3.setImage(imageHealthCreature1);
                creaturePane3.setId(ID);
                creaturePane3.getChildren().add(hpBarCreature3);
                creaturePane3.getChildren().add(creature);
                hpBarCreature3.setScaleX(healthPaneCreatureScaler());
                hpBarCreature3.setX(healthPaneCreatureScaler() / 2);
                System.out.println("Creature pane 2, Enemy");
                break;
            }
            case "3": {
                creaturePane4.setPrefWidth(creaturePaneWitdh);
                creaturePane4.setPrefHeight(creaturePaneHeight); //Storlek på pane
                creaturePane4.setLayoutX(creaturePaneX);
                creaturePane4.setLayoutY(creaturePaneY);
                hpBarCreature4 = new ImageView();
                Image imageHealthCreature1 = new Image(getClass().getResourceAsStream("Recourses/HealthBar.png"));
                hpBarCreature4.setImage(imageHealthCreature1);
                creaturePane4.setId(ID);
                creaturePane4.getChildren().add(hpBarCreature4);
                creaturePane4.getChildren().add(creature);
                hpBarCreature4.setScaleX(healthPaneCreatureScaler());
                hpBarCreature4.setX(healthPaneCreatureScaler() / 2);
                System.out.println("Creature pane 3, Enemy");
                break;
            }
        }

    }

    public int healthPaneHeroScaler() { //Heros healthpane scaler

        int currentHP = HeroDataStorage.getInstance().getHero().getHeroCurrentHP();
        int maxHP = HeroDataStorage.getInstance().getHero().getHp();
        int maxImageView = 50;

        int calculate;

        calculate = (currentHP * maxImageView) / maxHP; // Fullt fungerande, bara till alla kalla metoden varje tick
        return calculate;
    }

    public int healthPaneCreatureScaler() { //Samma sak som ovanstående fast med creatures

        if (attackSelect == null) {
            return 50;
        }
        if (attackSelect.equals("1")) {

            int hp = FightDataStorage.getInstance().getEnemy1().getHp();
            int maxHp = FightDataStorage.getInstance().getEnemy1().getMaxHp();
            int maxImageView = 50;
            int calculate;

            calculate = (hp * maxImageView) / maxHp;

            return calculate;
        }
        if (attackSelect.equals("2")) { //Procent beräkningar

            int hp = FightDataStorage.getInstance().getEnemy2().getHp();
            int maxHp = FightDataStorage.getInstance().getEnemy2().getMaxHp();
            int maxImageView = 50;
            int calculate;

            calculate = (hp * maxImageView) / maxHp;

            return calculate;
        }
        if (attackSelect.equals("3")) { //Procent beräkningar

            int hp = FightDataStorage.getInstance().getEnemy3().getHp();
            int maxHp = FightDataStorage.getInstance().getEnemy3().getMaxHp();
            int maxImageView = 50;
            int calculate;

            calculate = (hp * maxImageView) / maxHp;

            return calculate;
        }
        return 0;
    }

    public int healthPaneCreatureScalerUpdateAll(int enemyHp, int enemyMaxHP) { //uppdatera alla, används med cleave attacken

        int hp = enemyHp;
        int maxHp = enemyMaxHP;
        int maxImageView = 50;
        int calculate;

        calculate = (hp * maxImageView) / maxHp;

        return calculate;
    }

    public void healthPaneScaleInGame() { //scala när worldtime är igång

        try {
            if (attackSelect.equals("1")) {
                hpBarCreature2.setScaleX(healthPaneCreatureScaler());
                hpBarCreature2.setX(healthPaneCreatureScaler() / 2);
            } else if (attackSelect.equals("2")) {
                hpBarCreature3.setScaleX(healthPaneCreatureScaler());
                hpBarCreature3.setX(healthPaneCreatureScaler() / 2);
            } else if (attackSelect.equals("3")) {
                hpBarCreature4.setScaleX(healthPaneCreatureScaler());
                hpBarCreature4.setX(healthPaneCreatureScaler() / 2);
            } else if (attackSelect == null) {
                System.out.println("Null");
            }

            hpBarCreature1.setScaleX(healthPaneHeroScaler());
            hpBarCreature1.setX(healthPaneHeroScaler() / 2);
        } catch (Exception ex) {
            //System.out.println("Kan inte ladda innan world time startats");
        }
    }

    public void healthPaneScaleInGameUpdateAll() { //Uppdatera alla för cleaveattacken

        try {
            hpBarCreature2.setScaleX(healthPaneCreatureScalerUpdateAll(FightDataStorage.getInstance().getEnemy1().getHp(), FightDataStorage.getInstance().getEnemy1().getMaxHp()));
            hpBarCreature2.setX(healthPaneCreatureScalerUpdateAll(FightDataStorage.getInstance().getEnemy1().getHp(), FightDataStorage.getInstance().getEnemy1().getMaxHp()) / 2);
            hpBarCreature3.setScaleX(healthPaneCreatureScalerUpdateAll(FightDataStorage.getInstance().getEnemy2().getHp(), FightDataStorage.getInstance().getEnemy2().getMaxHp()));
            hpBarCreature3.setX(healthPaneCreatureScalerUpdateAll(FightDataStorage.getInstance().getEnemy2().getHp(), FightDataStorage.getInstance().getEnemy2().getMaxHp()) / 2);
            hpBarCreature4.setScaleX(healthPaneCreatureScalerUpdateAll(FightDataStorage.getInstance().getEnemy3().getHp(), FightDataStorage.getInstance().getEnemy3().getMaxHp()));
            hpBarCreature4.setX(healthPaneCreatureScalerUpdateAll(FightDataStorage.getInstance().getEnemy3().getHp(), FightDataStorage.getInstance().getEnemy3().getMaxHp()) / 2);
        } catch (Exception ex) {
            System.out.println("Mer än fler enemys : Error :");
        }
    }

    public void whatHeroToLoad() { //vilken hero den ska ladda

        if (HeroDataStorage.getInstance().getHero().getHeroType() == 1) {

            pane.getChildren().add(creaturePane1);
            spawnCreature("Recourses/WarriorChar.png", 40, 60, 30, 500, "0");

        } else if (HeroDataStorage.getInstance().getHero().getHeroType() == 2) {
            pane.getChildren().add(creaturePane1);
            spawnCreature("Recourses/RangerChar.png", 40, 60, 30, 500, "0");

        } else if (HeroDataStorage.getInstance().getHero().getHeroType() == 3) {
            pane.getChildren().add(creaturePane1);
            spawnCreature("Recourses/MageChar.png", 40, 60, 30, 500, "0");

        }

    }

    public void selectEnemy() { // selectar den man trycker på

        //attackSelect = selectEnemy(creaturePane1, creaturePane2, creaturePane3, creaturePane4); // första är den som selectas
        attackSelect = selectEnemy(creaturePane2, creaturePane3, creaturePane1, creaturePane4);
        attackSelect = selectEnemy(creaturePane3, creaturePane2, creaturePane1, creaturePane4);
        attackSelect = selectEnemy(creaturePane4, creaturePane2, creaturePane1, creaturePane3);

    }

    public void worldTime() { //starta world time

        timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                ae -> handleWorldTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void handleWorldTime() {

        //hanterar alla saker som använder worldtime//
        checkIfEnemysTurn(); //Kolla om det är fienden tur, isf attakera heron
        removeDeadEnemysFromArrayList(); //tar väck döda enemys

    }

    public void stopWorldTime() { //stanna worldtime

        timeline.stop();
    }

    public String selectEnemy(AnchorPane pane, AnchorPane pane2, AnchorPane pane3, AnchorPane pane4) { //själva attackmetoden visuellt för heron, jobbar med hero klassen och andra klasser
        pane.setOnMouseClicked((MouseEvent e) -> {
            pane.blendModeProperty().set(BlendMode.HARD_LIGHT);
            pane2.blendModeProperty().set(BlendMode.SRC_OVER);
            pane3.blendModeProperty().set(BlendMode.SRC_OVER);
            pane4.blendModeProperty().set(BlendMode.SRC_OVER);
            attackSelect = pane.getId();
            if (attackOrder.get(0).equals("Hero") && combatMessage1.getText().equals("") && specialAttack == 0) { //kollar om det är herons tur
                selectEnemyToAttack(); //selecta enemy
                heroAttack(); //attakera enemy som är selectad
                damageLabelCheckEnemy(); //floating combat text
                KillEnemyDisplay(); //kolla om fienden dog
                healthPaneScaleInGame(); //ändra hp baren
                checkIfEnemyIsDead(); //kolla om alla fiender är döda
                attackOrder.remove(0); // kolla om det är heros tur, ta väck honom i ordningen
            } else if (attackOrder.get(0).equals("Hero") && combatMessage1.getText().equals("") && specialAttack > 0) { // kolla om det är en special attack
                selectEnemyToAttack();
                doSpecialAttack();
            } else {
                System.out.println("Det är inte din tur idiot"); //debugg

            }
        });
        return attackSelect; //returna den man trycker på
    }

    public void heroAttack() { // attack och räknar ut chansen för en speical attack
        heroChar.heroAttack();
        System.out.println(attackOrder.size());
        Random randSpecial = new Random();

        int value = randSpecial.nextInt(3) + 1;
        if (value == 3) {
            special.setVisible(true);
        }

        soundManager.playSoundAtSpecialOccation(heroAttacking, heroChar.getHeroType());
//        soundManager.randomizeSounds(heroAttacking, heroChar.getHeroType());
    }

    public void KillEnemyDisplay() { //kollar om enemys är döda och tar väck dom

        try {

            if (numberCreature == 3) {

                if (DataStorage.FightDataStorage.getInstance().getEnemy1().getHp() <= 0) {

                    creaturePane2.setVisible(false);

                }
                if (FightDataStorage.getInstance().getEnemy2().getHp() <= 0) {

                    creaturePane3.setVisible(false);
                }
                if (FightDataStorage.getInstance().getEnemy3().getHp() <= 0) {

                    creaturePane4.setVisible(false);
                }
            } else if (numberCreature == 2) {
                if (DataStorage.FightDataStorage.getInstance().getEnemy1().getHp() <= 0) {

                    creaturePane2.setVisible(false);
                }
                if (FightDataStorage.getInstance().getEnemy2().getHp() <= 0) {

                    creaturePane3.setVisible(false);
                }
            } else if (numberCreature == 1) {

                if (DataStorage.FightDataStorage.getInstance().getEnemy1().getHp() <= 0) {

                    creaturePane2.setVisible(false);
                }
            }

        } catch (Exception ex) {
            combatMessage1.setText("Error killing enemy");
        }

    }

    public void checkIfEnemyIsDead() { //kolla om alla är döda och isf tillkallar victory

        if (creaturePane2.isVisible() == false && creaturePane3.isVisible() == false && creaturePane4.isVisible() == false && numberCreature == 3) {

            System.out.println("Victory");
            victory();
        } else if (creaturePane2.isVisible() == false && creaturePane3.isVisible() == false && numberCreature == 2) {

            System.out.println("Victory");
            victory();
        } else if (creaturePane2.isVisible() == false && numberCreature == 1) {

            System.out.println("Victory");
            victory();
        }
    }

    public void killHero() { // kollar om hero är död
        if (heroChar.getHeroCurrentHP() <= 0) {

            stopWorldTime();
            creaturePane1.setVisible(false);
            defeatPicture.setVisible(true);
            combatMessage1.setVisible(false);
            special.setVisible(false);

            revive.setVisible(true);
            combatMessage3.setVisible(true);
            combatMessage3.setText("You lost : " + Integer.toString(getGoldLost()) + "gold");

            HeroDataStorage.getInstance().getHero().setGold(getGoldLost());
            System.out.println("You're dead mofo");

            soundManager.defineSound(heroDeath);
            soundManager.defineSound(gameOver);

        }
    }

    public void calculateAttackOrder() { //räknar ut attack order beroende på speed

        int heroWeaponSpeed = 0;
        int heroArmorSpeed = 0;

        int enemy1Speed = FightDataStorage.getInstance().getEnemy1().getSpeed();
        int enemy1StartSpeed = FightDataStorage.getInstance().getEnemy1().getSpeed();
        int enemy2Speed = 0;
        int enemy2StartSpeed = 0;
        int enemy3Speed = 0;
        int enemy3StartSpeed = 0;

        if (HeroDataStorage.getInstance().getWeapon() != null) {
            heroWeaponSpeed = HeroDataStorage.getInstance().getWeapon().getWeaponSpeed();
        }
        if (HeroDataStorage.getInstance().getArmor() != null) {
            heroArmorSpeed = HeroDataStorage.getInstance().getArmor().getArmorSpeed();
        }

        int heroSpeed = heroChar.getSpeed() + heroArmorSpeed + heroWeaponSpeed;
        int heroStartSpeed = heroChar.getSpeed() + heroArmorSpeed + heroWeaponSpeed;

        if (FightDataStorage.getInstance().getEnemy2() != null) {
            enemy2Speed = FightDataStorage.getInstance().getEnemy2().getSpeed();
            enemy2StartSpeed = FightDataStorage.getInstance().getEnemy2().getSpeed();
        }
        if (FightDataStorage.getInstance().getEnemy3() != null) {
            enemy3Speed = FightDataStorage.getInstance().getEnemy3().getSpeed();
            enemy3StartSpeed = FightDataStorage.getInstance().getEnemy3().getSpeed();
        }
        for (int i = 0; i < 100; i++) {
            if (heroSpeed >= enemy1Speed && heroSpeed >= enemy2Speed && heroSpeed >= enemy3Speed) {

                attackOrder.add("Hero");
                heroSpeed = heroSpeed - 1;
            }
            if (enemy1Speed > heroSpeed && enemy1Speed >= enemy2Speed && enemy1Speed >= enemy3Speed) {

                attackOrder.add("Enemy1");
                enemy1Speed = enemy1Speed - 1;
            }
            if (enemy2Speed > heroSpeed && enemy2Speed > enemy1Speed && enemy2Speed >= enemy3Speed) {

                attackOrder.add("Enemy2");
                enemy2Speed = enemy2Speed - 1;
            }
            if (enemy3Speed > heroSpeed && enemy3Speed > enemy1Speed && enemy3Speed > enemy2Speed) {

                attackOrder.add("Enemy3");
                enemy3Speed = enemy3Speed - 1;
            }

            if (heroSpeed == 0) {
                heroSpeed = heroStartSpeed;
                enemy1Speed = enemy1StartSpeed;
                enemy2Speed = enemy2StartSpeed;
                enemy3Speed = enemy3StartSpeed;
            }
        }
        System.out.println(attackOrder.toString());
    }

    public void checkIfEnemysTurn() { // kolla om det är fiendens tur och går till enemyattack beroende på vilken fiende det är
        try {
            if (attackOrder.get(0).equals("Enemy1") && creaturePane2.isVisible() == true) {

                enemyAttack("Bear", "Scorpion", "Snake", "Spider", "Wolf", 1);
                healthPaneScaleInGame();
                combatMessage1.setText("Opponents turn");

            } else if (attackOrder.get(0).equals("Enemy2") && creaturePane3.isVisible() == true) {

                enemyAttack("Bear", "Scorpion", "Snake", "Spider", "Wolf", 2);
                healthPaneScaleInGame();
                combatMessage1.setText("Opponents turn");

            } else if (attackOrder.get(0).equals("Enemy3") && creaturePane4.isVisible() == true) {
                enemyAttack("Bear", "Scorpion", "Snake", "Spider", "Wolf", 3);
                healthPaneScaleInGame();
                combatMessage1.setText("Opponents turn");

            } else {
                combatMessage1.setText("");
            }
        } catch (Exception ex) {
            System.err.println("ErrorCode 10293");
        }

    }

    public void removeDeadEnemysFromArrayList() { //tar väck döda enemys från attack ordr

        try {
            for (int i = 0; i < attackOrder.size(); i++) {

                if (creaturePane2.isVisible() == false) {

                    if (attackOrder.get(i).equals("Enemy1")) {
                        attackOrder.remove(i);
                        return;

                    }
                }
                if (creaturePane3.isVisible() == false) {

                    if (attackOrder.get(i).equals("Enemy2")) {

                        attackOrder.remove(i);
                        return;

                    }
                }
                if (creaturePane4.isVisible() == false) {

                    if (attackOrder.get(i).equals("Enemy3")) {

                        attackOrder.remove(i);
                        return;
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("ALLVARLIGT FUCKING FEL");
        }
    }

    public void selectEnemyToAttack() { // kollar vilken id fiende har och attakera den med hjälp av id på knappen

        if (attackSelect != null) {

            switch (attackSelect) {
                case "1":
                    FightDataStorage.getInstance().getEnemy1();
                    FightDataStorage.getInstance().setEnemyID("1");
                    break;
                case "2":
                    FightDataStorage.getInstance().getEnemy2();
                    FightDataStorage.getInstance().setEnemyID("2");
                    break;
                case "3":
                    FightDataStorage.getInstance().getEnemy3();
                    FightDataStorage.getInstance().setEnemyID("3");
                    break;
            }

        }
    }

    public void enemyAttack(String enemy1, String enemy2, String enemy3, String enemy4, String enemy5, int type) { //fiendens attack som räknar ut attacken med hjälp av flera klasser och armor

        String enemyType = "";
        if (type == 1) {
            enemyType = FightDataStorage.getInstance().getEnemy1().getName();
        } else if (type == 2) {
            enemyType = FightDataStorage.getInstance().getEnemy2().getName();
        } else if (type == 3) {
            enemyType = FightDataStorage.getInstance().getEnemy3().getName();
        }

        if (enemyType.equals(enemy1)) {

            damageDisplay = EnemyBaseDataStorage.getInstance().getBear().Attack(HeroDataStorage.getInstance().getArmor());
            heroChar.setHeroCurrentHP(heroChar.getHeroCurrentHP() - damageDisplay);
            damageLabel(creaturePane1);
            attackOrder.remove(0);
            soundManager.defineSound(bearAttack);
            soundManager.playSoundAtSpecialOccation(heroHurt, 0);

        } else if (enemyType.equals(enemy2)) {
            damageDisplay = EnemyBaseDataStorage.getInstance().getScorpion().Attack(HeroDataStorage.getInstance().getArmor());
            heroChar.setHeroCurrentHP(heroChar.getHeroCurrentHP() - damageDisplay);
            damageLabel(creaturePane1);
            attackOrder.remove(0);
            soundManager.defineSound(scorpionAttack);
            soundManager.playSoundAtSpecialOccation(heroHurt, 0);

        } else if (enemyType.equals(enemy3)) {
            damageDisplay = EnemyBaseDataStorage.getInstance().getSnake().Attack(HeroDataStorage.getInstance().getArmor());
            heroChar.setHeroCurrentHP(heroChar.getHeroCurrentHP() - damageDisplay);
            damageLabel(creaturePane1);
            attackOrder.remove(0);
            soundManager.defineSound(snakeAttack);
            soundManager.playSoundAtSpecialOccation(heroHurt, 0);

        } else if (enemyType.equals(enemy4)) {
            damageDisplay = EnemyBaseDataStorage.getInstance().getSpider().Attack(HeroDataStorage.getInstance().getArmor());
            heroChar.setHeroCurrentHP(heroChar.getHeroCurrentHP() - damageDisplay);
            damageLabel(creaturePane1);
            attackOrder.remove(0);
            soundManager.defineSound(spiderAttack);
            soundManager.playSoundAtSpecialOccation(heroHurt, 0);

        } else if (enemyType.equals(enemy5)) {
            damageDisplay = EnemyBaseDataStorage.getInstance().getWolf().Attack(HeroDataStorage.getInstance().getArmor());
            heroChar.setHeroCurrentHP(heroChar.getHeroCurrentHP() - damageDisplay);
            damageLabel(creaturePane1);
            attackOrder.remove(0);
            soundManager.defineSound(wolfAttack);
            soundManager.playSoundAtSpecialOccation(heroHurt, 0);

        }
        killHero();
    }

    public void victory() { // här vinner man

        victoryDeath = true;
        soundManager.stopTheSound(backgroundSound);
        soundManager.defineSound(applause);

        fightAgain.setVisible(true);
        backToCity.setVisible(true);
        victoryPicture.setVisible(true);
        special.setVisible(false);

        HeroDataStorage.getInstance().getHero().setEXP(HeroDataStorage.getInstance().getHero().getEXP() + expGain());
        HeroDataStorage.getInstance().getHero().setGold(HeroDataStorage.getInstance().getHero().getGold() + getGoldGained());

        XPBAR(); //updatera xpbar

        combatMessage1.setText("Exp gained: " + Integer.toString(expGain()));
        combatMessage2.setText("Gold gained: " + Integer.toString(getGoldGained()));

        try {
            DBConnect.connect(combatMessage1);
            DBConnect.saveToDB(combatMessage1);
        } catch (Exception ex) {
            combatMessage1.setText("Error saving hero");
            combatMessage2.setText("Error saving hero");
        } finally {

            DBConnect.close(combatMessage1);
            stopWorldTime();
        }
    }

    public int expGain() { //exp man får från alla monster

        int experienceGain = 0;

        if (numberCreature == 1) {
            experienceGain = 1 * 10;

        } else if (numberCreature == 2) {
            experienceGain = 2 * 15;

        } else if (numberCreature == 3) {
            experienceGain = 3 * 20;
        }
        return experienceGain;
    }

    public int getGoldGained() {  // for guld efter att man har dödat alla enemys

        int goldGainedInt = numberCreature * 10 * numberCreature; // guld man får från alla fiender
        return goldGainedInt;

    }

    public int getGoldLost() { // guld man förlorar om man dör
        goldLost = HeroDataStorage.getInstance().getHero().getGold() / 2;
        return goldLost;
    }

    private void heroLevelUp() { // hero level up metod
        HeroDataStorage.getInstance().getHero().setLevel(HeroDataStorage.getInstance().getHero().getLevel() + 1);
        HeroDataStorage.getInstance().getHero().setEXP(currentXP - heroExpToLevel);
        HeroDataStorage.getInstance().getHero().setHp(HeroDataStorage.getInstance().getHero().getLevel() * 100);
        HeroDataStorage.getInstance().getHero().setHeroCurrentHP(HeroDataStorage.getInstance().getHero().getLevel() * 100);
        HeroDataStorage.getInstance().getHero().setBaseDamage(HeroDataStorage.getInstance().getHero().getLevel() * 5);
        soundManager.defineSound(levelUpSound);

    }

    public void damageLabel(AnchorPane creaturePane) { //floating combat texts
        Label damageLabel = new Label();
        Double xPos = creaturePane.getLayoutX();
        Double yPos = creaturePane.getLayoutY();

        damageLabel.setLayoutX(xPos + 10);
        damageLabel.setLayoutY(yPos - 20);

        checkWhoAttackDisplay(damageLabel, "Bear", "Scorpion", "Snake", "Spider", "Wolf", 0); // hero

        if (attackOrder.get(0).equals("Enemy1") && creaturePane2.isVisible() == true) {

            checkWhoAttackDisplay(damageLabel, "Bear", "Scorpion", "Snake", "Spider", "Wolf", 1);

        }
        if (attackOrder.get(0).equals("Enemy2") && creaturePane3.isVisible() == true) {

            checkWhoAttackDisplay(damageLabel, "Bear", "Scorpion", "Snake", "Spider", "Wolf", 2);
        }
        if (attackOrder.get(0).equals("Enemy3") && creaturePane4.isVisible() == true) {

            checkWhoAttackDisplay(damageLabel, "Bear", "Scorpion", "Snake", "Spider", "Wolf", 3);
        }

        damageLabel.textFillProperty().set(Color.WHITE);
        System.out.println("New damage");

        if (specialAttack > 0 && attackOrder.get(0).equals("Hero")) {
            System.out.println("test123");
            damageLabel.textFillProperty().set(Color.RED);
        }

        pane.getChildren().add(damageLabel);

        TranslateTransition move
                = new TranslateTransition(Duration.millis(1000), damageLabel);

        Random randomXPos = new Random();

        int randomNumber = randomXPos.nextInt(15 + 15) - 15;
        System.out.println(randomNumber);

        move.setFromY(0);
        move.setToY(-20);
        move.setFromX(0);
        move.setToX(randomNumber);
        move.setCycleCount(1);
        move.play();

        timeLine(damageLabel);

    }

    public void timeLine(Label damageLabel) { //timeline för att ta väck floating combat texts efter hand

        Timeline removeLabel = new Timeline(new KeyFrame(
                Duration.millis(1100), temp
                -> removeLabel(damageLabel)));
        removeLabel.play();
    }

    public void removeLabel(Label damageLabel) { //tar väck en label

        System.out.println("Remove label");
        pane.getChildren().remove(damageLabel);
    }

    public void damageLabelCheckEnemy() { // hanterare för damageLabel

        switch (attackSelect) {
            case "1":
                damageLabel(creaturePane2);
                break;
            case "2":
                damageLabel(creaturePane3);
                break;
            case "3":
                damageLabel(creaturePane4);
                break;
        }
    }

    public void damageLabelCheckEnemyAll() { //hanterare för damageLabel (cleave)

        try {

            if (FightDataStorage.getInstance().getEnemy1() != null && creaturePane2.isVisible() == true) {
                damageLabel(creaturePane2);
            }

            if (FightDataStorage.getInstance().getEnemy2() != null && creaturePane3.isVisible() == true) {
                damageLabel(creaturePane3);
            }
            if (FightDataStorage.getInstance().getEnemy3() != null && creaturePane4.isVisible() == true) {
                damageLabel(creaturePane4);
            }

        } catch (Exception ex) {
            System.out.println("test");
        }
    }

    public void checkWhoAttackDisplay(Label damageLabel, String enemy1, String enemy2, String enemy3, String enemy4, String enemy5, int type) { // kolla vilken fiende som ska få en floating combat text

        String enemyType = "";
        if (type == 1) {
            enemyType = FightDataStorage.getInstance().getEnemy1().getName();
        } else if (type == 2) {
            enemyType = FightDataStorage.getInstance().getEnemy2().getName();
        } else if (type == 3) {
            enemyType = FightDataStorage.getInstance().getEnemy3().getName();
        }

        if (attackOrder.get(0).equals("Hero")) {
            int getDamage = heroChar.getDisplayedDamage();
            String getDamageString = Integer.toString(getDamage);
            damageLabel.setText(getDamageString);

        } else if (enemyType.equals(enemy1)) {

            damageDisplayOnHero(damageLabel, enemyType);

        } else if (enemyType.equals(enemy2)) {

            damageDisplayOnHero(damageLabel, enemyType);
        } else if (enemyType.equals(enemy3)) {

            damageDisplayOnHero(damageLabel, enemyType);
        } else if (enemyType.equals(enemy4)) {

            damageDisplayOnHero(damageLabel, enemyType);
        } else if (enemyType.equals(enemy5)) {

            damageDisplayOnHero(damageLabel, enemyType);
        }

    }

    public void damageDisplayOnHero(Label damageLabel, String enemyType) { //heron får en damage på sig visuellt

        String getDamageString = Integer.toString(damageDisplay);
        damageLabel.setText(enemyType + getDamageString);

    }

    @FXML
    public void handleSpecialAttack(ActionEvent event) { // hantera special attack random

        if (event.getSource().equals(special)) {

            Random rand = new Random();
            specialAttack = rand.nextInt(3) + 1;

            System.out.println("You got attack " + specialAttack);
            special.setVisible(false);

            if (specialAttack == 1) {

                combatMessage1.setText("You got a strong attack");
            } else if (specialAttack == 2) {

                combatMessage1.setText("You got a double attack");
            } else if (specialAttack == 3) {

                combatMessage1.setText("You got a cleave attack");
            }
        }
    }

    public void doSpecialAttack() { // gör alla attacker med hjälp av hero klassen
        //1//
        if (specialAttack == 1) {
            
            System.out.println("Special Attack 1");
            damageDisplay = heroChar.specialAttack1();

            damageLabelCheckEnemy();
            KillEnemyDisplay();
            healthPaneScaleInGame();
            checkIfEnemyIsDead();
            attackOrder.remove(0);
            specialAttack = 0;

            soundManager.playSoundAtSpecialOccation(heroSpecialAttack1, heroChar.getHeroType());

        } else if (specialAttack == 2) {

            
            System.out.println("Special Attack 2 1/2");
            damageDisplay = heroChar.specialAttack2(); //2x
            damageLabelCheckEnemy();

            System.out.println("Special Attack 2 2/2");
            damageDisplay = heroChar.specialAttack2(); //2x
            damageLabelCheckEnemy();

            KillEnemyDisplay();
            healthPaneScaleInGame();
            checkIfEnemyIsDead();
            attackOrder.remove(0);
            specialAttack = 0;

            soundManager.playSoundAtSpecialOccation(heroSpecialAttack2, heroChar.getHeroType());

        } else if (specialAttack == 3) {

            
            System.out.println("Special Attack 3");
            damageDisplay = heroChar.specialAttack3();

            damageLabelCheckEnemyAll();
            KillEnemyDisplay();
            healthPaneScaleInGameUpdateAll();
            checkIfEnemyIsDead();
            attackOrder.remove(0);
            specialAttack = 0;

            soundManager.playSoundAtSpecialOccation(heroSpecialAttack3,0);

        }
    }

    private void changeBackGround() { // ändra random bakgrund
        Random random = new Random();
        backGroundPicture = random.nextInt(3);

        if (backGroundPicture == 0) {
            forestFight.setVisible(true);
        } else if (backGroundPicture == 1) {
            mountainsFight.setVisible(true);
        } else if (backGroundPicture == 2) {
            desertFight.setVisible(true);
        }

    }

    private void loadEnemysToDataStorage() { // ladda enemys från dataStorage
        try {
            DBConnect.connect(combatMessage1);
            ResultSet getCreature = DBConnect.CreateSelectStatement("select * from game.enemy", combatMessage1);
            while (getCreature.next()) {
                String enemyName = getCreature.getString("enemyName");
                int enemyHp = getCreature.getInt("enemyBaseHP");
                int enemyMaxDamage = getCreature.getInt("enemyBaseMaxDamage");
                int enemyMinDamage = getCreature.getInt("enemyBaseMinDamage");
                int enemySpeed = getCreature.getInt("enemyBaseSpeed");

                switch (enemyName) {
                    case "Bear":
                        Bear bear = new Bear(enemyName, enemyHp, enemyMaxDamage, enemyMinDamage, enemySpeed);
                        EnemyBaseDataStorage.getInstance().setBear(bear);
                        break;
                    case "Scorpion":
                        Scorpion scorpion = new Scorpion(enemyName, enemyHp, enemyMaxDamage, enemyMinDamage, enemySpeed);
                        EnemyBaseDataStorage.getInstance().setScorpion(scorpion);
                        break;
                    case "Snake":
                        Snake snake = new Snake(enemyName, enemyHp, enemyMaxDamage, enemyMinDamage, enemySpeed);
                        EnemyBaseDataStorage.getInstance().setSnake(snake);
                        break;
                    case "Spider":
                        Spider spider = new Spider(enemyName, enemyHp, enemyMaxDamage, enemyMinDamage, enemySpeed);
                        EnemyBaseDataStorage.getInstance().setSpider(spider);
                        break;
                    case "Wolf":
                        Wolf wolf = new Wolf(enemyName, enemyHp, enemyMaxDamage, enemyMinDamage, enemySpeed);
                        EnemyBaseDataStorage.getInstance().setWolf(wolf);
                        break;
                }
            }
        } catch (SQLException ex) {
            combatMessage1.setText("Error loading enemys from database");
        } finally {
            DBConnect.close(combatMessage1);
        }
    }

}
