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
import javafx.scene.effect.Effect;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class ShopController implements Initializable {

    @FXML
    Button backToCity;

    @FXML
    private AnchorPane pane;

    private final Button weapon1 = new Button();
    private final Button weapon2 = new Button();
    private final Button weapon3 = new Button();
    private final Button armor1 = new Button();
    private final Button armor2 = new Button();
    private final Button armor3 = new Button();

    Button[] array = new Button[6];

    @FXML
    public void goToCity(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        array[0] = weapon1;
        array[1] = weapon2;
        array[2] = weapon3;
        array[3] = armor1;
        array[4] = armor2;
        array[5] = armor3;
        

        for (Button array1 : array) {
            HoverMouse.inHoverSize(array1);
            HoverMouse.outHoverSize(array1);
        }

        if (DataStorage.getInstance().getHero().getHeroType() == 1) {
            warrior();

        } else if (DataStorage.getInstance().getHero().getHeroType() == 2) {
            bowman();

        } else if (DataStorage.getInstance().getHero().getHeroType() == 3) {
            wizard();
        }
    }

    public void warrior() {

        createWeapon(weapon1, 50, 100, "Recourses/Sword1.png");
        createWeapon(weapon2, 150, 100, "Recourses/Sword2.png");
        createWeapon(weapon3, 250, 100, "Recourses/Sword3.png");
        createWeapon(armor1, 400, 150, "Recourses/Armor1.png");
        createWeapon(armor2, 500, 150, "Recourses/Armor2.png");
        createWeapon(armor3, 600, 150, "Recourses/Armor3.png");

    }

    public void bowman() {

        createWeapon(weapon1, 50, 100, "Recourses/Sword1.png");
        createWeapon(weapon2, 150, 100, "Recourses/Sword2.png");
        createWeapon(weapon3, 250, 100, "Recourses/Sword3.png");
        createWeapon(armor1, 400, 150, "Recourses/Armor1.png");
        createWeapon(armor2, 500, 150, "Recourses/Armor2.png");
        createWeapon(armor3, 600, 150, "Recourses/Armor3.png");

    }

    public void wizard() {

        createWeapon(weapon1, 50, 100, "Recourses/Sword1.png");
        createWeapon(weapon2, 150, 100, "Recourses/Sword2.png");
        createWeapon(weapon3, 250, 100, "Recourses/Sword3.png");
        createWeapon(armor1, 400, 150, "Recourses/Armor1.png");
        createWeapon(armor2, 500, 150, "Recourses/Armor2.png");
        createWeapon(armor3, 600, 150, "Recourses/Armor3.png");

    }

    public void createWeapon(Button weapon, int x, int y, String URL) {

        weapon.setLayoutX(x);
        weapon.setLayoutY(y);

        Image image = new Image(getClass().getResourceAsStream(URL));

        Effect effect = new ImageInput(image);

        weapon.setEffect(effect);
        pane.getChildren().add(weapon);

    }

}
