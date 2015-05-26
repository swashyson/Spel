package game;

import Items.Weapon;
import Items.Armor;
import DataStorage.HeroDataStorage;
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
    private Button fight;
    @FXML
    private Button inn;
    @FXML
    private Button shop;
    @FXML
    private Button menu;

    private Weapon weapon;
    private int weaponID;
    private Armor armor;
    private int armorID;

    private int heroID = HeroDataStorage.getInstance().getHero().getHeroID();

    private SoundManager soundManager = new SoundManager();

    private String buttonClick = "button_click";
    private String citySound = "City";

    @FXML
    public void goToMenu(ActionEvent event) {

        soundManager.defineSound(buttonClick);
        System.out.println("buttonclick");

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Menu");

        soundManager.stopTheSound("back");
    }

    @FXML
    public void goToInn(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "InnScene");

        soundManager.stopTheSound("back");
        
    }

    @FXML
    public void goToFight(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Fight");
        
        soundManager.stopTheSound("back");

    }

    @FXML
    public void goToShop(ActionEvent event) {

        soundManager.defineSound(buttonClick);

        SwitchScene sc = new SwitchScene();
        sc.change(event, "Shop");

        soundManager.stopTheSound("back");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HoverMouse.getInstance().inHover(fight);
        HoverMouse.getInstance().outHover(fight);
        HoverMouse.getInstance().inHover(inn);
        HoverMouse.getInstance().outHover(inn);
        HoverMouse.getInstance().inHover(shop);
        HoverMouse.getInstance().outHover(shop);
        HoverMouse.getInstance().inHover(menu);
        HoverMouse.getInstance().outHover(menu);

        HeroDataStorage.getInstance().printHero();

        checkWeapon();
        checkArmor();

        soundManager.defineSound(citySound);

    }

    public void warrior() {

    }

    public void checkWeapon() {

        DBConnect.connect();

        ResultSet rs = DBConnect.CreateSelectStatement("select * from game.hero_has_weapon where hero_idHero = '" + heroID + "';");
        try {
            if (rs.next()) {
                weaponID = rs.getInt("weapon_weaponID");
            }
            ResultSet check = DBConnect.CreateSelectStatement("select * from game.weapon where weaponID = '" + weaponID + "';");
            System.out.println("select * from game.weapon where weaponID = '" + weaponID + "';");
            if (check.next()) {

                String weaponName = check.getString("weaponName");
                int weaponMinDamage = check.getInt("weaponMinDamage");
                int weaponMaxDamage = check.getInt("weaponMaxDamage");
                int weaponSpeed = check.getInt("weaponSpeed");
                int weaponlevel = check.getInt("weaponLevel");
                int weaponType = check.getInt("weaponType");
                int weaponGold = check.getInt("weaponGold");

                weapon = new Weapon(weaponName, weaponID, weaponMinDamage, weaponMaxDamage, weaponSpeed, weaponlevel, weaponType, weaponGold);
                HeroDataStorage.getInstance().setWeapon(weapon);

                System.out.println(weapon);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.close();
        }

    }

    public void checkArmor() {

        DBConnect.connect();

        ResultSet rs = DBConnect.CreateSelectStatement("select * from game.hero_has_armor where hero_idHero = '" + heroID + "';");
        try {
            if (rs.next()) {
                armorID = rs.getInt("armor_armorID");
            }
            ResultSet check = DBConnect.CreateSelectStatement("select * from game.armor where armorID = '" + armorID + "';");
            System.out.println("select * from game.armor where armorID = '" + armorID + "';");
            if (check.next()) {

                String armorName = check.getString("armorName");
                int localArmor = check.getInt("armor");
                int armorType = check.getInt("armorType");
                int armorLevel = check.getInt("armorLevel");
                int armorSpeed = check.getInt("armorSpeed");
                int armorGold = check.getInt("armorGold");
                armor = new Armor(armorName, armorID, localArmor, armorType, armorLevel, armorSpeed, armorGold);
                HeroDataStorage.getInstance().setArmor(armor);

                System.out.println(armor);

                DBConnect.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.close();
        }

    }

}
