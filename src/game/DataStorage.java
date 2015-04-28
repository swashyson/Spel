/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class DataStorage {

    private static DataStorage myDataStorage;
    private int userID;
    private String heroName;
    private int heroType;
    private int heroLevel;
    private int heroGold;
    private int heroCurrentHP;
    private int heroEXP;
    private int heroBaseHP;
    private int heroBaseSpeed;
    private int heroBaseDamage;

    public static DataStorage getInstance() {
        if (myDataStorage == null) {
            myDataStorage = new DataStorage();
        }

        return myDataStorage;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return heroName;
    }

    public int getUserType() {
        return heroType;
    }

    public void setUserLevel(int heroLevel) {
        this.heroLevel = heroLevel;
    }

    public int getUserLevel() {
        return heroLevel;
    }

    public void setHeroGold(int heroGold) {
        this.heroGold = heroGold;
    }

    public int getHeroGold() {
        return heroGold;
    }

    public void setHeroCurrentHP(int heroCurrentHP) {
        this.heroCurrentHP = heroCurrentHP;
    }

    public int getHeroCurrentHP() {
        return heroCurrentHP;
    }

    public void setHeroEXP(int heroEXP) {
        this.heroEXP = heroEXP;
    }

    public int getHeroEXP() {
        return heroEXP;
    }

    public void setHeroBaseHP(int heroBaseHP) {
        this.heroBaseHP = heroBaseHP;
    }

    public int getHeroBaseHP() {
        return heroBaseHP;
    }

    public void setHeroBaseSpeed(int heroBaseSpeed) {
        this.heroBaseSpeed = heroBaseSpeed;
    }

    public int getHeroBaseSpeed() {
        return heroBaseSpeed;
    }

    public void setHeroBaseDamage(int heroBaseDamage) {
        this.heroBaseDamage = heroBaseDamage;
    }

    public int getHeroBaseDamage() {
        return heroBaseDamage;
    }

    public void setHeroType(int heroType) {
        this.heroType = heroType;
    }

    public int getHeroType() {
        return heroType;
    }

    public void printAll() {
        System.out.println("userID = " + userID);
        System.out.println("heroType = " + heroType);
        System.out.println("heroLevel = " + heroLevel);
        System.out.println("heroGold = " + heroGold);
        System.out.println("heroCurrentHP = " + heroCurrentHP);
        System.out.println("heroEXP = " + heroEXP);
        System.out.println("heroBaseHP = " + heroBaseHP);
        System.out.println("heroBaseSpeed= " + heroBaseSpeed);
        System.out.println("heroBaseDamage = " + heroBaseDamage);
    }

}
