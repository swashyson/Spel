/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import DataStorage.HeroDataStorage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Label;

/**
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class DBConnect {

    //private static String URL;
    private static Connection c;

    public static void connect(Label fel) { //connecta till databasen
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL = "jdbc:mysql://127.0.0.1:3306/game?user=root&password=root";
            Connection cc = DriverManager.getConnection(URL);
            c = cc;

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            fel.setText("Error Connecting to database");

        }
    }

    public static void close(Label fel) { //stäng connection

        try {
            c.close();
            System.out.println("Connection Closed");
        } catch (SQLException ex) {
            fel.setText("Error closing database connection");
        }

    }

    public static Connection getConnection() { //hämta connection
        return c;
    }

    public static ResultSet CreateSelectStatement(String commando, Label fel) { //gör en select statement
        ResultSet rs = null;
        try {
            Statement st = c.createStatement();
            rs = st.executeQuery(commando);
        } catch (SQLException ex) {
            fel.setText("Error selecting statement from database");
        }
        return rs;
    }

    public static void CreateInsertStatement(String commando, Label label, String Medelande) { //gör en insert statement
        try {
            Statement st = c.createStatement();
            st.execute(commando);
        } catch (SQLException ex) {
            label.setText(Medelande);
        }
    }

    public static void CreateAlterStatement(String commando, Label fel) { // gör en alter statement
        try {
            Statement st = c.createStatement();
            st.execute(commando);
        } catch (SQLException ex) {
            fel.setText("Error alter statement");
        }
    }

    public static void saveToDB(Label label) { //spara till databasen alla värden
        try {
            Statement st = c.createStatement();
            st.execute("UPDATE game.hero "
                    + "SET heroLevel = '" + HeroDataStorage.getInstance().getHero().getLevel() + "', "
                    + "heroGold ='" + HeroDataStorage.getInstance().getHero().getGold() + "',  "
                    + "heroEXP ='" + HeroDataStorage.getInstance().getHero().getEXP() + "',    "
                    + "heroCurrentHP ='" + HeroDataStorage.getInstance().getHero().getHeroCurrentHP() + "',  "
                    + "heroBaseHP ='" + HeroDataStorage.getInstance().getHero().getHeroCurrentHP() + "',  "
                    + "heroBaseSpeed ='" + HeroDataStorage.getInstance().getHero().getSpeed() + "',  "
                    + "heroBaseDamage ='" + HeroDataStorage.getInstance().getHero().getBaseDamage() + "' "
                    + "WHERE idHERO = '" + HeroDataStorage.getInstance().getHero().getHeroID() + "'");

        } catch (SQLException ex) {
            label.setText("Error saving hero");
        }

    }
    public static void resetArmorAndWeapon(){ //resetta när man loggar ut
    
        HeroDataStorage.getInstance().setWeapon(null);
        HeroDataStorage.getInstance().setArmor(null);
    }
}
