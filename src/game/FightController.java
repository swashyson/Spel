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
        loadStatsFromDataStorage();
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

    public void loadStatsFromDataStorage() {

        heroChar = DataStorage.getInstance().getHero();
        heroEXP = DataStorage.getInstance().getHero().getEXP();

    }

    public void loadStatsFromDataBase() {

        //Ladda in enemy
    }

    public void fight() {
        heroChar.fightMonster(heroChar, enemy);
        heroChar.heroTimeStart();

    }

    public void createEnemy() {

        // Ska vara en random generator här beroende på vilken lvl osv man är
        enemy = new Bear();
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

        int currentHP = DataStorage.getInstance().getHero().getHeroCurrentHP();
        int maxHP = DataStorage.getInstance().getHero().getHp();
        int maxImageView = 50;

        int calculate;

        calculate = (currentHP * maxImageView) / maxHP; // Fullt fungerande, bara till alla kalla metoden varje tick
        return calculate;
    }

    public void healthPaneSetPosX() {

        //Ska flytta scaleX hit från createCreaturePan
    }

    public void whatHeroToLoad() {

        if (DataStorage.getInstance().getHero().getHeroType() == 1) {

            spawnCreature("Recourses/WarriorChar.png", 40, 60, 30, 500);

        } else if (DataStorage.getInstance().getHero().getHeroType() == 2) {

            spawnCreature("Recourses/RangerChar.png", 40, 60, 30, 500);

        } else if (DataStorage.getInstance().getHero().getHeroType() == 3) {

            spawnCreature("Recourses/MageChar.png", 40, 60, 30, 500);

        }

    }

}
