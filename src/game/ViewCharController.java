/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import Creature.Hero;
import DataStorage.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class ViewCharController implements Initializable {

    @FXML
    private ListView list;
    @FXML
    private ListView stats;
    @FXML
    private Button play;
    @FXML
    private Button back;
    @FXML
    private ImageView imageView;
    @FXML
    private Label fel;
    
    private final ArrayList<String> getName = new ArrayList();
    private final ArrayList getStats = new ArrayList();
    private final int userID = HeroDataStorage.getInstance().getuserID();

    private final SoundManager soundManager = new SoundManager();

    private final String buttonClick = "button_click";

    @FXML
    public void back(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "SelectOrCreate");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HoverMouse.getInstance().inHover(play);
        HoverMouse.getInstance().outHover(play);
        HoverMouse.getInstance().inHover(back);
        HoverMouse.getInstance().outHover(back);
        listHeros();
    }

    @FXML
    public void selectChar() {

        try {
            resetText();
            System.out.println("Rad ID = " + list.getSelectionModel().getSelectedIndex());
            getStats.removeAll(getStats);

            Object name = list.getSelectionModel().getSelectedItem();
            String stringName = (String) name;

            DBConnect.connect(fel);

            ResultSet rs = DBConnect.CreateSelectStatement("select * from game.hero where userID = '" + userID + "' and heroName = '" + stringName + "'", fel);
            while (rs.next()) {
                int level = rs.getInt("heroLevel");
                int type = rs.getInt("heroType");

                if (type == 1) {
                    getStats.add("Type: Warrior");
                    changePic("Melee");
                } else if (type == 2) {
                    getStats.add("Type: Ranger");
                    changePic("range");
                } else if (type == 3) {
                    getStats.add("Type: Mage");
                    changePic("Mage");
                } else {
                    getStats.add("Type: No class");
                }

                getStats.add("Level: " + level);
            }
            ObservableList<Object> OL = FXCollections.observableArrayList(getStats);
            stats.setItems(OL);

        } catch (SQLException ex) {
            fel.setText("Error selecting hero");
        } finally {
            DBConnect.close(fel);
        }
    }

    @FXML
    public void play(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        loadHero();
        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");
    }

    public void loadHero() {
        try {

            DBConnect.connect(fel);

            Object name = list.getSelectionModel().getSelectedItem();
            String stringName = (String) name;
            ResultSet rs = DBConnect.CreateSelectStatement("select * from game.hero where userID = '" + userID + "' and heroName = '" + stringName + "'", fel);
            System.out.println("select * from game.hero where userID = '" + userID + "' and heroName = '" + stringName + "'");

            if (!stringName.equals("")) {

                if (rs.next()) {
                    int heroID = rs.getInt("idHero");
                    int heroType = rs.getInt("heroType");
                    int heroLevel = rs.getInt("heroLevel");
                    int heroGold = rs.getInt("heroGold");
                    int heroCurrentHP = rs.getInt("heroCurrentHP");
                    int heroEXP = rs.getInt("heroEXP");
                    int heroBaseHP = rs.getInt("heroBaseHP");
                    int heroBaseSpeed = rs.getInt("heroBaseSpeed");
                    int heroBaseDamage = rs.getInt("heroBaseDamage");

                    Hero newHero = new Hero(stringName, heroBaseHP, heroBaseSpeed, heroGold, heroBaseDamage, heroLevel, heroEXP, heroType, heroCurrentHP, heroID);

                    HeroDataStorage.getInstance().setHero(newHero);
                }

            } else {
                fel.setText("You must select a hero");
            }
        } catch (SQLException ex) {
            fel.setText("You must select a hero");
        } finally {
            DBConnect.close(fel);
        }

    }

    private void listHeros() {
        try {
            DBConnect.connect(fel);

            ResultSet rs = DBConnect.CreateSelectStatement("select * from game.login, game.hero where login.userID = hero.userID and login.userID = '" + userID + "'", fel);
            System.out.println("select * from game.login, game.hero where login.userID = hero.userID and login.userID = '" + userID + "';");

            while (rs.next()) {
                String add = rs.getString("heroName");
                getName.add(add);
            }
            ObservableList<String> OL = FXCollections.observableArrayList(getName);
            list.setItems(OL);

            System.out.println("Antalet Gubbar = " + getName.size());

        } catch (SQLException ex) {
            fel.setText("Error loading hero");
        } finally {
            DBConnect.close(fel);
        }
    }

    public void changePic(String type) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("Recourses/" + type + ".png").toExternalForm());
        imageView.setImage(image);

    }

    public void resetPic() {
        imageView.setImage(null);

    }

    public void remove() {

        soundManager.defineSound(buttonClick);

        try {

            DBConnect.connect(fel);

            Object name = list.getSelectionModel().getSelectedItem();
            String stringName = (String) name;
            System.out.println("Försöker ta väck " + stringName + "...");

            int whatRow = list.getSelectionModel().getSelectedIndex();
            System.out.println("Rad du har markerat = " + whatRow);

            ObservableList<Object> OL = FXCollections.observableArrayList(getName);
            OL.remove(whatRow);

            getName.remove(whatRow);
            list.setItems(OL);

            DBConnect.CreateInsertStatement("delete from game.hero where heroName = '" + stringName + "' and userID = '" + userID + "';", fel, "You must select a hero"); // fel i databasen
            resetPic();
            getStats.removeAll(getStats);
            stats.setItems(null);

        } catch (Exception ex) {
            fel.setText("You must select a hero");
        }

    }

    public void resetText() {
        fel.setText(null);

    }

}
