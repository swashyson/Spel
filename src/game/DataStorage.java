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

    private static int userID, currentHealth, gold, currentLevel;

    private DataStorage(int userID, int currentHealth, int gold, int currentLevel) {
        this.userID = userID;
        this.currentHealth = currentHealth;
        this.gold = gold;
        this.currentLevel = currentLevel;

    }

    private int getHealth() {
        return currentHealth;
    }

    private int getGold() {
        return gold;
    }

    private int getUserID() {
        return userID;
    }

    private int getLevel() {
        return currentLevel;
    }

}
