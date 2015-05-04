/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.URL;
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

    private final Button weapon1 = new Button();
    private final Button weapon2 = new Button();
    private final Button weapon3 = new Button();
    private final Button armor1 = new Button();
    private final Button armor2 = new Button();
    private final Button armor3 = new Button();

    private Weapon weapon;
    private boolean hasWeapon = false;
    private int weaponID;

    private final Button[] array = new Button[6];

    @FXML
    public void goToCity(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        checkWeapon();
        System.out.println("is there a weapon = " + hasWeapon);

        removeItemsAtStart(weapon1, weapon1, weapon1, 1);
        removeItemsAtStart(weapon1, weapon2, weapon2, 2);
        removeItemsAtStart(weapon1, weapon2, weapon3, 3);

        buyItem(weapon1, 1); // du måste ha värden för vapen i din databas, första vapnet id = 1
        buyItem(weapon2, 2); // id = 2
        buyItem(weapon3, 3);

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

        createItem(weapon1, 50, 100, "Recourses/Sword1.png");
        createItem(weapon2, 150, 100, "Recourses/Sword2.png");
        createItem(weapon3, 250, 100, "Recourses/Sword3.png");
        createItem(armor1, 400, 150, "Recourses/Armor1.png");
        createItem(armor2, 500, 150, "Recourses/Armor2.png");
        createItem(armor3, 600, 150, "Recourses/Armor3.png");

    }

    public void bowman() {

        createItem(weapon1, 50, 100, "Recourses/Sword1.png");
        createItem(weapon2, 150, 100, "Recourses/Sword2.png");
        createItem(weapon3, 250, 100, "Recourses/Sword3.png");
        createItem(armor1, 400, 150, "Recourses/Armor1.png");
        createItem(armor2, 500, 150, "Recourses/Armor2.png");
        createItem(armor3, 600, 150, "Recourses/Armor3.png");

    }

    public void wizard() {

        createItem(weapon1, 50, 100, "Recourses/Sword1.png");
        createItem(weapon2, 150, 100, "Recourses/Sword2.png");
        createItem(weapon3, 250, 100, "Recourses/Sword3.png");
        createItem(armor1, 400, 150, "Recourses/Armor1.png");
        createItem(armor2, 500, 150, "Recourses/Armor2.png");
        createItem(armor3, 600, 150, "Recourses/Armor3.png");

    }

    public void createItem(Button weapon, int x, int y, String URL) {

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
        int LocalWeaponID = DataStorage.getInstance().getWeapon().getWeaponID();
        int heroID = DataStorage.getInstance().getHero().getHeroID();

        try {
            ResultSet rs = DBConnect.CreateSelectStatement("select * from hero_has_weapon where hero_idHero = '" + heroID + "';");
            if (rs.next()) {
                DBConnect.CreateAlterStatement("UPDATE game.hero_has_weapon SET weapon_weaponID='" + LocalWeaponID + "' WHERE hero_idHero='" + heroID + "';");

            } else {
                DBConnect.CreateInsertStatement("INSERT INTO game.hero_has_weapon (hero_idHero, weapon_weaponID) VALUES ('" + heroID + "', '" + LocalWeaponID + "');", null, null); //fel label och fel text = null null
                System.out.println("INSERT INTO 'game'.'hero_has_weapon' ('hero_idHero', 'weapon_weaponID') VALUES ('" + heroID + "', '" + LocalWeaponID + "');");

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

    public void removeItemsAtStart(Button button, Button button2,Button button3, int WeaponID) {

        if (hasWeapon == true) {

            if (DataStorage.getInstance().getWeapon().getWeaponID() == WeaponID) {
                button.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);

            }
        }
    }

    public void checkWeapon() {

        DBConnect.connect();

        int heroID = DataStorage.getInstance().getHero().getHeroID();

        ResultSet rs = DBConnect.CreateSelectStatement("select * from game.hero_has_weapon where hero_idHero = '" + heroID + "';");
        try {
            if (rs.next()) {
                weaponID = rs.getInt("weapon_weaponID");
                hasWeapon = true;
                System.err.println(weaponID);
            }
            ResultSet check = DBConnect.CreateSelectStatement("select * from game.weapon where weaponID = '" + weaponID + "';");
            System.out.println("select * from game.weapon where weaponID = '" + weaponID + "';");
            if (check.next()) {

                String weaponName = check.getString("weaponName");
                int weaponMinDamage = check.getInt("weaponMinDamage");
                int weaponMaxDamage = check.getInt("weaponMaxDamage");
                int weaponSpeed = check.getInt("weaponSpeed");
                int weaponlevel = check.getInt("weaponLevel");
                int weaponType = check.getInt("weaponType");

                weapon = new Weapon(weaponName, weaponID, weaponMinDamage, weaponMaxDamage, weaponSpeed, weaponlevel, weaponType);
                DataStorage.getInstance().setWeapon(weapon);

                System.out.println(weapon);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
