package Creature;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DataStorage.*;
import Items.Weapon;
import java.util.Random;

/**
 *
 * @author Mohini, Mattias, Johan, Fredrik, Jonathan
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

    private int displayedDamage;

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

    public void basicAttack(Weapon weapon) { // Kolla vilken hero man attakera och gör damage på den

        try {
            switch (FightDataStorage.getInstance().getEnemyID()) {

                case "1":
                    displayedDamage = getWeaponRandomDamage();
                    FightDataStorage.getInstance().getEnemy1().setHp(FightDataStorage.getInstance().getEnemy1().getHp() - displayedDamage);
                    break;
                case "2":
                    displayedDamage = getWeaponRandomDamage();
                    FightDataStorage.getInstance().getEnemy2().setHp(FightDataStorage.getInstance().getEnemy2().getHp() - displayedDamage);
                    break;
                case "3":
                    displayedDamage = getWeaponRandomDamage();
                    FightDataStorage.getInstance().getEnemy3().setHp(FightDataStorage.getInstance().getEnemy3().getHp() - displayedDamage);
                    break;
                case "null":
                    System.out.println("Select");
            }
        } catch (Exception ex) {
            System.out.println("First Select doesent count");
        }

    }

    public int getWeaponRandomDamage() { //gör random damage om man har ett svärt

        if (HeroDataStorage.getInstance().getWeapon() != null) {
            Random rand = new Random();
            int minDamage = HeroDataStorage.getInstance().getWeapon().getWeaponMinDamage();
            int maxDamage = HeroDataStorage.getInstance().getWeapon().getWeaponMaxDamage();
            int R = rand.nextInt(maxDamage - minDamage) + minDamage;
            return R + heroBaseDamage;
        }

        return heroBaseDamage; //skada bara basedamage om man inte har något vapen
    }

    public void heroAttack() { // kallar basicattack

        if (HeroDataStorage.getInstance().getWeapon() != null) {
            basicAttack(HeroDataStorage.getInstance().getWeapon());
        } else {
            basicAttack(null);
        }
    }

    public int getDisplayedDamage() { //Returnar displayed damage tillbaka till fighting scenen visuellt

        return displayedDamage;

    }

    public int specialAttack1() { //special attack som gör 3x skada med random, sen kollar den även vem man trycker på

        System.out.println("Massive BLOOOOOOOOW");
        int massiveBlow = getWeaponRandomDamage() * 3;

        switch (FightDataStorage.getInstance().getEnemyID()) {

            case "1":
                displayedDamage = massiveBlow;
                FightDataStorage.getInstance().getEnemy1().setHp(FightDataStorage.getInstance().getEnemy1().getHp() - displayedDamage);
                System.out.println(massiveBlow + "Damage with Massive blow");
                break;
            case "2":
                displayedDamage = massiveBlow;
                FightDataStorage.getInstance().getEnemy2().setHp(FightDataStorage.getInstance().getEnemy2().getHp() - displayedDamage);
                break;
            case "3":
                displayedDamage = massiveBlow;
                FightDataStorage.getInstance().getEnemy3().setHp(FightDataStorage.getInstance().getEnemy3().getHp() - displayedDamage);
                break;
            case "null":
                System.out.println("Error");
        }
        return massiveBlow;
    }

    public int specialAttack2() { //två attacker efter varandra

        System.out.println("Dubbel attack");
        int dubbelAttack = getWeaponRandomDamage();

        switch (FightDataStorage.getInstance().getEnemyID()) {

            case "1":
                displayedDamage = dubbelAttack;
                FightDataStorage.getInstance().getEnemy1().setHp(FightDataStorage.getInstance().getEnemy1().getHp() - displayedDamage);
                break;
            case "2":
                displayedDamage = dubbelAttack;
                FightDataStorage.getInstance().getEnemy2().setHp(FightDataStorage.getInstance().getEnemy2().getHp() - displayedDamage);
                break;
            case "3":
                displayedDamage = dubbelAttack;
                FightDataStorage.getInstance().getEnemy3().setHp(FightDataStorage.getInstance().getEnemy3().getHp() - displayedDamage);
                break;
            case "null":
                System.out.println("Error");
        }
        return dubbelAttack;
    }

    public int specialAttack3() { //cleave attack som skadar alla på banan
        System.out.println("Cleave attack");
        int cleaveAttack = getWeaponRandomDamage();

        try {

            displayedDamage = cleaveAttack;
            FightDataStorage.getInstance().getEnemy1().setHp(FightDataStorage.getInstance().getEnemy1().getHp() - displayedDamage);
            FightDataStorage.getInstance().getEnemy2().setHp(FightDataStorage.getInstance().getEnemy2().getHp() - displayedDamage);
            FightDataStorage.getInstance().getEnemy3().setHp(FightDataStorage.getInstance().getEnemy3().getHp() - displayedDamage);

        } catch (Exception ex) {
            System.out.println("Dunk");
        }
        return cleaveAttack;

    }
}
