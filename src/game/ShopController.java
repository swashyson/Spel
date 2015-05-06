/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.Effect;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class ShopController implements Initializable {

    @FXML
    private Button backToCity;

    @FXML
    private AnchorPane pane;

    @FXML
    private ListView current;
    @FXML
    private ListView buy;

    private final Button weapon1 = new Button();
    private final Button weapon2 = new Button();
    private final Button weapon3 = new Button();
    private final Button armor1 = new Button();
    private final Button armor2 = new Button();
    private final Button armor3 = new Button();

    private Weapon weapon;
    private Armor armor;

    private final Button[] array = new Button[6];
    private final ArrayList<Object> currentItems = new ArrayList();
    private final ArrayList<Object> buyItems = new ArrayList();

    @FXML
    public void goToCity(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        startMethod();

        int i = 1;
        for (Button array1 : array) {
            HoverMouse.getInstance().inHoverSize(array1, i, buy);
            HoverMouse.getInstance().outHoverSize(array1, buy);
            i = i + 1;
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

    public void createItem(Button item, int x, int y, String URL) {

        item.setLayoutX(x);
        item.setLayoutY(y);

        Image image = new Image(getClass().getResourceAsStream(URL));

        Effect effect = new ImageInput(image);

        item.setEffect(effect);
        pane.getChildren().add(item);

    }

    public void buyWeapon(Button button, int weaponID) {

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                try {

                    System.out.println("Buy Weapon");
                    DBConnect.connect();

                    ResultSet rs = DBConnect.CreateSelectStatement("select * from game.weapon where weaponID = '" + weaponID + "'");

                    if (rs.next()) {

                        String weaponName = rs.getString("weaponName");
                        int weaponMinDamage = rs.getInt("weaponMinDamage");
                        int weaponMaxDamage = rs.getInt("weaponMaxDamage");
                        int weaponSpeed = rs.getInt("weaponSpeed");
                        int weaponlevel = rs.getInt("weaponLevel");
                        int weaponType = rs.getInt("weaponType");

                        if (levelReq(weaponlevel) == true) {

                            weapon = new Weapon(weaponName, weaponID, weaponMinDamage, weaponMaxDamage, weaponSpeed, weaponlevel, weaponType);
                            setWeaponToHero(weapon, weapon1);
                            removeWeapon();
                            listViewGetCurrentItems();

                        } else {
                            System.out.println("Du är för låg level blabla");
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    DBConnect.close();
                }
            }

        });

    }

    public void buyArmor(Button button, int armorID) {

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                try {

                    System.out.println("Buy Armor");
                    DBConnect.connect();

                    ResultSet rs = DBConnect.CreateSelectStatement("select * from game.armor where armorID = '" + armorID + "'");

                    if (rs.next()) {

                        String armorName = rs.getString("armorName");
                        int localArmor = rs.getInt("armor");
                        int armorType = rs.getInt("armorType");
                        int armorLevel = rs.getInt("armorLevel");
                        int armorSpeed = rs.getInt("armorSpeed");

                        if (levelReq(armorLevel) == true) {

                            armor = new Armor(armorName, armorID, localArmor, armorType, armorLevel, armorSpeed);
                            setArmorToHero(armor, armor1);

                            removeArmor();
                            listViewGetCurrentItems();
                        } else {
                            System.out.println("Du är för låg level blabla");
                        }

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    DBConnect.close();
                }
            }

        });

    }

    public void setWeaponToHero(Weapon armor, Button button) {

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
        } finally {
            DBConnect.close();
        }

    }

    public void setArmorToHero(Armor armor, Button button) {

        DBConnect.connect();

        DataStorage.getInstance().setArmor(armor);
        int localArmorID = DataStorage.getInstance().getArmor().getArmorID();
        int heroID = DataStorage.getInstance().getHero().getHeroID();

        try {
            ResultSet rs = DBConnect.CreateSelectStatement("select * from hero_has_armor where hero_idHero = '" + heroID + "';");
            if (rs.next()) {
                DBConnect.CreateAlterStatement("UPDATE game.hero_has_armor SET armor_armorID='" + localArmorID + "' WHERE hero_idHero='" + heroID + "';");

            } else {
                DBConnect.CreateInsertStatement("INSERT INTO game.hero_has_armor (hero_idHero, armor_armorID) VALUES ('" + heroID + "', '" + localArmorID + "');", null, null); //fel label och fel text = null null
                System.out.println("INSERT INTO 'game'.'hero_has_armor' ('hero_idHero', 'armor_armorID') VALUES ('" + heroID + "', '" + localArmorID + "');");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.close();
        }
    }

    public void removeWeapon(Button button, Button button2, Button button3, int WeaponID) {

        if (DataStorage.getInstance().getWeapon() != null) {

            System.out.println("Du har ett vapen");

            if (DataStorage.getInstance().getWeapon().getWeaponID() == WeaponID) {
                button.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);

            }
        }
    }

    public void removeArmor(Button button, Button button2, Button button3, int armorID) {

        if (DataStorage.getInstance().getArmor() != null) {

            System.out.println("Du har en armor");

            if (DataStorage.getInstance().getArmor().getArmorID() == armorID) {
                button.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);

            }
        }
    }

    public void startMethod() {

        removeWeapon();
        removeArmor();

        buyWeapon(weapon1, 1); // du måste ha värden för vapen i din databas, första vapnet id = 1
        buyWeapon(weapon2, 2); // id = 2
        buyWeapon(weapon3, 3);

        buyArmor(armor1, 1);
        buyArmor(armor2, 2);
        buyArmor(armor3, 3);

        array[0] = weapon1;
        array[1] = weapon2;
        array[2] = weapon3;
        array[3] = armor1;
        array[4] = armor2;
        array[5] = armor3;

        listViewGetCurrentItems();

    }

    public void listViewGetCurrentItems() {

        if (DataStorage.getInstance().getWeapon() != null) {
            currentItems.add("Your Weapon");
            currentItems.add("____________________________________");
            currentItems.add("Weapon Name: " + DataStorage.getInstance().getWeapon().getName());
            currentItems.add("Weapon Min Damage: " + DataStorage.getInstance().getWeapon().getWeaponMinDamage());
            currentItems.add("Weapon Max Damage: " + DataStorage.getInstance().getWeapon().getWeaponMaxDamage());
            currentItems.add("Weapon Speed: " + DataStorage.getInstance().getWeapon().getWeaponSpeed());
        } else {
            currentItems.add("You dont have a weapon");
        }
        if (DataStorage.getInstance().getArmor() != null) {

            currentItems.add("Your Armor");
            currentItems.add("____________________________________");
            currentItems.add("Armor Name: " + DataStorage.getInstance().getArmor().getName());
            currentItems.add("Armor Value: " + DataStorage.getInstance().getArmor().getArmor());
            currentItems.add("Armor Speed: " + DataStorage.getInstance().getArmor().getArmorSpeed());

        } else {
            currentItems.add("You dont have a armor set");
        }
        ObservableList<Object> OL = FXCollections.observableArrayList(currentItems);
        current.setItems(OL);
        currentItems.removeAll(currentItems);

    }

    public void removeWeapon() {

        removeWeapon(weapon1, weapon1, weapon1, 1);
        removeWeapon(weapon1, weapon2, weapon2, 2);
        removeWeapon(weapon1, weapon2, weapon3, 3);

    }

    public void removeArmor() {

        removeArmor(armor1, armor1, armor1, 1);
        removeArmor(armor1, armor2, armor2, 2);
        removeArmor(armor1, armor2, armor3, 3);

    }

    public boolean levelReq(int itemLevel) {

        if (DataStorage.getInstance().getHero().getLevel() < itemLevel) {
            return false;
        }
        return true;

    }
}
