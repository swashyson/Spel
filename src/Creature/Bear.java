/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Creature;

import java.util.Random;

/**
 *
 * @author Mohini
 */
public class Bear extends Enemy {

    public Bear(String name, int hp, int maxDamage, int minDamage, int speed) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.speed = speed;

    }

    @Override
    protected void specialAttack1(Hero hero) {

    }

    @Override
    protected void specialAttack2(Hero hero) {

    }
   
    
    public void attack(Hero hero) {
        Random whatAttack = new Random();
        int attack = whatAttack.nextInt(9) + 1;
        if(attack != 10){
        hero.setHp(hero.getHp() - getDmg());
        }
        else{
            Random whatSpecialAttack = new Random();
            int specialAttack = whatSpecialAttack.nextInt(1)+1;
            if(specialAttack == 1){
                specialAttack1(hero);
            }else {
                specialAttack2(hero);
            }
        }
    }

    public int getMaxDmg() {
        return maxDamage;
    }

    
    
    public int getMinDmg() {
        return minDamage;
    }
    
    public int getDmg(){
        Random rand = new Random();
        int minDmg = this.minDamage;
        int maxDmg = this.maxDamage;
        int dmg = rand.nextInt(maxDmg - minDmg) + minDmg;
        
        return dmg;
    }

}
