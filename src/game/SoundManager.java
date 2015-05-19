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

/**
 *
 * @author Mohini
 */
public class SoundManager {

    private boolean muteUnMute;
    
    private MediaPlayer fightBackgroundSound;
    private Media mediaFile;

    public void playBackgroundSound(String newBackgroundSound) { //ha någon sorts input, string?

        if (newBackgroundSound == "Fight") {

            try {
                mediaFile = new Media(getClass().getResource("Recourses/FightingSound.mp3").toString());

                System.out.println(mediaFile);
                fightBackgroundSound = new MediaPlayer(mediaFile);
                fightBackgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
                fightBackgroundSound.setVolume(0.5);
                fightBackgroundSound.play();
//            fightBackgroundSound.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (newBackgroundSound == "Inn") {
            try {
                mediaFile = new Media(getClass().getResource(null).toString());

                System.out.println(mediaFile);
                fightBackgroundSound = new MediaPlayer(mediaFile);
                fightBackgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
                fightBackgroundSound.setVolume(0.5);
                fightBackgroundSound.play();
//            fightBackgroundSound.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (newBackgroundSound == "Shop") {
            try {
                mediaFile = new Media(getClass().getResource(null).toString());

                System.out.println(mediaFile);
                fightBackgroundSound = new MediaPlayer(mediaFile);
                fightBackgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
                fightBackgroundSound.setVolume(0.5);
                fightBackgroundSound.play();
//            fightBackgroundSound.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopTheSound(String stopSound) {
        //läs av parametern och stäng av det specifika ljudet.

        System.out.println("Stop sound in: " + stopSound);

        if (stopSound == "Fight") {
            fightBackgroundSound.stop();
        } else if (stopSound == "Shop") {

        } else if (stopSound == "Inn") {

        }
    }

    public void muteSounds(boolean muteUnMute) {
        this.muteUnMute = muteUnMute;
//        if(muteUnMute == true){
//            //sätt alla ljud som stop
//        }
//        else if(muteUnMute == false){
//            //referera till
//        }
    }

}
