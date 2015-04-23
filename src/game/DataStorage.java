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
    private String heroName;
    private int heroType;
    private int heroLevel;
    private int eqWeapon;
    private int eqArmour;
    private int heroGold;
    private int heroCurrentHP;
    private int heroEXP;
    private int heroBaseHP;
    private int heroBaseSpeed;

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

    public String getUserType() {
        return heroName;
    }

    public void setUserLevel(int heroLevel) {
        this.heroLevel = heroLevel;
    }

    public int getUserLevel() {
        return heroLevel;
    }

    public void setEqWeapon(int eqWeapon) {
        this.eqWeapon = eqWeapon;
    }

    public int getEqWeapon() {
        return eqWeapon;
    }

    public void setEqArmour(int eqArmour) {
        this.eqArmour = eqArmour;
    }

    public int getEqArmour() {
        return eqArmour;
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

}
