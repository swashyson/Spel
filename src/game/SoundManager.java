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

    private boolean muteUnMute;

    private MediaPlayer backgroundSound;
    private Media defineBackgroundSoundFile;
    
    private MediaPlayer shortSound;
    private Media defineShortSoundFile;

    public void defineBackgroundSound(String newBackgroundSound) { //ha någon sorts input, string?

        muteUnMute = getMuteSounds(); // ha kvar???

//        if(muteUnMute && !muteUnMute){
//            muteUnMute = true;
//        }
        if (newBackgroundSound == "Fight" && !muteUnMute) {

            try {
                defineBackgroundSoundFile = new Media(getClass().getResource("sounds/FightingSound.mp3").toString());

                playBackgroundSound();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (newBackgroundSound == "Inn" && !muteUnMute) {
            try {
                defineBackgroundSoundFile = new Media(getClass().getResource(null).toString());

                playBackgroundSound();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (newBackgroundSound == "Shop" && !muteUnMute) {
            try {
                defineBackgroundSoundFile = new Media(getClass().getResource(null).toString());

                playBackgroundSound();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (newBackgroundSound == "City" && !muteUnMute) {
            try {
                defineBackgroundSoundFile = new Media(getClass().getResource("sounds/CitySound_ChirpingBirds.wav").toString());

                playBackgroundSound();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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

    public void stopTheSound(/*String stopSound*/) {
        //läs av parametern och stäng av det specifika ljudet.
        backgroundSound.stop();
    }

    // ha kvar eller ta bort???
    public void setMuteSounds(boolean muteUnMute) {
        this.muteUnMute = muteUnMute;
        System.out.println(muteUnMute);
//        if(muteUnMute){
//            //sätt alla ljud som stop
//            muteUnMute = true;
//        }
//        else if(!muteUnMute){
//            //referera till
//            muteUnMute = false;
//        }
    }

    // ha kvar eller ta bort???
    public boolean getMuteSounds() {
        return muteUnMute;
    }

    public void defineShortSound(String shortSound) {

        //lägg till referenser till annat som också använder korta ljud, till exempel bear, scorpion mm
        if (shortSound == "purchase" && !muteUnMute) {
            try {
                defineShortSoundFile = new Media(getClass().getResource("sounds/purchaseItem.mp3").toString());

                playShortSound();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else if (shortSound == "button" && !muteUnMute) { // ska vi ha ljud till knapparna???
            try {
                defineShortSoundFile = new Media(getClass().getResource("sounds/buttonEffect.aif").toString());

                playShortSound();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void playShortSound() {
        try {
            defineBackgroundSoundFile = new Media(getClass().getResource("sounds/buttonEffect.aif").toString());

            System.out.println(defineShortSoundFile);
            shortSound = new MediaPlayer(defineShortSoundFile);
            shortSound.setVolume(0.5);
            shortSound.setAutoPlay(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
