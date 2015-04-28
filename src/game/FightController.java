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
 * @author Hyypes
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

        heroEXP = DataStorage.getInstance().getHeroEXP();
        XPBAR();

        if (DataStorage.getInstance().getHeroType() == 1) {
            
            Warrior("Recourses/Warrior.png");

        }
    }

    public void XPBAR() {

        XP.setScaleX(heroEXP);
        XP.setX(XP.getScaleX() / 2);

    }

    public void Warrior(String URL) {

        ImageView warrior = new ImageView();
        Image warriorDisplay = new Image(getClass().getResourceAsStream(URL));
        warrior.setImage(warriorDisplay);
        
        warrior.setY(500);
        warrior.setX(30);
        
        pane.getChildren().add(warrior);

    }

    public void CreateChar() {

    }

}
