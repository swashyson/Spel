/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Creature;

import game.Hero;

/**
 *
 * @author Mohini
 */
public class Bear extends Enemy {
    
    public Bear(String name,int hp, int maxDamage, int minDamage, int speed){
        this.name = name;
        this.hp = hp;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.speed = speed;
                
    }
    @Override
    protected void specialAttack1() {

    }

    @Override
    protected void specialAttack2() {

    }

    @Override
    protected Enemy attack(Hero hero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected int getMaxDmg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected int getMinDmg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
