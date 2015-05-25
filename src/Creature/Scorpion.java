/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Creature;

import DataStorage.HeroDataStorage;
import Items.Armor;
import java.util.Random;

/**
 *
 * @author Mohini
 */
public class Scorpion extends Enemy {

    public Scorpion(String name, int hp, int maxDamage, int minDamage, int speed) {
        this.name = name;
        this.hp = (hp * HeroDataStorage.getInstance().getHero().getLevel());
        this.maxHp = this.hp;
        this.maxDamage = maxDamage * (HeroDataStorage.getInstance().getHero().getLevel());
        this.minDamage = minDamage * (HeroDataStorage.getInstance().getHero().getLevel());
        this.speed = speed;
    }

    @Override
    protected void specialAttack1(Hero hero) {

    }

    @Override
    protected void specialAttack2(Hero hero) {

    }

    @Override
    public int getMaxDmg() {
        return maxDamage;
    }

    @Override
    public int getMinDmg() {
        return minDamage;
    }

    public int basicAttack() {

        Random rand = new Random();
        int minDmg = this.minDamage;
        int maxDmg = this.maxDamage;

        int dmg = rand.nextInt(maxDamage - minDamage) + minDamage;

        //System.out.println("Bear skadade dig med " + dmg);
        return dmg;
    }

    public int Attack(Armor armor) {

        Random randAttack = new Random();
        int randomInt = randAttack.nextInt(10) + 1;

        System.out.println("Random int = " + randomInt);

        if (HeroDataStorage.getInstance().getArmor() == null) {

            System.out.println("Hero har inget armor");
            if (randomInt <= 7) {

                return basicAttack();
            } else if (randomInt == 10) {

                return specialAttack1();
            } else if (randomInt == 8 || randomInt == 9) {

                return specialAttack2();
            }

        } else {

            if (randomInt == 1) {

                int armorValue = HeroDataStorage.getInstance().getArmor().getArmor();
                int calculate = basicAttack() - armorValue * HeroDataStorage.getInstance().getHero().getLevel();
                System.out.println("armorValue blockade " + armorValue * HeroDataStorage.getInstance().getHero().getLevel());
                System.out.println("Bear skadade dig med " + calculate);
                return calculate;

            } else if (randomInt == 2) {

                int armorValue = HeroDataStorage.getInstance().getArmor().getArmor();
                int calculate = specialAttack1() - armorValue * HeroDataStorage.getInstance().getHero().getLevel();
                System.out.println("armorValue blockade " + armorValue * HeroDataStorage.getInstance().getHero().getLevel());
                System.out.println("Bear skadade dig med " + calculate);
                return calculate;

            } else if (randomInt == 3) {

                int armorValue = HeroDataStorage.getInstance().getArmor().getArmor();
                int calculate = specialAttack2() - armorValue * HeroDataStorage.getInstance().getHero().getLevel();
                System.out.println("armorValue blockade " + armorValue * HeroDataStorage.getInstance().getHero().getLevel());
                System.out.println("Bear skadade dig med " + calculate);
                return calculate;

            }
        }
        return basicAttack();
    }

    public int specialAttack1() {

        Random rand = new Random();
        int minDmg = this.minDamage;
        int maxDmg = this.maxDamage;

        int dmg = rand.nextInt(maxDamage - minDamage) + minDamage;
        dmg = dmg * 3;

        System.out.println("SpecialAttack1 " + dmg);
        return dmg;
    }

    public int specialAttack2() {

        Random rand = new Random();
        int minDmg = this.minDamage;
        int maxDmg = this.maxDamage;

        int dmg = rand.nextInt(maxDamage - minDamage) + minDamage;

        System.out.println("SpecialAttack2 " + dmg);

        dmg = dmg * 2;

        return dmg;
    }

}
