/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Creature;

import Creature.Creature;

/**
 *
 * @author Mohini
 */
public abstract class Enemy extends Creature {

    protected int maxDamage;
    protected int minDamage;

    protected abstract Enemy attack(Hero hero);

    protected abstract int getMaxDmg();

    protected abstract int getMinDmg();

    protected abstract void specialAttack1();

    protected abstract void specialAttack2();

}
