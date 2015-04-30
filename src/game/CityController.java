package game;

import java.net.URL;
import java.sql.ResultSet;
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

    private Weapon weapon;
    private boolean hasWeapon = false;
    private int weaponID;

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

        DataStorage.getInstance().printHero();

        checkWeapon();
    }

    public void warrior() {

    }

    public void checkWeapon() {

        DBConnect.connect();

        int heroID = DataStorage.getInstance().getHero().getHeroID();

        ResultSet rs = DBConnect.CreateSelectStatement("select * from game.hero_has_weapon where hero_idHero = '" + heroID + "';");
        try {
            if (rs.next()) {
                weaponID = rs.getInt("weapon_weaponID");
                hasWeapon = true;
                System.err.println(weaponID);
            }
            ResultSet check = DBConnect.CreateSelectStatement("select * from game.weapon where weaponID = '"+ weaponID +"';");
            System.out.println("select * from game.weapon where weaponID = '"+ weaponID +"';");
            if (check.next()) {

                String weaponName = check.getString("weaponName");
                int weaponMinDamage = check.getInt("weaponMinDamage");
                int weaponMaxDamage = check.getInt("weaponMaxDamage");
                int weaponSpeed = check.getInt("weaponSpeed");
                int weaponlevel = check.getInt("weaponLevel");
                int weaponType = check.getInt("weaponType");

                weapon = new Weapon(weaponName, weaponID, weaponMinDamage, weaponMaxDamage, weaponSpeed, weaponlevel, weaponType);
                DataStorage.getInstance().setWeapon(weapon);

                System.out.println(weapon);

                System.out.println(weapon);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
