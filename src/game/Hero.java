/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Mattias
 */
public class Hero extends Creature {

    static int userID;
    private int heroLevel;
    private int heroGold;
    private int heroEXP;
    private int heroBaseDamage;
    private int heroType;
    private int heroCurrentHP;

    public Hero(String heroName, int heroBaseHp, int heroSpeed, int heroGold, int heroBaseDamage, int heroLevel, int heroEXP, int heroType, int heroCurrentHP) {

        this.name = heroName;
        this.hp = heroBaseHp;
        this.speed = heroSpeed;
        this.heroGold = heroGold;
        this.heroLevel = heroLevel;
        this.speed = heroLevel;
        this.heroEXP = heroEXP;
        this.heroBaseDamage = heroBaseDamage;
        this.heroType = heroType;
        this.heroCurrentHP = heroCurrentHP;

    }

    public void setGold(int gold) {

        this.heroGold = gold;

    }

    public int getGold() {

        return heroGold;

    }

    public void setLevel(int level) {

        this.heroLevel = level;

    }

    public int getLevel() {

        return heroLevel;

    }

    public void setEXP(int EXP) {

        this.heroEXP = EXP;
    }

    public int getEXP() {

        return heroEXP;

    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setHeroType(int heroType) {
        this.heroType = heroType;
    }

    public int getHeroCurrentHP() {
        return heroCurrentHP;
    }

    public void setHeroCurrentHP(int heroCurrentHP) {
        this.heroCurrentHP = heroCurrentHP;
    }

    public int getHeroType() {
        return heroType;
    }

    public void setBaseDamage(int heroBaseDamage) {
        this.heroBaseDamage = heroBaseDamage;
    }

    public int getBaseDamage() {
        return heroBaseDamage;
    }

    public void printAll() {

        System.out.println("UserID = " + getUserID());

    }

}
