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
    private AnchorPane creaturePane1 = new AnchorPane();
    private AnchorPane creaturePane2 = new AnchorPane();
    private AnchorPane creaturePane3 = new AnchorPane();
    private AnchorPane creaturePane4;

    private ImageView hpBarCreature1 = new ImageView();
    private ImageView hpBarCreature2 = new ImageView();
    private ImageView hpBarCreature3 = new ImageView();
    private String attackSelect;
    private ArrayList<String> attackOrder = new ArrayList();

    private Timeline timeline;
    private int numberCreature;

    @FXML
    public void goToCity(ActionEvent event) {

        stopWorldTime();
        attackSelect = null;

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadEnemyStatsFromDataStorage();

        loadHeroStatsFromDataStorage();
        XPBAR();
        whatHeroToLoad();
        createEnemy();
        selectEnemy();
        worldTime();
        calculateAttackOrder();
    }

    public void XPBAR() {

        XP.setScaleX(heroEXP);
        XP.setX(XP.getScaleX() / 2);

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
        numberCreature = rand.nextInt(2) + 1;
        System.out.print("antal djur" + numberCreature);
        for (int i = 0; i < numberCreature; i++) {
            int whatCreature = rand.nextInt(4) + 1;

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
                case 0:
                    pane.getChildren().add(creaturePane2);
                    FightDataStorage.getInstance().setEnemy1(enemy);
                    spawnCreature(pic, 40, 60, 730, 500, "1");
                    break;
                case 1:
                    pane.getChildren().add(creaturePane3);
                    FightDataStorage.getInstance().setEnemy2(enemy);
                    spawnCreature(pic, 40, 60, 730, 400, "2");
                    break;
                case 2:
                    FightDataStorage.getInstance().setEnemy3(enemy);
                    spawnCreature(pic, 40, 60, 730, 300, "3");
                    break;
            }
        }

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

            return 30;
        }
        if (attackSelect.equals("2")) { //Procent beräkningar

            return 10;
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
            } else if (attackSelect == null) {
                System.out.println("Null");
            }
        } catch (Exception ex) {
            System.out.println("Kan inte ladda innan world time startats");
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

        attackSelect = selectEnemy(creaturePane1, creaturePane2, creaturePane3); // första är den som selectas
        attackSelect = selectEnemy(creaturePane2, creaturePane3, creaturePane1);
        attackSelect = selectEnemy(creaturePane3, creaturePane2, creaturePane1);

    }

    public void worldTime() {
        timeline = new Timeline(new KeyFrame(
                Duration.millis(100),
                ae -> handleWorldTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void handleWorldTime() {

        if (attackSelect != null) {

            if (attackSelect.equals("1")) {

                FightDataStorage.getInstance().getEnemy1();
                FightDataStorage.getInstance().setEnemyID("1");

            } else if (attackSelect.equals("2")) {

                FightDataStorage.getInstance().getEnemy2();
                FightDataStorage.getInstance().setEnemyID("2");

            }

        }
        healthPaneScaleInGame(); //Scala hpBar med worldtime
        KillEnemyDisplay(); //Kolla om fienden är död
        checkIfEnemysTurn(); //Kolla om det är fienden tur, isf attakera heron
        removeDeadEnemysFromArrayList();

    }

    public void stopWorldTime() {

        timeline.stop();
    }

    public String selectEnemy(AnchorPane pane, AnchorPane pane2, AnchorPane pane3) {
        pane.setOnMouseClicked((MouseEvent e) -> {
            pane.blendModeProperty().set(BlendMode.HARD_LIGHT);
            pane2.blendModeProperty().set(BlendMode.SRC_OVER);
            pane3.blendModeProperty().set(BlendMode.SRC_OVER);
            attackSelect = pane.getId();
            if (attackOrder.get(0).equals("Hero")) {
                heroAttack();
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
            if (DataStorage.FightDataStorage.getInstance().getEnemy1().getHp() <= 0) {

                creaturePane2.setVisible(false);
            }
            if (FightDataStorage.getInstance().getEnemy2() != null) {
                if (FightDataStorage.getInstance().getEnemy2().getHp() <= 0) {

                    creaturePane3.setVisible(false);
                }
            }
            if (DataStorage.FightDataStorage.getInstance().getEnemy1() != null && DataStorage.FightDataStorage.getInstance().getEnemy2() == null && creaturePane2.isVisible() == false) {
                System.out.println("Victory");
                stopWorldTime();
            }
            if (DataStorage.FightDataStorage.getInstance().getEnemy1() != null && DataStorage.FightDataStorage.getInstance().getEnemy2() != null && creaturePane2.isVisible() == false && creaturePane3.isVisible() == false) {

                System.out.println("Victory");
                stopWorldTime();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void calculateAttackOrder() { //kan någon försöka?

        int heroSpeed = heroChar.getSpeed();
        int heroStartSpeed = heroChar.getSpeed();

        int enemy1Speed = FightDataStorage.getInstance().getEnemy1().getSpeed();
        int enemy1StartSpeed = FightDataStorage.getInstance().getEnemy1().getSpeed();
        int enemy2Speed =0;
        int enemy2StartSpeed = 0;
        if(FightDataStorage.getInstance().getEnemy2() != null){
        enemy2Speed = FightDataStorage.getInstance().getEnemy2().getSpeed();
        enemy2StartSpeed= FightDataStorage.getInstance().getEnemy2().getSpeed();
        }
        for (int i = 0; i < 500; i++) {
            if (heroSpeed >= enemy1Speed && heroSpeed >= enemy2Speed) {

                attackOrder.add("Hero");
                heroSpeed = heroSpeed - 1;
            }
            if (enemy1Speed > heroSpeed && enemy1Speed >= enemy2Speed) {

                attackOrder.add("Enemy1");
                enemy1Speed = enemy1Speed - 1;
            }
            if (enemy2Speed > heroSpeed && enemy2Speed > enemy1Speed) {

                attackOrder.add("Enemy2");
                enemy2Speed = enemy2Speed - 1;
            }
            System.out.println(attackOrder.get(i)); // Hela metoden är bara alfa, inte alls klar, är inte så jävla vass på matte asså...

            if (heroSpeed == 0 ) {

                heroSpeed = heroStartSpeed;
            }
            if (enemy1Speed == 0) {

                enemy1Speed = enemy1StartSpeed;
            }
            if (enemy2Speed == 0) {

                enemy2Speed = enemy2StartSpeed;
            }
        }

    }

    public void checkIfEnemysTurn() {
        if (attackOrder.get(0).equals("Enemy1") && creaturePane2.isVisible() == true) {

            attackOrder.remove(0);
            System.out.println(FightDataStorage.getInstance().getEnemy1().getName() + " Skadade dig");
        }
        if (attackOrder.get(0).equals("Enemy2") && numberCreature == 2 && creaturePane3.isVisible() == true) {

            attackOrder.remove(0);
            System.out.println(FightDataStorage.getInstance().getEnemy2().getName() + " Skadade dig");
        }
    }

    public void removeDeadEnemysFromArrayList() {

        try {
            for (int i = 0; i < attackOrder.size(); i++) {

                if (creaturePane2.isVisible() == false) {

                    if (attackOrder.get(i).equals("Enemy1")) {
                        attackOrder.remove(i);
                    }
                }
                if (creaturePane3.isVisible() == false) {

                    if (attackOrder.get(i).equals("Enemy2")) {

                        attackOrder.remove(i);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Array out of bounds (Alla fiender är döda)");
        }
    }

}
