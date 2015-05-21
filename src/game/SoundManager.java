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

    private int muteUnMute;

    private MediaPlayer backgroundSound;
    private Media defineBackgroundSoundFile;

    private MediaPlayer shortSound;
    private Media defineShortSoundFile;
    private ConfigFile config = new ConfigFile();

    //Läser av en textremsa som skickas från en annan klass. Därefter spelat motsvarande bakgrundsljud upp.
    public void defineBackgroundSound(String newBackgroundSound) {

        muteUnMute = config.getSound();

        if (newBackgroundSound == "Fight" /*&& muteUnMute == 1*/) {
            defineBackgroundSoundFile = new Media(getClass().getResource("sounds/FightingSound.wav").toString());
            System.out.println("playing fighting sound");
            playBackgroundSound();
        } //        else if (newBackgroundSound == "Inn" && muteUnMute == 1) {
        //            defineBackgroundSoundFile = new Media(getClass().getResource(null).toString());
        //            playBackgroundSound();
        //        } else if (newBackgroundSound == "Shop" && muteUnMute == 1) {
        //            defineBackgroundSoundFile = new Media(getClass().getResource(null).toString());
        //            playBackgroundSound();
        //        } 
        else if (newBackgroundSound == "City" /*&& muteUnMute == 1*/) {
            defineBackgroundSoundFile = new Media(getClass().getResource("sounds/CitySound_ChirpingBirds.wav").toString());
            System.out.println("playing city sound");
            playBackgroundSound();
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
    public void stopTheSound(String sound) {
//    public void stopTheSound() {
        if (sound == "City"/*||sound=="Fight"*/) {
            System.out.println("Stopping: " + sound + " in soundManager");
            backgroundSound.stop();
            
        } else if (sound == "Fight") {
            System.out.println("Stopping: " + sound + " in soundManager");
            backgroundSound.stop();
        }
    }

    //läser av en textremsa som skickas från andra klasser. därefter spelas ett kort ljud upp
    public void defineShortSound(String shortSound) {

        //lägg till referenser till annat som också använder korta ljud, till exempel bear, scorpion mm
        if (shortSound == "purchase" && muteUnMute == 1) {
            defineShortSoundFile = new Media(getClass().getResource("sounds/purchaseItem.mp3").toString());
            System.out.println("played purchase sound");
            playShortSound();
        } else if (shortSound == "button_click" && muteUnMute == 1) { // ska vi ha ljud till knapparna???
            defineShortSoundFile = new Media(getClass().getResource("sounds/buttonEffect.aif").toString());
            playShortSound();
        } else if (shortSound == "arrow_attack" && muteUnMute == 1) {
            defineShortSoundFile = new Media(getClass().getResource("sounds/ArrowDamage.wav").toString());
            playShortSound();
        } else if (shortSound == "bear_attack" && muteUnMute == 1) {
            defineShortSoundFile = new Media(getClass().getResource("sounds/BearAttack.wav").toString());
            playShortSound();
        } else if (shortSound == "snake_attack" && muteUnMute == 1) {
            defineShortSoundFile = new Media(getClass().getResource("sounds/HissingSnake.wav").toString());
            playShortSound();
        }else if (shortSound == "error" && muteUnMute == 1){
            defineShortSoundFile = new Media(getClass().getResource("sounds/error_sound.wav").toString());
            playShortSound();
        }else if (shortSound == "level_up" && muteUnMute == 1){
            defineShortSoundFile = new Media(getClass().getResource("sounds/level_up.wav").toString());
            playShortSound();
        }else if(shortSound == "entrance" && muteUnMute == 1){
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

}
