/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import DataStorage.*;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class LoginController implements Initializable {

    @FXML
    private Label fel;
    @FXML
    private Button createAccount;
    @FXML
    private Button login;
    @FXML
    private Button forgot;
    @FXML
    private Button settings;
    @FXML
    private Button exit;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;

    private final SoundManager soundManager = new SoundManager();

    private final String buttonClick = "button_click";
    private final String error = "error";

    @FXML
    public void logIn(ActionEvent event) {

        try {
            DBConnect.connect(fel);
            ResultSet rs = DBConnect.CreateSelectStatement("select * from game.login where login.userName =  '" + name.getText() + "' and login.userPassword = '" + password.getText() + "'", fel);
            //System.out.println("select * from game.login where login.userName =  '" + name.getText() + "' and login.userPassword = '" + password.getText() + "'");

            if (rs.first()) {

                int ID;
                SwitchScene sc = new SwitchScene();
                sc.change(event, "SelectOrCreate");
                ID = rs.getInt("userID");

                HeroDataStorage.getInstance().setuserID(ID);

                soundManager.defineSound(buttonClick);
            } else {
                fel.setText("Wrong username/password");
                FadeTransition ft = new FadeTransition(Duration.millis(4000), fel);
                ft.setFromValue(1.0);
                ft.setToValue(0. - 1);
                ft.play();

                soundManager.defineSound(error);
            }

        } catch (SQLException ex) {
            fel.setText("Error connecting to database");
        } finally {
            DBConnect.close(fel);
        }
    }

    @FXML
    public void createAccount(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "CreateAccount");
    }

    @FXML
    public void forgot(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "ForgotPW");

    }

    @FXML
    public void settings(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Settings");

    }

    @FXML
    public void exit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadConfigFile();

        HoverMouse.getInstance().inHover(createAccount);
        HoverMouse.getInstance().outHover(createAccount);
        HoverMouse.getInstance().inHover(login);
        HoverMouse.getInstance().outHover(login);
        HoverMouse.getInstance().inHover(forgot);
        HoverMouse.getInstance().outHover(forgot);
        HoverMouse.getInstance().inHover(settings);
        HoverMouse.getInstance().outHover(settings);
        HoverMouse.getInstance().inHover(exit);
        HoverMouse.getInstance().outHover(exit);

        HoverMouse.getInstance().ClickEffect(login);

    }

    public void loadConfigFile() {

        ConfigFile CF = new ConfigFile();
        CF.readConfigFile();

    }

    public void openHelpFile() {
        try {
            String fullPath = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
            String[] path = fullPath.split("dist");
            File file = new File(path[0] + "src/game/helpFile/helpFile.pdf");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
        } catch (Exception ex) {
            fel.setText("Could not load help file");
            ex.printStackTrace();
        }
    }

}
