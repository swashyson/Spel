/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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

    private Button weapon1 = new Button();
    private final Button weapon2 = new Button();
    private final Button weapon3 = new Button();
    private final Button armor1 = new Button();
    private final Button armor2 = new Button();
    private final Button armor3 = new Button();

    private Weapon weapon;
    private boolean isThereAWeapon;

    Button[] array = new Button[6];

    @FXML
    public void goToCity(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        isThereAWeapon();
        System.out.println("is there a weapon = " + isThereAWeapon);
        
        removeItemsAtStart(weapon1, 1);

        buyItem(weapon1, 1); // du måste ha värden för vapen i din databas, första vapnet id = 1
        buyItem(weapon2, 2); // id = 2

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

    public void buyItem(Button button, int weaponID) {

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                try {

                    System.out.println("Buy");
                    DBConnect.connect();

                    ResultSet rs = DBConnect.CreateSelectStatement("select * from game.weapon where weaponID = '" + weaponID + "'");

                    if (rs.next()) {

                        String weaponName = rs.getString("weaponName");
                        int weaponMinDamage = rs.getInt("weaponMinDamage");
                        int weaponMaxDamage = rs.getInt("weaponMaxDamage");
                        int weaponSpeed = rs.getInt("weaponSpeed");
                        int weaponlevel = rs.getInt("weaponLevel");
                        int weaponType = rs.getInt("weaponType");

                        weapon = new Weapon(weaponName, weaponID, weaponMinDamage, weaponMaxDamage, weaponSpeed, weaponlevel, weaponType);
                        setWeaponToHero(weapon, weapon1);

                        DBConnect.close();

                        removeWeapon(button, weaponID);

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });

    }

    public void setWeaponToHero(Weapon weapon, Button button) {

        DBConnect.connect();

        DataStorage.getInstance().setWeapon(weapon);
        int weaponID = DataStorage.getInstance().getWeapon().getWeaponID();
        int heroID = DataStorage.getInstance().getHero().getHeroID();

        try {
            ResultSet rs = DBConnect.CreateSelectStatement("select * from hero_has_weapon where hero_idHero = '" + heroID + "';");
            if (rs.next()) {
                DBConnect.createAlterStatement("UPDATE game.hero_has_weapon SET weapon_weaponID='" + weaponID + "' WHERE hero_idHero='" + heroID + "';");

            } else {
                DBConnect.CreateInsertStatement("INSERT INTO game.hero_has_weapon (hero_idHero, weapon_weaponID) VALUES ('" + heroID + "', '" + weaponID + "');", null, null); //fel label och fel text = null null
                System.out.println("INSERT INTO 'game'.'hero_has_weapon' ('hero_idHero', 'weapon_weaponID') VALUES ('" + heroID + "', '" + weaponID + "');");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        DBConnect.close();
    }

    public void removeWeapon(Button button, int weaponID) {

        if (DataStorage.getInstance().getWeapon().getWeaponID() == weaponID) {

            button.setVisible(false);
        }
    }

    public boolean isThereAWeapon() {

        if (DataStorage.getInstance().getWeapon() == null) {

            isThereAWeapon = false;
        } else {
            isThereAWeapon = true;
        }
        return isThereAWeapon;
    }
    public void removeItemsAtStart(Button button, int WeaponID){
    
        if(isThereAWeapon == true){
        
            if(DataStorage.getInstance().getWeapon().getWeaponID() == WeaponID){
                button.setVisible(false);
            
            }
        }
    }

}
