/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Creature;


import DataStorage.HeroDataStorage;
import java.util.Random;

/**
 *
 * @author Mohini
 */
public class Snake extends Enemy {

    public Snake(String name, int hp, int maxDamage, int minDamage, int speed) {
        this.name = name;
        this.hp = (hp * HeroDataStorage.getInstance().getHero().getLevel());
        this.maxHp = this.hp;
        this.maxDamage = maxDamage*(HeroDataStorage.getInstance().getHero().getLevel());
        this.minDamage = minDamage*(HeroDataStorage.getInstance().getHero().getLevel());
        this.speed = speed;
    }

    @Override
    protected void specialAttack1(Hero hero) {

    }

    @Override
    protected void specialAttack2(Hero hero) {

    }

    
    public void attack(Hero hero) {
        hero.setHp(hero.getHp() - getDmg());
    }

    @Override
    public int getMaxDmg() {
        return maxDamage;
    }

    @Override
    public int getMinDmg() {
        return minDamage;
    }

    public int getDmg() {
        Random rand = new Random();
        int minDmg = this.minDamage;
        int maxDmg = this.maxDamage;
        int dmg = rand.nextInt(maxDmg - minDmg) + minDmg;

        return dmg;
    }

    public int basicAttack() {

        return 1;
    }

}
