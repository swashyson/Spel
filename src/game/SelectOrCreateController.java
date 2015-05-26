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

/**
 * FXML Controller class
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class SelectOrCreateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button create;
    @FXML
    private Button choose;
    @FXML
    private Button back;

    private final SoundManager soundManager = new SoundManager();

    private final String buttonClick = "button_click";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HoverMouse.getInstance().inHover(create);
        HoverMouse.getInstance().outHover(create);
        HoverMouse.getInstance().inHover(choose);
        HoverMouse.getInstance().outHover(choose);
        HoverMouse.getInstance().inHover(back);
        HoverMouse.getInstance().outHover(back);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        SwitchScene SC = new SwitchScene();

        if (event.getSource().equals(create)) {

            soundManager.defineSound(buttonClick);
            SC.change(event, "CharCreation");

        } else if (event.getSource().equals(choose)) {

            soundManager.defineSound(buttonClick);
            SC.change(event, "ViewChar");

        } else if (event.getSource().equals(back)) {

            soundManager.defineSound(buttonClick);
            SC.change(event, "Login");

        }

    }

}
