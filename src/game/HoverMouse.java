/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import Items.Weapon;
import Items.Armor;
import DataStorage.HeroDataStorage;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class HoverMouse {

    private static HoverMouse hoverMouse;

    private final static ArrayList<Object> buyItems = new ArrayList();

    private int getclass;

    private Weapon weapon;
    private Armor armor;
    ArrayList<Weapon> weaponList;
    ArrayList<Armor> armorList;

    public static HoverMouse getInstance() {
        if (hoverMouse == null) {
            hoverMouse = new HoverMouse();
        }

        return hoverMouse;
    }

    public void inHover(Button button) {
        button.setOnMouseEntered((MouseEvent e) -> {
            button.blendModeProperty().setValue(BlendMode.HARD_LIGHT);
        });
    }

    public void outHover(Button button) {
        button.setOnMouseExited((MouseEvent e) -> {
            button.blendModeProperty().setValue(BlendMode.SRC_OVER);
        });
    }

    public void inHoverSize(Button button, int ID, ListView list) {
        button.setOnMouseEntered((MouseEvent e) -> {
            button.setScaleX(2);
            button.setScaleY(2);
            DBConnect.connect();

            try {
                getclass = HeroDataStorage.getInstance().getHero().getHeroType();
                weaponList = new ArrayList<>();
                armorList = new ArrayList<>();

                if (ID >= 1 && ID <= 3) {   //OM DET ÄR ETT VAPEN MED ANDRA ORD
                    ResultSet rs = DBConnect.CreateSelectStatement("select * from weapon where weapontype = '" + getclass + "';");
                    while (rs.next()) {

                        String weaponName = rs.getString("weaponName");
                        int weaponMinDamage = rs.getInt("weaponMinDamage");
                        int weaponMaxDamage = rs.getInt("weaponMaxDamage");
                        int weaponSpeed = rs.getInt("weaponSpeed");
                        int weaponLevel = rs.getInt("weaponLevel");
                        int weaponID = rs.getInt("WeaponID");
                        int weaponType = rs.getInt("WeaponType");

                        weapon = new Weapon(weaponName, weaponID, weaponMinDamage, weaponMaxDamage, weaponSpeed, weaponLevel, weaponType);
                        weaponList.add(weapon); // lägger till alla vapen från databasen i en arraylista
                    }

                    ifArmorOrWeapon(ID, list);

                } else if (ID >= 4 && ID < 7) { // ARMOR
                    ResultSet rs = DBConnect.CreateSelectStatement("select * from armor where armortype = '" + getclass + "';");

                    while (rs.next()) {

                        String armorName = rs.getString("armorName");
                        int armorSpeed = rs.getInt("armorSpeed");
                        int armorLevel = rs.getInt("armorLevel");
                        int armorType = rs.getInt("armorType");
                        int armorValue = rs.getInt("armor");
                        int armorID = rs.getInt("armorID");

                        armor = new Armor(armorName, armorID, armorValue, armorType, armorLevel, armorSpeed);
                        armorList.add(armor);
                    }
                    
                    ifArmorOrWeapon(ID, list);

                }

                DBConnect.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void outHoverSize(Button button, ListView list) {
        button.setOnMouseExited((MouseEvent e) -> {
            button.setScaleX(1);
            button.setScaleY(1);

            buyItems.removeAll(buyItems);
            ObservableList<Object> OL = FXCollections.observableArrayList(buyItems);
            list.setItems(OL);
        });

    }

    public void ClickEffect(Button button) {
        button.setOnMouseClicked((MouseEvent e) -> {
            button.setScaleX(1.2);
            button.setScaleY(1.2);
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(500), temp
                    -> handeClickEffect(button)));
            timeline.play();
        });

    }

    public void handeClickEffect(Button button) {

        button.setScaleX(1);
        button.setScaleY(1);

    }

    public void inClick(Button button) {
        button.blendModeProperty().setValue(BlendMode.HARD_LIGHT);
    }

    public void outClick(Button button) {
        button.blendModeProperty().setValue(BlendMode.SRC_OVER);
    }

    public void buyWeaponsAdd(ListView list, int index) {
        buyItems.add("Weapon Stats");
        buyItems.add("Name: " + weaponList.get(index).getName());
        buyItems.add("Weapon Min Damage: " + weaponList.get(index).getWeaponMinDamage());
        buyItems.add("Weapon Max Damage: " + weaponList.get(index).getWeaponMaxDamage());
        buyItems.add("Weapon Speed: " + weaponList.get(index).getWeaponSpeed());
        ObservableList<Object> OL = FXCollections.observableArrayList(buyItems);
        list.setItems(OL);
    }

    public void buyArmorsAdd(ListView list, int index) {
        buyItems.add("Armor Stats");
        buyItems.add("Armor Name: " + armorList.get(index).getName());
        buyItems.add("Armor Value: " + armorList.get(index).getArmor());
        buyItems.add("Armor Speed: " + armorList.get(index).getArmorSpeed());
        buyItems.add("Armor Level: " + armorList.get(index).getArmorLevel());
        ObservableList<Object> OL = FXCollections.observableArrayList(buyItems);
        list.setItems(OL);
    }

    public void ifArmorOrWeapon(int ID, ListView list) {

        if (ID == 1) {
            buyWeaponsAdd(list, 0);

        } else if (ID == 2) {

            buyWeaponsAdd(list, 1);
        } else if (ID == 3) {

            buyWeaponsAdd(list, 2);
        } else if (ID == 4) {

            buyArmorsAdd(list, 0);
        } else if (ID == 5) {

            buyArmorsAdd(list, 1);
        } else if (ID == 6) {

            buyArmorsAdd(list, 2);
        }
    }

}
