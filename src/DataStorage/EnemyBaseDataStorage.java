package DataStorage;

import Creature.Bear;
import Creature.Scorpion;
import Creature.Snake;
import Creature.Spider;
import Creature.Wolf;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonni
 */
public class EnemyBaseDataStorage {
    private static EnemyBaseDataStorage enemyDataStorage;
    private Bear bear;
    private Scorpion scorpion;
    private Snake snake;
    private Spider spider;
    private Wolf wolf;
    

    public static EnemyBaseDataStorage getInstance() {
        if (enemyDataStorage == null) {
            enemyDataStorage = new EnemyBaseDataStorage();
        }
        
        return enemyDataStorage;
    }
    public void setBear(Bear bear){
        this.bear = bear;
    }
    public Bear getBear(){
        return bear;
    }
    
    public void setScorpion(Scorpion scorpion){
        this.scorpion = scorpion;
    }
    
    public Scorpion getScorpion(){
        return scorpion;
    }
    public void setSnake(Snake snake){
        this.snake = snake;
    }
    
    public Snake getSnake(){
        return snake;
    }
    public void setSpider(Spider spider){
        this.spider = spider;
    }
    
    public Spider getSpider(){
        return spider;
    }
    public void setWolf(Wolf wolf){
        this.wolf = wolf;
    }
    
    public Wolf getWolf(){
        return wolf;
    }
}
