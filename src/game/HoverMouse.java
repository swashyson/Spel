/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
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
    private final static int lowerWeaponRange = 1;
    private final static int midWeaponRange = 3;
    private final static int higherWeaponRange = 6;

    private int getclass;
    private int globalAdaper;

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
                getclass = DataStorage.getInstance().getHero().getHeroType();
                getSetAdapter();
                
                if (ID >= lowerWeaponRange && ID <= midWeaponRange) {
                    ResultSet rs = DBConnect.CreateSelectStatement("select * from weapon where weaponID = '" + (ID + globalAdaper) + "';");
                    if (rs.next()) {
                        
                        String weaponName = rs.getString("weaponName");
                        int weaponMinDamage = rs.getInt("weaponMinDamage");
                        int weaponMaxDamage = rs.getInt("weaponMaxDamage");
                        int weaponSpeed = rs.getInt("weaponSpeed");
                        int weaponLevel = rs.getInt("weaponLevel");
                        
                        buyItems.add("Weapon Stats");
                        buyItems.add("Name: " + weaponName);
                        buyItems.add("Weapon Min Damage: " + weaponMinDamage);
                        buyItems.add("Weapon Max Damage: " + weaponMaxDamage);
                        buyItems.add("Weapon Speed: " + weaponSpeed);
                        buyItems.add("Weapon Level: " + weaponLevel);
                        
                    }
                    
                } else if (ID > midWeaponRange && ID <= higherWeaponRange) {
                    
                    int localAdapter = 3;
                    ResultSet rs2 = DBConnect.CreateSelectStatement("select * from armor where armorID = '" + (ID + globalAdaper - localAdapter) + "';");
                    
                    if (rs2.next()) {
                        String armorName = rs2.getString("armorName");
                        int armor = rs2.getInt("armor");
                        int armorLevel = rs2.getInt("armorLevel");
                        int armorSpeed = rs2.getInt("armorSpeed");
                        buyItems.add("Armor Stats");
                        buyItems.add("Name" + armorName);
                        buyItems.add("Armor Value: " + armor);
                        buyItems.add("Armor Speed: " + armorSpeed);
                        buyItems.add("Armor Level: " + armorLevel);
                    }
                }
                //System.out.println(ID);
                ObservableList<Object> OL = FXCollections.observableArrayList(buyItems);
                list.setItems(OL);
                
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

    public void getSetAdapter() {
        if (getclass == 1) {
            globalAdaper = 0;

        } else if (getclass == 2) {
            globalAdaper = 3;
            System.out.println("Ranger");

        } else if (getclass == 3) {
            globalAdaper = 6;

        }
    }
}
