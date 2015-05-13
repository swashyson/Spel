package Creature;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import Creature.Creature;
import Creature.Enemy;
import DataStorage.HeroDataStorage;
import Items.Weapon;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Mattias
 */
public class Hero extends Creature {

    private int userID;
    private int heroLevel;
    private int heroGold;
    private int heroEXP;
    private int heroBaseDamage;
    private int heroType;
    private int heroCurrentHP;
    private final int heroID;

    private Timer timer;
    private Enemy enemy;

    public Hero(String heroName, int heroBaseHp, int heroSpeed, int heroGold, int heroBaseDamage, int heroLevel, int heroEXP, int heroType, int heroCurrentHP, int heroID) {

        this.name = heroName;
        this.hp = heroBaseHp;
        this.heroGold = heroGold;
        this.heroLevel = heroLevel;
        this.heroEXP = heroEXP;
        this.heroBaseDamage = heroBaseDamage;
        this.heroType = heroType;
        this.heroCurrentHP = heroCurrentHP;
        this.heroID = heroID;
        this.speed = heroSpeed;

    }

    public int getHeroID() {
        return heroID;
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

    public void fightMonster(Hero hero, Enemy enemy) {

        System.out.println("Select Monster"); //Lägga in denna i heroTimeStart och byta plats på dom

    }

    public void basicAttack(Weapon weapon, Enemy enemy) {

        //enemy.setHp(enemy.getHp());
        System.out.println("You damaged enemy with a attack that damaged " + (getBaseDamage() + getWeaponRandomDamage()) + "To ");
        heroTimeStop();
    }

    public int getWeaponRandomDamage() {

        if (HeroDataStorage.getInstance().getWeapon() != null) {
            Random rand = new Random();
            int minDamage = HeroDataStorage.getInstance().getWeapon().getWeaponMinDamage();
            int maxDamage = HeroDataStorage.getInstance().getWeapon().getWeaponMaxDamage();
            int R = rand.nextInt(maxDamage - minDamage) + minDamage;
            return R;
        }
        return 0;
    }

    public int getWeaponSpeed() {
        if (HeroDataStorage.getInstance().getWeapon() != null) {

            int Speed = HeroDataStorage.getInstance().getWeapon().getWeaponSpeed();
            return Speed;
        }
        return 0;
    }

    public void heroTimeStart() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {;                                                //Attack!;
                basicAttack(HeroDataStorage.getInstance().getWeapon(), enemy); // måste få in värderna från enemy
            }
        }, 3000 - speed - getWeaponSpeed() * 2, 3000 - speed * -getWeaponSpeed() - 2); //Time tick speeden, desto snabbare speed man har desto snabbare slår man helt enkelt
        //Beräknar med basic speed och weaponSpeed, och en konstant * (multiplier*2)
        //Dubbelt för att den ska loopas i onändlighet
    }

    public void heroTimeStop() {
        timer.cancel();
    }

}
