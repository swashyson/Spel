/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import Creature.*;
import Creature.Hero;
import DataStorage.*;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
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

    private Timeline timeline;

    @FXML
    public void goToCity(ActionEvent event) {

        heroChar.heroTimeStop();
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
        int numberCreature = rand.nextInt(2) + 1;
        System.out.print("antal djur" + numberCreature);
        for (int i = 0; i < numberCreature; i++) {
            int whatCreature = rand.nextInt(4) + 1;

            if (whatCreature == 1) {
                enemy = new Bear(EnemyBaseDataStorage.getInstance().getBear().getName(), EnemyBaseDataStorage.getInstance().getBear().getHp(), EnemyBaseDataStorage.getInstance().getBear().getMaxDmg(), EnemyBaseDataStorage.getInstance().getBear().getMinDmg(), EnemyBaseDataStorage.getInstance().getBear().getSpeed());
                pic = "Recourses/Bear.png";
            } else if (whatCreature == 2) {
                enemy = new Scorpion(EnemyBaseDataStorage.getInstance().getScorpion().getName(), EnemyBaseDataStorage.getInstance().getScorpion().getHp(), EnemyBaseDataStorage.getInstance().getScorpion().getMaxDmg(), EnemyBaseDataStorage.getInstance().getScorpion().getMinDmg(), EnemyBaseDataStorage.getInstance().getScorpion().getSpeed());
                pic = "Recourses/Bear.png";
            } else if (whatCreature == 3) {
                enemy = new Snake(EnemyBaseDataStorage.getInstance().getSnake().getName(), EnemyBaseDataStorage.getInstance().getSnake().getHp(), EnemyBaseDataStorage.getInstance().getSnake().getMaxDmg(), EnemyBaseDataStorage.getInstance().getSnake().getMinDmg(), EnemyBaseDataStorage.getInstance().getSnake().getSpeed());
                pic = "Recourses/Bear.png";
            } else if (whatCreature == 4) {
                enemy = new Spider(EnemyBaseDataStorage.getInstance().getSpider().getName(), EnemyBaseDataStorage.getInstance().getSpider().getHp(), EnemyBaseDataStorage.getInstance().getSpider().getMaxDmg(), EnemyBaseDataStorage.getInstance().getSpider().getMinDmg(), EnemyBaseDataStorage.getInstance().getSpider().getSpeed());
                pic = "Recourses/Bear.png";
            } else if (whatCreature == 5) {
                enemy = new Wolf(EnemyBaseDataStorage.getInstance().getWolf().getName(), EnemyBaseDataStorage.getInstance().getWolf().getHp(), EnemyBaseDataStorage.getInstance().getWolf().getMaxDmg(), EnemyBaseDataStorage.getInstance().getWolf().getMinDmg(), EnemyBaseDataStorage.getInstance().getWolf().getSpeed());
                pic = "Recourses/Bear.png";
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

    public int healthPaneCreatureScaler() {

        if (attackSelect == null) {
            return 50;
        }
        if (attackSelect.equals("1")) {

            System.out.println("Enemy1 Set hp: 30");
            return 30;
        }
        if (attackSelect.equals("2")) {

            System.out.println("Enemy2 Set hp : 10");
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
                Duration.millis(2000),
                ae -> handleWorldTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void handleWorldTime() {

        System.out.println("World time Tick tack");
        if (attackSelect != null) {

            if (attackSelect.equals("1")) {

                FightDataStorage.getInstance().getEnemy1();
                FightDataStorage.getInstance().setEnemyID("1");

            } else if (attackSelect.equals("2")) {

                FightDataStorage.getInstance().getEnemy2();
                FightDataStorage.getInstance().setEnemyID("2");

            }

        }
        healthPaneScaleInGame();

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
            Attack();
            System.out.println(attackSelect);
        });
        return attackSelect;
    }

    public void Attack() {
        heroChar.heroTimeStart();
    }
}
