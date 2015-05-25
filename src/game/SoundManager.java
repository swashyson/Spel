/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Mohini
 */
public class SoundManager {

    private boolean soundOn;

    private MediaPlayer backgroundSound;
    private Media defineBackgroundSoundFile;

    private MediaPlayer shortSound;
    private Media defineShortSoundFile;
    
    private final ConfigFile cF = new ConfigFile();

    public void defineBackgroundSound(String newBackgroundSound) { //ha någon sorts input, string?
        try{
        cF.readConfigFile();
        if(cF.getSound() == 1){// ha kvar???
            soundOn = true;
        }else if(cF.getSound() == 0) {
            soundOn = false;
        }
        
        if (newBackgroundSound == "Fight" && soundOn == true) {
            defineBackgroundSoundFile = new Media(getClass().getResource("sounds/FightingSound.wav").toString());
            System.out.println("playing fighting sound");
            playBackgroundSound();
        } 
        else if (newBackgroundSound == "City" && soundOn == true) {
            defineBackgroundSoundFile = new Media(getClass().getResource("sounds/CitySound_ChirpingBirds.wav").toString());
            System.out.println("playing city sound");
            playBackgroundSound();
        }
        }catch(Exception ex){
            
        }
    }

    //Spelar upp själva ljudet och loopar det med ungefär 1 sekund innan den startar om.
    private void playBackgroundSound() {
        try {
            System.out.println(defineBackgroundSoundFile);
            backgroundSound = new MediaPlayer(defineBackgroundSoundFile);
            backgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundSound.setVolume(0.5);
            backgroundSound.setAutoPlay(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //stänger av bakgrundsljudet som spelas.
    public void stopTheSound() {
            backgroundSound.stop();
    }

    public void defineShortSound(String shortSound) {

        //lägg till referenser till annat som också använder korta ljud, till exempel bear, scorpion mm
        if (shortSound == "purchase" && soundOn == true) {
            defineShortSoundFile = new Media(getClass().getResource("sounds/purchaseItem.mp3").toString());
            System.out.println("played purchase sound");
            playShortSound();
        } else if (shortSound == "button_click" && soundOn == true) { // ska vi ha ljud till knapparna???
            defineShortSoundFile = new Media(getClass().getResource("sounds/buttonEffect.aif").toString());
            playShortSound();
        } else if (shortSound == "arrow_attack" && soundOn == true) {
            defineShortSoundFile = new Media(getClass().getResource("sounds/ArrowDamage.wav").toString());
            playShortSound();
        } else if (shortSound == "bear_attack" && soundOn == true) {
            defineShortSoundFile = new Media(getClass().getResource("sounds/BearAttack.wav").toString());
            playShortSound();
        } else if (shortSound == "snake_attack" && soundOn == true) {
            defineShortSoundFile = new Media(getClass().getResource("sounds/HissingSnake.wav").toString());
            playShortSound();
        }else if (shortSound == "error" && soundOn == true){
            defineShortSoundFile = new Media(getClass().getResource("sounds/error_sound.wav").toString());
            playShortSound();
        }else if (shortSound == "level_up" && soundOn == true){
            defineShortSoundFile = new Media(getClass().getResource("sounds/level_up.wav").toString());
            playShortSound();
        }else if(shortSound == "entrance" && soundOn == true){
            defineShortSoundFile = new Media(getClass().getResource("sounds/entranceBell.wav").toString());
            playShortSound();
        }

    }

    //Spelar upp ett kort ljud utan att loopa det
    private void playShortSound() {
        try {
            System.out.println(defineShortSoundFile);
            shortSound = new MediaPlayer(defineShortSoundFile);
            shortSound.setVolume(0.5);
            shortSound.setAutoPlay(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean getSoundOn(){
        return soundOn;
    } 

}
