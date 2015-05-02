/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Mohini
 */
public abstract class Enemy extends Creature{
    
    protected int maxDamage;
    protected int minDamage;
    
    protected Enemy(Hero hero){
        
    }
    
    protected int getMaxDmg(){
        return maxDamage;
    }
    
    protected int getMinDmg(){
        return minDamage;
    }
    
    public abstract void specialAttack1();
    public abstract void specialAttack2();
    
}
