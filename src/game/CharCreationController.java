/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import DataStorage.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
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

    private int type;

    private final SoundManager soundManager = new SoundManager();

    private final String buttonClick = "button_click";

    @FXML
    public void back(ActionEvent event) { //tillbaka knappen

        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "SelectOrCreate");
    }

    @FXML
    public void Select(ActionEvent event) { //kollar vilken typ av hero man väljer och markerar den

        if (event.getSource().equals(hero1)) {
            HoverMouse.getInstance().inClick(hero1);
            HoverMouse.getInstance().outClick(hero2);
            HoverMouse.getInstance().outClick(hero3);
            type = 1;

            soundManager.defineSound(buttonClick);

        } else if (event.getSource().equals(hero2)) {
            HoverMouse.getInstance().inClick(hero2);
            HoverMouse.getInstance().outClick(hero1);
            HoverMouse.getInstance().outClick(hero3);
            type = 2;

            soundManager.defineSound(buttonClick);

        } else if (event.getSource().equals(hero3)) {
            HoverMouse.getInstance().inClick(hero3);
            HoverMouse.getInstance().outClick(hero2);
            HoverMouse.getInstance().outClick(hero1);
            type = 3;

            soundManager.defineSound(buttonClick);

        }
    }

    public void Create(ActionEvent event) { //skapa en hero och lägger in den i databasen

        soundManager.defineSound(buttonClick);
        if (name.getText().equals("")) {
            fel.setText("You need to enter a name"); // fel hantering
        } else {
            try {
                DBConnect.connect(fel);

                int userID = HeroDataStorage.getInstance().getuserID();

                ResultSet rs = DBConnect.CreateSelectStatement("Select * from game.hero where userID = '" + userID + "' and heroName = '" + name.getText() + "'", fel);

                if (rs.next()) {

                    fel.setText("You already have a hero by this name"); //fel hantering
                } else {

                    DBConnect.CreateInsertStatement("INSERT INTO game.hero (heroName, heroType, userID, heroLevel, heroGold, heroCurrentHP, heroEXP, heroBaseHP, heroBaseSpeed, heroBaseDamage)"
                            + " VALUES ( '" + name.getText() + "', '" + type + "', '" + userID + "', '1', '0', '10', '0', '10', '5', '2')", fel, "hej");
                    System.out.println("INSERT INTO game.hero (heroName, heroType, userID, heroLevel, heroGold, heroCurrentHP, heroEXP, heroBaseHP, heroBaseSpeed, heroBaseDamage)"
                            + " VALUES ( '" + name.getText() + "', '" + type + "', '" + userID + "', '1', '0', '10', '0', '10', '5', '2' )");

                    SwitchScene sc = new SwitchScene();
                    sc.change(event, "ViewChar");
                }
            } catch (SQLException ex) {
                fel.setText("Error");
            } finally {
                DBConnect.close(fel);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) { //hover mouse metoder för att få effekt på knapparna
        HoverMouse.getInstance().inHover(create);
        HoverMouse.getInstance().outHover(create);
        HoverMouse.getInstance().inHover(back);
        HoverMouse.getInstance().outHover(back);
    }

    public void clickOnTextField() { // en resetter
        fel.setText(null);
    }

}
