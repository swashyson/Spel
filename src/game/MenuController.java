/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import DataStorage.HeroDataStorage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class MenuController implements Initializable {

    @FXML
    private Button backToCity;
    @FXML
    private Button logout;
    @FXML
    private Button changeCharacter;
    @FXML
    private Button settings;
    @FXML
    private Label fel;

    private final SoundManager soundManager = new SoundManager();
    private final String buttonClick = "button_click";

    @FXML
    public void goToCity(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "City");

    }

    @FXML
    public void logout(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        try {
            DBConnect.connect(fel);
            DBConnect.saveToDB(fel);
            HeroDataStorage.getInstance().setHero(null);
            DBConnect.resetArmorAndWeapon();
            SwitchScene sc = new SwitchScene();
            sc.change(event, "Login");
        } catch (Exception ex) {
            fel.setText("Error saving hero");
        } finally {
            DBConnect.close(fel);
        }

    }

    @FXML
    public void settings(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Settings");
    }

    @FXML
    public void goToCharacter(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        try {
            DBConnect.connect(fel);
            DBConnect.saveToDB(fel);
            HeroDataStorage.getInstance().setHero(null);
            DBConnect.resetArmorAndWeapon();
            SwitchScene sc = new SwitchScene();
            sc.change(event, "ViewChar");
        } catch (Exception ex) {
            fel.setText("Error saving hero");
        } finally {
            DBConnect.close(fel);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HoverMouse.getInstance().inHover(backToCity);
        HoverMouse.getInstance().outHover(backToCity);
        HoverMouse.getInstance().inHover(logout);
        HoverMouse.getInstance().outHover(logout);
        HoverMouse.getInstance().inHover(changeCharacter);
        HoverMouse.getInstance().outHover(changeCharacter);
        HoverMouse.getInstance().inHover(settings);
        HoverMouse.getInstance().outHover(settings);

    }

}
