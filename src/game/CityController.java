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
public class CityController implements Initializable {

    @FXML
    Button fight;
    @FXML
    Button inn;
    @FXML
    Button shop;
    @FXML
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
        sc.change(event, "Shop");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HoverMouse.inHover(fight);
        HoverMouse.outHover(fight);
        HoverMouse.inHover(inn);
        HoverMouse.outHover(inn);
        HoverMouse.inHover(shop);
        HoverMouse.outHover(shop);
        HoverMouse.inHover(menu);
        HoverMouse.outHover(menu);
        
        
        DataStorage.getInstance().printAll();
    }

    public void warrior() {

    }

}
