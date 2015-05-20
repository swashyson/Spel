/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import DataStorage.HeroDataStorage;
import Items.Armor;
import Items.Weapon;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class HoverMouse implements HoverMouseInterface{

    private static HoverMouse hoverMouse;

    private final static ArrayList<Object> buyItems = new ArrayList();

    private int getclass;

    private Weapon weapon;
    private Armor armor;
    private ArrayList<Weapon> weaponList;
    private ArrayList<Armor> armorList;
    
    private String attackSelect;

    public static HoverMouse getInstance() {
        if (hoverMouse == null) {
            hoverMouse = new HoverMouse();
        }

        return hoverMouse;
    }

    @Override
    public void inHover(Button button) {
        button.setOnMouseEntered((MouseEvent e) -> {
            button.blendModeProperty().setValue(BlendMode.HARD_LIGHT);
        });
    }

    @Override
    public void outHover(Button button) {
        button.setOnMouseExited((MouseEvent e) -> {
            button.blendModeProperty().setValue(BlendMode.SRC_OVER);
        });
    }

    @Override
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
                         int weaponGold = rs.getInt("WeaponGold");

                        weapon = new Weapon(weaponName, weaponID, weaponMinDamage, weaponMaxDamage, weaponSpeed, weaponLevel, weaponType, weaponGold);
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
                        int armorGold = rs.getInt("armorGold");
                        armor = new Armor(armorName, armorID, armorValue, armorType, armorLevel, armorSpeed, armorGold);
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

    @Override
    public void outHoverSize(Button button, ListView list) {
        button.setOnMouseExited((MouseEvent e) -> {
            button.setScaleX(1);
            button.setScaleY(1);

            buyItems.removeAll(buyItems);
            ObservableList<Object> OL = FXCollections.observableArrayList(buyItems);
            list.setItems(OL);
        });

    }

    @Override
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

    @Override
    public void handeClickEffect(Button button) {

        button.setScaleX(1);
        button.setScaleY(1);

    }

    @Override
    public void inClick(Button button) {
        button.blendModeProperty().setValue(BlendMode.HARD_LIGHT);
    }

    @Override
    public void outClick(Button button) {
        button.blendModeProperty().setValue(BlendMode.SRC_OVER);
    }

    @Override
    public void buyWeaponsAdd(ListView list, int index) {
        buyItems.add("Weapon Stats");
        buyItems.add("Weapon name: " + weaponList.get(index).getName());
        buyItems.add("Weapon MinDamage: " + weaponList.get(index).getWeaponMinDamage());
        buyItems.add("Weapon MaxDamage: " + weaponList.get(index).getWeaponMaxDamage());
        buyItems.add("Weapon Speed: " + weaponList.get(index).getWeaponSpeed());
        buyItems.add("Price: " + weaponList.get(index).getWeaponGold());
        
        ObservableList<Object> OL = FXCollections.observableArrayList(buyItems);
        list.setItems(OL);
    }

    @Override
    public void buyArmorsAdd(ListView list, int index) {
        buyItems.add("Armor Stats");
        buyItems.add("Armor Name: " + armorList.get(index).getName());
        buyItems.add("Armor Value: " + armorList.get(index).getArmor());
        buyItems.add("Armor Speed: " + armorList.get(index).getArmorSpeed());
        buyItems.add("Armor Level: " + armorList.get(index).getArmorLevel());
        buyItems.add("Price:: " + armorList.get(index).getArmorGold());
        
        ObservableList<Object> OL = FXCollections.observableArrayList(buyItems);
        list.setItems(OL);
    }

    @Override
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
