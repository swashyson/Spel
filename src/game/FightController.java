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

    private int heroEXP;

    @FXML
    public void goToCity(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        heroEXP = DataStorage.getInstance().getHero().getEXP();
        XPBAR();

        if (DataStorage.getInstance().getHero().getHeroType() == 1) {

            spawnHero("Recourses/WarriorChar.png");

        } else if (DataStorage.getInstance().getHero().getHeroType() == 2) {
            
            spawnHero("Recourses/RangerChar.png");

        } else if (DataStorage.getInstance().getHero().getHeroType() == 3) {
            
            spawnHero("Recourses/MageChar.png");

        }
    }

    public void XPBAR() {

        XP.setScaleX(heroEXP);
        XP.setX(XP.getScaleX() / 2);

    }

    public void spawnHero(String URL) {

        ImageView hero = new ImageView();
        Image warriorDisplay = new Image(getClass().getResourceAsStream(URL));
        hero.setImage(warriorDisplay);

        hero.setY(500);
        hero.setX(30);

        pane.getChildren().add(hero);

    }

    public void CreateChar() {

    }

}
