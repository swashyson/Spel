/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import game.Item;

/**
 *
 * @author Mattias
 */
public class Armor extends Item {

    private final int armorID;
    private int armor;
    private int armorType;
    private int armorLevel;
    private int armorSpeed;

    public Armor(String name, int armorID, int armor, int armorType, int armorLevel, int armorSpeed) {
        super(name);
        this.armorID = armorID;
        this.armor = armor;
        this.armorType = armorType;
        this.armorLevel = armorLevel;
        this.armorSpeed = armorSpeed;
    }

    public void setArmor(int armor) {
        this.armor = armor;

    }

    public int getArmor() {
        return armor;
    }

    public void setArmorType(int armorType) {
        this.armorType = armorType;

    }

    public int getArmorType() {
        return armorType;
    }

    public void setArmorLevel(int armorLevel) {
        this.armorLevel = armorLevel;

    }

    public int getArmorLevel() {
        return armorLevel;
    }

    public void setArmorSpeed(int armorSpeed) {
        this.armorSpeed = armorSpeed;

    }

    public int getArmorSpeed() {
        return armorSpeed;
    }

    public int getArmorID() {
        return armorID;
    }

}
