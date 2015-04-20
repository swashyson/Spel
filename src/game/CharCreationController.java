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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mattias
 */
public class CharCreationController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private Button hero1;
    @FXML
    private Button hero2;
    @FXML
    private Button hero3;
    @FXML
    private Button create;
    @FXML
    private Button back;

    public int type;

    @FXML
    public void back(ActionEvent event) {
        SwitchScene sc = new SwitchScene();
        sc.change(event, "SelectOrCreate");
    }

    @FXML
    public void Select(ActionEvent event) {
        if (event.getSource().equals(hero1)) {
            type = 1;
        } else if (event.getSource().equals(hero2)) {
            type = 2;
        } else if (event.getSource().equals(hero3)) {
            type = 3;
        }
    }

    public void Create() {
        DBConnect.connect();
        Connection c = DBConnect.getConnection();

        getID();

        DBConnect.CreateInsertStatement("INSERT INTO `game`.`hero` (`idHero`, `heroName`, `heroType`, `userID`, `heroLevel`, `eqWeapon`, `eqArmour`)"
                + " VALUES ('1', '1', '1', '1', '1', '1', '1')");
    }

    public void getID() {
        try {
            ResultSet rs = DBConnect.CreateSelectStatement("Select * from game.login");
            
            while(rs.next()){
                
            rs.getInt("UserID");
            
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
