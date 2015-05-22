/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.URL;
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
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class ForgotPWController implements Initializable {

    private String question, answer;

    @FXML
    private Button getQuestion;

    @FXML
    private Button getPassword;

    @FXML
    private Button back;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private TextField secretAnswer;

    @FXML
    private TextField secretQuestion;

    private SoundManager soundManager = new SoundManager();

    private String buttonClick = "button_click";

    @FXML
    public void getQuestion(ActionEvent event) throws Exception {

        soundManager.defineShortSound(buttonClick);

        DBConnect.connect();

        ResultSet rs = DBConnect.CreateSelectStatement("select * from game.login where login.userName = '" + username.getText() + "'");
        // If user exists
        if (rs.first()) {

            question = rs.getString("userQuestion");
            secretQuestion.setText(question + "?");
            secretAnswer.setEditable(true);
            // if not
        } else {
            username.setText("This user does not exist bitch!");
        }
        DBConnect.close();

    }

    @FXML

    // Second return button
    public void getPassword(ActionEvent event) throws Exception {

        soundManager.defineShortSound(buttonClick);

        DBConnect.connect();
        ResultSet rs = DBConnect.CreateSelectStatement("select * from game.login where login.userName = '" + username.getText() + "'");
        while (rs.next()) {

            answer = rs.getString("userAnswer");
            String pass = rs.getString("userPassword");
            // If secret answer = right
            if (answer.equals(secretAnswer.getText())) {

                password.setText(pass);

                // If secret answer = wrong
            } else {
                secretAnswer.setText("WRONG!");
                password.clear();
            }

        }
        DBConnect.close();
    }

    @FXML
    //BACK TO LOGIN
    public void back(ActionEvent event) {

        soundManager.defineShortSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Login");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TF as not editable
        secretQuestion.setEditable(false);
        password.setEditable(false);
        secretAnswer.setEditable(false);

        // Getpassword button!
        HoverMouse.getInstance().inHover(getPassword);
        HoverMouse.getInstance().outHover(getPassword);
        HoverMouse.getInstance().ClickEffect(getPassword);

        // GetQuestion button!
        HoverMouse.getInstance().inHover(getQuestion);
        HoverMouse.getInstance().outHover(getQuestion);
        HoverMouse.getInstance().ClickEffect(getQuestion);

        // BackToLogin
        HoverMouse.getInstance().inHover(back);
        HoverMouse.getInstance().outHover(back);

    }

}
