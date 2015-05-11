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
public class Weapon extends Item {

    private final int weaponID;
    private int weaponMinDamage;
    private int weaponMaxDamage;
    private int weaponSpeed;
    private int weaponLevel;
    private int weaponType;

    public Weapon(String name, int weaponID, int weaponMinDamage, int weaponMaxDamage, int weaponSpeed, int weaponLevel, int weaponType) {
        super(name);
        this.weaponID = weaponID;
        this.weaponMinDamage = weaponMinDamage;
        this.weaponMaxDamage = weaponMaxDamage;
        this.weaponSpeed = weaponSpeed;
        this.weaponLevel = weaponLevel;
        this.weaponType = weaponType;
    }

    public int getWeaponID() {
        return weaponID;
    }

    public int getWeaponMinDamage() {
        return weaponMinDamage;
    }

    public void setWeaponMinDamage(int weaponMinDamage) {
        this.weaponMinDamage = weaponMinDamage;
    }

    public int getWeaponMaxDamage() {
        return weaponMaxDamage;
    }

    public void setWeaponMaxDamage(int weaponMaxDamage) {
        this.weaponMaxDamage = weaponMaxDamage;
    }

    public int getWeaponSpeed() {
        return weaponSpeed;
    }

    public void setWeaponSpeed(int weaponSpeed) {
        this.weaponSpeed = weaponSpeed;
    }

    public int getWeaponLevel() {
        return weaponLevel;
    }

    public void setWeaponLevel(int weaponLevel) {
        this.weaponLevel = weaponLevel;
    }

    public int getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(int weaponType) {
        this.weaponType = weaponType;
    }

}
