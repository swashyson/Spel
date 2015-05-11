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
    private Enemy[] enemys;  
    private Hero heroChar;
    private Enemy enemy;

    AnchorPane creaturePane;

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
        fight();
        XPBAR();
        whatHeroToLoad();

    }

    public void XPBAR() {

        XP.setScaleX(heroEXP);
        XP.setX(XP.getScaleX() / 2);

    }

    public void spawnCreature(String URL, int creaturePaneWitdh, int creaturePaneHeight, int creaturePaneX, int creaturePaneY) {

        ImageView hero = new ImageView();
        Image heroDisplay = new Image(getClass().getResourceAsStream(URL));
        hero.setImage(heroDisplay);
        
            createCreaturePane(hero, creaturePaneWitdh, creaturePaneHeight, creaturePaneX, creaturePaneY);
            pane.getChildren().add(creaturePane);
        

    }

    public void loadHeroStatsFromDataStorage() {

        heroChar = HeroDataStorage.getInstance().getHero();
        heroEXP = HeroDataStorage.getInstance().getHero().getEXP();

    }

    public void loadEnemyStatsFromDataBase() {

        //Ladda in enemy
    }

    public void fight() {
        heroChar.fightMonster(heroChar, enemy);
        heroChar.heroTimeStart();

    }

    public void createEnemy() {

        // Ska vara en random generator här beroende på vilken lvl osv man är
        enemy = new Bear("Bear",1,1,1,1);
        spawnCreature("Recourses/Bear.png", 40, 60, 730, 500); // spawna en fiende på dessa positionerna med en pane
        
    }

    public void createCreaturePane(ImageView creature, int creaturePaneWitdh, int creaturePaneHeight, int creaturePaneX, int creaturePaneY) {

        creaturePane = new AnchorPane();
        creaturePane.prefWidth(creaturePaneWitdh);
        creaturePane.prefHeight(creaturePaneHeight); //Storlek på pane
        creaturePane.setLayoutX(creaturePaneX);
        creaturePane.setLayoutY(creaturePaneY);

        ImageView hpBar = new ImageView();
        Image imageHealth = new Image(getClass().getResourceAsStream("Recourses/HealthBar.png"));
        hpBar.setImage(imageHealth);

        creaturePane.getChildren().add(hpBar);
        creaturePane.getChildren().add(creature);

        hpBar.setScaleX(healthPaneScaler());
        hpBar.setX(healthPaneScaler() / 2);

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
