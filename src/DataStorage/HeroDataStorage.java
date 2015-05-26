/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStorage;

import Items.Armor;
import Creature.Hero;
import Items.Weapon;

/**
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
//
public class HeroDataStorage {

    private static HeroDataStorage heroDataStorage ;
    private Hero hero;
    private Weapon weapon;
    private Armor armor;
    private int userID;

    public static HeroDataStorage getInstance() {
        if (heroDataStorage  == null) {
            heroDataStorage  = new HeroDataStorage();
        }

        return heroDataStorage ;
    }

    public void setHero(Hero hero) {

        System.out.println("Du har sparat en Hero i datastorage");
        this.hero = hero;
    }

    public Hero getHero() {

        return hero;
    }

    public void setWeapon(Weapon weapon) {

        System.out.println("Du har sparat ett vapen");
        this.weapon = weapon;
    }

    public Weapon getWeapon() {

        return weapon;
    }

    public void setArmor(Armor armor) {

        System.out.println("Du har sparat en armor");
        this.armor = armor;
    }

    public Armor getArmor() {

        return armor;
    }
    public void setuserID(int userID){
        this.userID = userID;        
    }
    public int getuserID(){
        return userID;
    } 
}
