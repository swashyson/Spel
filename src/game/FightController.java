/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import Creature.*;
import Creature.Hero;
import DataStorage.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    private ImageView XP;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label levelLabel;
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
    private AnchorPane creaturePane1 = new AnchorPane();
    private AnchorPane creaturePane2 = new AnchorPane();
    private AnchorPane creaturePane3 = new AnchorPane();
    private AnchorPane creaturePane4 = new AnchorPane();

    private ImageView hpBarCreature1 = new ImageView();
    private ImageView hpBarCreature2 = new ImageView();
    private ImageView hpBarCreature3 = new ImageView();
    private ImageView hpBarCreature4 = new ImageView();

    private String attackSelect;
    private ArrayList<String> attackOrder = new ArrayList();
    private int currentXP;
    private int heroExpToLevel;
    private Timeline timeline;
    private int numberCreature;
    
    private String[] enemyValue;

    SoundManager soundManager = new SoundManager(); // tar hand om alla ljud i spelet

    @FXML
    public void goToCity(ActionEvent event) {

        stopWorldTime();
        attackSelect = null;
        soundManager.stopTheSound();
        
        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");
        
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        worldTime();
        loadEnemyStatsFromDataStorage();
        loadHeroStatsFromDataStorage();
        whatHeroToLoad();
        XPBAR();
        createEnemy();
        calculateAttackOrder();
        selectEnemy();

        System.out.println("heroEXP" + HeroDataStorage.getInstance().getHero().getEXP());
        
        startSound();
        
    }

    public void XPBAR() {

        int maxXpWidth = 763;
        currentXP = HeroDataStorage.getInstance().getHero().getEXP();
        heroExpToLevel = HeroDataStorage.getInstance().getHero().getLevel() * 100;

        int calculate;

        XP.setScaleX((HeroDataStorage.getInstance().getHero().getEXP() * maxXpWidth) / heroExpToLevel);
        XP.setX(XP.getScaleX() / 2);
        levelLabel.setText("You´r level are " + HeroDataStorage.getInstance().getHero().getLevel());

        if (currentXP >= heroExpToLevel) {
            heroLevelUp();
            
            XPBAR();
            
        }

    }

    public void spawnCreature(String URL, int creaturePaneWitdh, int creaturePaneHeight, int creaturePaneX, int creaturePaneY, String ID) {

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

    public void createEnemy() {

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

    public void getEnemyValue(int i) {

    }
    

    public void createCreaturePane(ImageView creature, int creaturePaneWitdh, int creaturePaneHeight, int creaturePaneX, int creaturePaneY, String ID) {

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

    public int healthPaneHeroScaler() {

        int currentHP = HeroDataStorage.getInstance().getHero().getHeroCurrentHP();
        int maxHP = HeroDataStorage.getInstance().getHero().getHp();
        int maxImageView = 50;

        int calculate;

        calculate = (currentHP * maxImageView) / maxHP; // Fullt fungerande, bara till alla kalla metoden varje tick
        return calculate;
    }

    public int healthPaneCreatureScaler() { //kan någon fixa?

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

    public void healthPaneScaleInGame() {

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

    public void whatHeroToLoad() {

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

    public void selectEnemy() {

        attackSelect = selectEnemy(creaturePane1, creaturePane2, creaturePane3, creaturePane4); // första är den som selectas
        attackSelect = selectEnemy(creaturePane2, creaturePane3, creaturePane1, creaturePane4);
        attackSelect = selectEnemy(creaturePane3, creaturePane2, creaturePane1, creaturePane4);
        attackSelect = selectEnemy(creaturePane4, creaturePane2, creaturePane1, creaturePane3);

    }

    public void worldTime() {
        timeline = new Timeline(new KeyFrame(
                Duration.millis(100),
                ae -> handleWorldTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void handleWorldTime() {

        healthPaneScaleInGame(); //Scala hpBar med worldtime
        //KillEnemyDisplay(); //Kolla om fienden är död
        checkIfEnemysTurn(); //Kolla om det är fienden tur, isf attakera heron
        removeDeadEnemysFromArrayList();

    }

    public void stopWorldTime() {

        timeline.stop();
    }

    public String selectEnemy(AnchorPane pane, AnchorPane pane2, AnchorPane pane3, AnchorPane pane4) {
        pane.setOnMouseClicked((MouseEvent e) -> {
            pane.blendModeProperty().set(BlendMode.HARD_LIGHT);
            pane2.blendModeProperty().set(BlendMode.SRC_OVER);
            pane3.blendModeProperty().set(BlendMode.SRC_OVER);
            pane4.blendModeProperty().set(BlendMode.SRC_OVER);
            attackSelect = pane.getId();
            if (attackOrder.get(0).equals("Hero")) {
                selectEnemyToAttack();
                heroAttack();
                KillEnemyDisplay();
                checkIfEnemyIsDead();
                attackOrder.remove(0); // kolla om det är heros tur, ta väck honom i ordningen
            } else {
                System.out.println("Det är inte din tur idiot");

            }
            //System.out.println(attackSelect); //Debugg för att kolla vilken fiende man trycker på
        });
        return attackSelect; //returna den man trycker på
    }

    public void heroAttack() {
        heroChar.heroAttack();
        System.out.println("Heron Lyckades Attakera");
    }

    public void KillEnemyDisplay() {

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
            ex.printStackTrace();
        }

    }

    public void checkIfEnemyIsDead() {

        if (creaturePane2.isVisible() == false && creaturePane3.isVisible() == false && creaturePane4.isVisible() == false && numberCreature == 3) {

            System.out.println("Victory");
            victory();
            stopWorldTime();
        } else if (creaturePane2.isVisible() == false && creaturePane3.isVisible() == false && numberCreature == 2) {

            System.out.println("Victory");
            victory();
            stopWorldTime();
        } else if (creaturePane2.isVisible() == false && numberCreature == 1) {

            System.out.println("Victory");
            victory();
            stopWorldTime();
        }
    }

    public void killHero() {
        if (heroChar.getHeroCurrentHP() <= 0) {

            creaturePane1.setVisible(false);
            stopWorldTime();
            System.out.println("You dead mofo");
        }
    }

    public void calculateAttackOrder() { //kan någon försöka?

        int heroSpeed = heroChar.getSpeed();
        int heroStartSpeed = heroChar.getSpeed();

        int enemy1Speed = FightDataStorage.getInstance().getEnemy1().getSpeed();
        int enemy1StartSpeed = FightDataStorage.getInstance().getEnemy1().getSpeed();
        int enemy2Speed = 0;
        int enemy2StartSpeed = 0;
        int enemy3Speed = 0;
        int enemy3StartSpeed = 0;
        if (FightDataStorage.getInstance().getEnemy2() != null) {
            enemy2Speed = FightDataStorage.getInstance().getEnemy2().getSpeed();
            enemy2StartSpeed = FightDataStorage.getInstance().getEnemy2().getSpeed();
        }
        if (FightDataStorage.getInstance().getEnemy3() != null) {
            enemy3Speed = FightDataStorage.getInstance().getEnemy3().getSpeed();
            enemy3StartSpeed = FightDataStorage.getInstance().getEnemy3().getSpeed();
        }
        for (int i = 0; i < 500; i++) {
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
            //System.out.println(attackOrder.get(i)); // Hela metoden är bara alfa, inte alls klar, är inte så jävla vass på matte asså...

            if (heroSpeed == 0) {
                heroSpeed = heroStartSpeed;
                enemy1Speed = enemy1StartSpeed;
                enemy2Speed = enemy2StartSpeed;
                enemy3Speed = enemy3StartSpeed;
            }

//            if (enemy1Speed == heroSpeed && enemy1Speed == enemy2Speed && enemy1Speed == enemy3Speed) {
//
//                
//            }
//            if (enemy2Speed == heroSpeed && enemy2Speed == enemy1Speed && enemy2Speed == enemy3Speed) {
//
//                
//            }
//            if (enemy3Speed == heroSpeed && enemy3Speed == enemy1Speed && enemy3Speed == enemy2Speed) {
//
//               
//            }
            //System.out.println(attackOrder.get(i)); // Hela metoden är bara alfa, inte alls klar, är inte så jävla vass på matte asså...
        }
        System.out.println(attackOrder.toString()); // Hela metoden är bara alfa, inte alls klar, är inte så jävla vass på matte asså..
    }

    public void checkIfEnemysTurn() {
        if (attackOrder.get(0).equals("Enemy1") && creaturePane2.isVisible() == true) {

            enemyAttack("Bear", "Scorpion", "Snake", "Spider", "Wolf", 1);
           // System.out.println("Monster attakerade dig, nu har du " + heroChar.getHeroCurrentHP() + " HP");
        }
        if (attackOrder.get(0).equals("Enemy2") && creaturePane3.isVisible() == true) {

            enemyAttack("Bear", "Scorpion", "Snake", "Spider", "Wolf", 2);
            //System.out.println("Monster attakerade dig, nu har du " + heroChar.getHeroCurrentHP() + " HP");
        }
        if (attackOrder.get(0).equals("Enemy3") && creaturePane4.isVisible() == true) {

            enemyAttack("Bear", "Scorpion", "Snake", "Spider", "Wolf", 3);
            //System.out.println("Monster attakerade dig, nu har du " + heroChar.getHeroCurrentHP() + " HP");
        }

    }

    public void removeDeadEnemysFromArrayList() {

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

    public void selectEnemyToAttack() {

        if (attackSelect != null) {

            if (attackSelect.equals("1")) {

                FightDataStorage.getInstance().getEnemy1();
                FightDataStorage.getInstance().setEnemyID("1");

            } else if (attackSelect.equals("2")) {

                FightDataStorage.getInstance().getEnemy2();
                FightDataStorage.getInstance().setEnemyID("2");

            } else if (attackSelect.equals("3")) {

                FightDataStorage.getInstance().getEnemy3();
                FightDataStorage.getInstance().setEnemyID("3");

            }

        }
    }

    public void enemyAttack(String enemy1, String enemy2, String enemy3, String enemy4, String enemy5, int type) {

        String enemyType = "";
        if (type == 1) {
            enemyType = FightDataStorage.getInstance().getEnemy1().getName();
        } else if (type == 2) {
            enemyType = FightDataStorage.getInstance().getEnemy2().getName();
        } else if (type == 3) {
            enemyType = FightDataStorage.getInstance().getEnemy3().getName();
        }

        if (enemyType.equals(enemy1)) {
            heroChar.setHeroCurrentHP(heroChar.getHeroCurrentHP() - EnemyBaseDataStorage.getInstance().getBear().basicAttack());
            attackOrder.remove(0);

        } else if (enemyType.equals(enemy2)) {

            heroChar.setHeroCurrentHP(heroChar.getHeroCurrentHP() - EnemyBaseDataStorage.getInstance().getScorpion().basicAttack());
            attackOrder.remove(0);

        } else if (enemyType.equals(enemy3)) {

            heroChar.setHeroCurrentHP(heroChar.getHeroCurrentHP() - EnemyBaseDataStorage.getInstance().getSnake().basicAttack());
            attackOrder.remove(0);

        } else if (enemyType.equals(enemy4)) {

            heroChar.setHeroCurrentHP(heroChar.getHeroCurrentHP() - EnemyBaseDataStorage.getInstance().getSpider().basicAttack());
            attackOrder.remove(0);

        } else if (enemyType.equals(enemy5)) {

            heroChar.setHeroCurrentHP(heroChar.getHeroCurrentHP() - EnemyBaseDataStorage.getInstance().getWolf().basicAttack());
            attackOrder.remove(0);

        }
        killHero();
    }

    public void victory() {

        int exp = numberCreature * 10;

        HeroDataStorage.getInstance().getHero().setEXP(HeroDataStorage.getInstance().getHero().getEXP() + exp);
        XPBAR();
        getGold();
        System.err.println(HeroDataStorage.getInstance().getHero().getGold());
    }
    public void getGold(){  // for guld efter att man har dödat alla enemys
        int gold =numberCreature * 10 * HeroDataStorage.getInstance().getHero().getLevel();
        HeroDataStorage.getInstance().getHero().setGold(HeroDataStorage.getInstance().getHero().getGold()+ gold);
    }

    private void startSound() {
        try {
            System.out.println("connecting to soundmanager, trying to start Fight sound.");
            soundManager.defineBackgroundSound("Fight");
        } catch (Exception e) {
            System.out.println("failed to start fightsound");
            e.printStackTrace();
        }
    }
    private void heroLevelUp(){
        HeroDataStorage.getInstance().getHero().setLevel(HeroDataStorage.getInstance().getHero().getLevel() + 1);
        HeroDataStorage.getInstance().getHero().setEXP(currentXP - heroExpToLevel);
        HeroDataStorage.getInstance().getHero().setHp(HeroDataStorage.getInstance().getHero().getLevel() * 100);
        HeroDataStorage.getInstance().getHero().setBaseDamage(HeroDataStorage.getInstance().getHero().getLevel() * 5);
    }
}

