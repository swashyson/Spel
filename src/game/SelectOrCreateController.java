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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HoverMouse.inHover(create);
        HoverMouse.outHover(create);
        HoverMouse.inHover(choose);
        HoverMouse.outHover(choose);
        HoverMouse.inHover(back);
        HoverMouse.outHover(back);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        SwitchScene SC = new SwitchScene();

        if (event.getSource().equals(create)) {

            SC.change(event, "CharCreation");

        } else if (event.getSource().equals(choose)) {

            SC.change(event, "ViewChar");

        } else if (event.getSource().equals(back)) {

            SC.change(event, "Login");

        }

    }

}
