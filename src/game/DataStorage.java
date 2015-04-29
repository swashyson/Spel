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
    private Hero hero;

    public static DataStorage getInstance() {
        if (myDataStorage == null) {
            myDataStorage = new DataStorage();
        }

        return myDataStorage;
    }

    public void setHero(Hero hero) {

        System.out.println("Du har sparat en Hero i datastorage");
        this.hero = hero;
    }

    public Hero getHero() {

        return hero;
    }
    public void printAll(){
    
        System.out.println("UserID = " + hero.getUserID());
        System.out.println("HeroName = " + hero.getName());
        System.out.println("HeroType = " + hero.getHeroType());
        System.out.println("HeroSpeed = " + hero.getSpeed());
        System.out.println("HeroGold = " + hero.getGold());
        System.out.println("HeroBaseDamage = " + hero.getBaseDamage());
        System.out.println("HeroLevel = " + hero.getLevel());
        System.out.println("HeroEXP = " + hero.getEXP());
        System.out.println("HeroHP = " + hero.getHp());
        System.out.println("HeroCurrentHP = " + hero.getHeroCurrentHP());
        
    }
    

}
