/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import Creature.Hero;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import DataStorage.*;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

/**
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class LoginController implements Initializable {

    // @FXML
    // private Label label;
    @FXML
    private Label fel;
    @FXML
    private Button createAccount;
    @FXML
    private Button login;
    @FXML
    private Button forgot;
    @FXML
    private TextField name;
    @FXML
    private TextField password;
    
    ConfigFile config = new ConfigFile();
    SoundManager soundManager = new SoundManager();
    
    private String buttonClick = "button_click";

    @FXML
    public void logIn(ActionEvent event) {

        if(config.getSound() == 1){
            soundManager.defineShortSound(buttonClick);
        }
        
        try {
            DBConnect.connect();
            ResultSet rs = DBConnect.CreateSelectStatement("select * from game.login where login.userName =  '" + name.getText() + "' and login.userPassword = '" + password.getText() + "'");
            //System.out.println("select * from game.login where login.userName =  '" + name.getText() + "' and login.userPassword = '" + password.getText() + "'");

            if (rs.first()) {

                int ID;
                SwitchScene sc = new SwitchScene();
                sc.change(event, "SelectOrCreate");
                ID = rs.getInt("userID");

                HeroDataStorage.getInstance().setuserID(ID);
                DBConnect.close();
            } else {
                fel.setText("Wrong username/password");
                FadeTransition ft = new FadeTransition(Duration.millis(4000), fel);
                ft.setFromValue(1.0);
                ft.setToValue(0. - 1);
                ft.play();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void createAccount(ActionEvent event) {

        if(config.getSound() == 1){
            soundManager.defineShortSound(buttonClick);
        }
        
        SwitchScene sc = new SwitchScene();
        sc.change(event, "CreateAccount");
    }

    @FXML
    public void forgot(ActionEvent event) {

        if(config.getSound() == 1){
            soundManager.defineShortSound(buttonClick);
        }
        
        SwitchScene sc = new SwitchScene();
        sc.change(event, "ForgotPW");

    }

    @FXML
    public void settings(ActionEvent event) {

        if(config.getSound() == 1){
            soundManager.defineShortSound(buttonClick);
        }
        
        SwitchScene sc = new SwitchScene();
        sc.change(event, "Settings");

    }
    @FXML
    public void exit(ActionEvent event){
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

        HoverMouse.getInstance().ClickEffect(login);

        //Kolla om det fungerar att ha ljudet över flera scener, annars får vi
        //sätta på och stänga av ljudet i varje scen.
        
//        if(cf.getSound() == 1){
//            soundManager.defineBackgroundSound("City");
//            System.out.println("Started backgroundsound - chirping birds - logincontroller");
//        }
        
    }

    public void loadConfigFile() {

        ConfigFile CF = new ConfigFile();
        CF.readConfigFile();

        //CF.setSound(0);
    }

}
