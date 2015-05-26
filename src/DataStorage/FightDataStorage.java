/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStorage;

import Creature.*;

/**
 *
 * @author @author Mohini, Mattias, Johan, Fredrik, Jonathan
 */
public class FightDataStorage {
    private static FightDataStorage fightDataStorage ;
    
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    
    private String EnemyID;

    public static FightDataStorage getInstance() {
        if (fightDataStorage  == null) {
            fightDataStorage  = new FightDataStorage();
        }

        return fightDataStorage ;
    }
    
    public void setEnemy1(Enemy enemy){
        this.enemy1 = enemy;
    }
    public Enemy getEnemy1(){
        
        return enemy1;
        
    }
    public void setEnemy2(Enemy enemy){
        this.enemy2 = enemy;
    }
    public Enemy getEnemy2(){
        return enemy2;
    }
    public void setEnemy3(Enemy enemy){
        this.enemy3 = enemy;
    }
    public Enemy getEnemy3(){
        return enemy3;
    }
    public void setEnemyID(String EnemyID){
        this.EnemyID = EnemyID;
    }
    public String getEnemyID(){
        return EnemyID;
    }
}
