/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Creature;



/**
 *
 * @author Mohini, Mattias, Johan, Fredrik, Jonathan
 */
public abstract class Enemy extends Creature {

    protected int maxDamage;
    protected int minDamage;
    
    protected abstract int getMaxDmg();

    protected abstract int getMinDmg();
    
    protected abstract int specialAttack1();
    
    protected abstract int specialAttack2();
           

}
