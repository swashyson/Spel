/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author gul_h_000
 */
public class DataStorage {

    private static DataStorage myDataStorage;
    private int userID;


    private DataStorage(int userID, int currentHealth, int gold, int currentLevel) {
    }
    public static DataStorage getInstance() {
        if (myDataStorage == null) {
            myDataStorage = new DataStorage();
        }

        return myDataStorage;
    }
    public void setUserID(int userID){

        this.userID = userID;


    }
    public int getUserID(){
        return userID;

}
}