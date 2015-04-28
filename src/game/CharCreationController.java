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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private Label fel;

    public int type;

    @FXML
    public void back(ActionEvent event) {
        SwitchScene sc = new SwitchScene();
        sc.change(event, "SelectOrCreate");
    }

    @FXML
    public void Select(ActionEvent event) {
        if (event.getSource().equals(hero1)) {
            HoverMouse.inClick(hero1);
            HoverMouse.outClick(hero2);
            HoverMouse.outClick(hero3);
            type = 1;
        } else if (event.getSource().equals(hero2)) {
            HoverMouse.inClick(hero2);
            HoverMouse.outClick(hero1);
            HoverMouse.outClick(hero3);
            type = 2;
        } else if (event.getSource().equals(hero3)) {
            HoverMouse.inClick(hero3);
            HoverMouse.outClick(hero2);
            HoverMouse.outClick(hero1);
            type = 3;
        }
    }

    public void Create(ActionEvent event) {

        try {
            DBConnect.connect();
            Connection c = DBConnect.getConnection();

            int userID = DataStorage.getInstance().getUserID();

            ResultSet rs = DBConnect.CreateSelectStatement("Select * from game.hero where userID = '" + userID + "' and heroName = '" + name.getText() + "'");

            if (rs.next()) {

                fel.setText("You already have a hero by this name");
            } else {

                DBConnect.CreateInsertStatement("INSERT INTO game.hero (heroName, heroType, userID, heroLevel, eqWeapon, eqArmour, heroGold, heroCurrentHP, heroEXP, heroBaseHP, heroBaseSpeed, heroBaseDamage)"
                        + " VALUES ( '" + name.getText() + "', '" + type + "', '" + userID + "', '1', null, null, '0', '10', '0', '10', '5', '2')", null, null);
                System.out.println("INSERT INTO game.hero (heroName, heroType, userID, heroLevel, eqWeapon, eqArmour, heroGold, heroCurrentHP, heroEXP, heroBaseHP, heroBaseSpeed, heroBaseDamage)"
                        + " VALUES ( '" + name.getText() + "', '" + type + "', '" + userID + "', '1', null, null, '0', '10', '0', '10', '5', '2' )");

                SwitchScene sc = new SwitchScene();
                sc.change(event, "ViewChar");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(DataStorage.getInstance().getUserID());

        HoverMouse.inHover(create);
        HoverMouse.outHover(create);
        HoverMouse.inHover(back);
        HoverMouse.outHover(back);

    }
    public void clickOnTextField(){
        fel.setText(null);
    }

}
