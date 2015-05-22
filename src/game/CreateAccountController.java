/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.ResultSet;

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class CreateAccountController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField password;
    @FXML
    private TextField question;
    @FXML
    private TextField answer;
    @FXML
    private Button create;
    @FXML
    private Button back;

    @FXML
    private Label fel;

    private String typeName;
    private String typePassword;
    private String typeQuestion;
    private String typeAnswer;

    private SoundManager soundManager = new SoundManager();
    private ConfigFile config = new ConfigFile();

    private String buttonClick = "button_click";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HoverMouse.getInstance().inHover(create);
        HoverMouse.getInstance().outHover(create);
        HoverMouse.getInstance().inHover(back);
        HoverMouse.getInstance().outHover(back);

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource().equals(create)) {

            create(event);

        } else if (event.getSource().equals(back)) {

            back(event);

        }

    }

    public void create(ActionEvent event) {

        if(config.getSound() == 1){
            soundManager.defineShortSound(buttonClick);
        }
        try{
        ResultSet rS ;
        DBConnect.connect();
        typeName = name.getText();
        typePassword = password.getText();
        typeQuestion = question.getText();
        typeAnswer = answer.getText();

        rS = DBConnect.CreateSelectStatement("select * from game.login where userName = '" + typeName + "'");
        if(rS.next()){
            fel.setText("That name is used");
            
        }else{
            DBConnect.CreateInsertStatement("INSERT INTO game.login (userName, userPassword, userQuestion, userAnswer)" + " VALUES('" + typeName + "','" + typePassword + "','" + typeQuestion + "','" + typeAnswer + "')", fel, "User already exists");
        System.out.println("Account skapat");
        SwitchScene sc = new SwitchScene();
                sc.change(event, "Login");
        }
        }catch(Exception ex){
            fel.setText("fel");
        } finally {
            DBConnect.close();
        }

    }

    public void back(ActionEvent event) {

        if(config.getSound() == 1){
            soundManager.defineShortSound(buttonClick);
        }
        
        SwitchScene sc = new SwitchScene();
        sc.change(event, "Login");
    }

}
