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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    @FXML
    public void logIn(ActionEvent event) {
        
        

        try {
            DBConnect.connect();
            ResultSet rs = DBConnect.CreateSelectStatement("select * from game.login where login.userName =  '" + name.getText() + "' and login.userPassword = '" + password.getText() + "'");
            //System.out.println("select * from game.login where login.userName =  '" + name.getText() + "' and login.userPassword = '" + password.getText() + "'");

            if (rs.first()) {

                int ID;
                SwitchScene sc = new SwitchScene();
                sc.change(event, "SelectOrCreate");
                ID = rs.getInt("userID");
                
                Hero.userID = ID;
                
                //DataStorage.getInstance().setUserID(ID);

                DBConnect.close();
            } else {
                fel.setText("Wrong username/password");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void createAccount(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "CreateAccount");
    }

    @FXML
    public void forgot(ActionEvent event) {

        SwitchScene sc = new SwitchScene();
        sc.change(event, "ForgotPW");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HoverMouse.getInstance().inHover(createAccount);
        HoverMouse.getInstance().outHover(createAccount);
        HoverMouse.getInstance().inHover(login);
        HoverMouse.getInstance().outHover(login);
        HoverMouse.getInstance().inHover(forgot);
        HoverMouse.getInstance().outHover(forgot);
        
        HoverMouse.getInstance().ClickEffect(login);

    }

}
