/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mattias
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

    private ArrayList<String> getName = new ArrayList();
    private ArrayList getStats = new ArrayList();

    @FXML
    public void back(ActionEvent event) {
        SwitchScene sc = new SwitchScene();
        sc.change(event, "SelectOrCreate");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HoverMouse.inHover(play);
        HoverMouse.outHover(play);
        HoverMouse.inHover(back);
        HoverMouse.outHover(back);

        try {
            DBConnect.connect();
            Connection c = DBConnect.getConnection();

            int userID = DataStorage.getInstance().getUserID();

            ResultSet rs = DBConnect.CreateSelectStatement("select * from game.login, game.hero where login.userID = hero.userID and login.userID = '" + userID + "';");
            System.out.println("select * from game.login, game.hero where login.userID = hero.userID and login.userID = '" + userID + "';");

            while (rs.next()) {
                String add = rs.getString("heroName");
                getName.add(add);
            }
            ObservableList<String> OL = FXCollections.observableArrayList(getName);
            list.setItems(OL);

            DBConnect.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void selectChar() {

        try {

            getStats.removeAll(getStats);

            Object name = list.getSelectionModel().getSelectedItem();
            String stringName = (String) name;
            int userID = DataStorage.getInstance().getUserID();

            DBConnect.connect();
            Connection c = DBConnect.getConnection();

            ResultSet rs = DBConnect.CreateSelectStatement("select * from game.hero where userID = '" + userID + "' and heroName = '" + stringName + "'");
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
            DBConnect.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void play(ActionEvent event) {
        try {

            DBConnect.connect();
            Connection c = DBConnect.getConnection();

            Object name = list.getSelectionModel().getSelectedItem();
            String stringName = (String) name;
            int userID = DataStorage.getInstance().getUserID();
            ResultSet rs = DBConnect.CreateSelectStatement("select * from game.hero where userID = '" + userID + "' and heroName = '" + stringName + "'");
            System.out.println("select * from game.hero where userID = '" + userID + "' and heroName = '" + stringName + "'");

            if (rs.next()) {
                int heroType = rs.getInt("heroType");
                int heroLevel = rs.getInt("heroLevel");
                int heroGold = rs.getInt("heroGold");
                int eqWeapon = rs.getInt("eqWeapon");
                int eqArmour = rs.getInt("eqArmour");
                int heroCurrentHP = rs.getInt("heroCurrentHP");
                int heroEXP = rs.getInt("heroEXP");
                int heroBaseHP = rs.getInt("heroBaseHP");
                int heroBaseSpeed = rs.getInt("heroBaseSpeed");
                int heroBaseDamage = rs.getInt("heroBaseDamage");

                DataStorage.getInstance().setHeroType(heroType);
                DataStorage.getInstance().setUserLevel(heroLevel);
                DataStorage.getInstance().setHeroGold(heroGold);
                DataStorage.getInstance().setEqArmour(eqArmour);
                DataStorage.getInstance().setEqWeapon(eqWeapon);
                DataStorage.getInstance().setHeroCurrentHP(heroCurrentHP);
                DataStorage.getInstance().setHeroEXP(heroEXP);
                DataStorage.getInstance().setHeroBaseHP(heroBaseHP);
                DataStorage.getInstance().setHeroBaseSpeed(heroBaseSpeed);
                DataStorage.getInstance().setHeroBaseDamage(heroBaseDamage);
            }
            DataStorage.getInstance().printAll();
            DBConnect.close();

            SwitchScene sc = new SwitchScene();
            sc.change(event, "City");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void changePic(String type) {
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("Recourses/" + type + ".png").toExternalForm());
        imageView.setImage(image);

    }
    public void resetPic(){
        imageView.setImage(null);
        
    }

    public void remove() {

        try {

            DBConnect.connect();
            Connection c = DBConnect.getConnection();

            Object name = list.getSelectionModel().getSelectedItem();
            String stringName = (String) name;
            int userID = DataStorage.getInstance().getUserID();
            
            int whatRow = list.getSelectionModel().getSelectedIndex();
            System.out.println(whatRow);
            
            ObservableList<Object> OL = FXCollections.observableArrayList(getName);
            OL.remove(whatRow);
            
            getName.remove(whatRow);
            list.setItems(OL);
            
            DBConnect.CreateInsertStatement("delete from game.hero where heroName = '"+stringName+"' and userID = '"+userID+"';");
            resetPic();
            getStats.removeAll(getStats);
            stats.setItems(null);
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
