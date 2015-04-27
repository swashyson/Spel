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
 * @author Hyypes
 */
public class CityController implements Initializable {

    @FXML
    Button fight;
    Button inn;
    Button shop;
    Button menu;

    @FXML
    public void goToMenu(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Menu");
    }

    @FXML
    public void goToInn(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "InnScene");
    }

    @FXML
    public void goToFight(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Fight");
    }

    @FXML
    public void goToShop(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "ShoppingScene");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
