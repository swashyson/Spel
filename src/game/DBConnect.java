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
 
/**
 *
 * @author Mattias,Jonathan
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
 
    public static String getURL() {
 
        return URL;
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
    public static void CreateInsertStatement(String commando) {
        try {
            Statement st = c.createStatement();
            st.execute(commando);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
