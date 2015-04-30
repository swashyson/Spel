/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

/**
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class DBConnect {

    private static String URL;
    private static Connection c;

    public static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URLC = "jdbc:mysql://127.0.0.1:3306/game?user=root&password=root";
            Connection cc = DriverManager.getConnection(URLC);

            URL = URLC;
            c = cc;

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static void close() {

        try {
            c.close();
            System.out.println("Connection Closed");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return c;
    }

    public static ResultSet CreateSelectStatement(String commando) {
        ResultSet rs = null;
        try {
            Statement st = c.createStatement();
            rs = st.executeQuery(commando);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public static void CreateInsertStatement(String commando, Label label, String Medelande) {
        try {
            Statement st = c.createStatement();
            st.execute(commando);
        } catch (Exception ex) {

            label.setText(Medelande);
            System.out.println("Fel");
        }
    }


    public static void saveToDB() {
        connect();
        try {
            Statement st = c.createStatement();
            st.execute("UPDATE game.hero "
                    + "SET heroLevel = '" + DataStorage.getInstance().getHero().getLevel() + "', "
                    + "heroGold ='" + DataStorage.getInstance().getHero().getGold() + "',  "
                    + "heroEXP ='" + DataStorage.getInstance().getHero().getEXP() + "',    "
                    + "heroCurrentHP ='" + DataStorage.getInstance().getHero().getHeroCurrentHP() + "',  "
                    + "heroBaseSpeed ='" + DataStorage.getInstance().getHero().getHeroSpeed() + "',  "
                    + "heroBaseDamage ='" + DataStorage.getInstance().getHero().getBaseDamage() + "' "
                    + "WHERE idHERO = '" + DataStorage.getInstance().getHero().getUserID() + "'");
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        close();

    }
}
