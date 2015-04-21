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
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

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

    private ArrayList<String> getName = new ArrayList();
    private ArrayList getStats = new ArrayList();

    @FXML
    public void back(ActionEvent event) {
        SwitchScene sc = new SwitchScene();
        sc.change(event, "SelectOrCreate");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
                getStats.add("Level: " + level);
                getStats.add("Type: " + type);
            }
            ObservableList<Object> OL = FXCollections.observableArrayList(getStats);
            stats.setItems(OL);
            DBConnect.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
