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

    private boolean muteUnMute; // checka av i varje metod om detta är sant eller inte

    private MediaPlayer fightBackgroundSound;
//    private Media fightBackSoundFile = new Media(getClass().getResource("/Music/FightingSound.mp3").toString());

    //one individual background sound per scene.
    public void playFightingBackgroundSound() { //ha någon sorts input, string?
        System.out.println("you've arrived in playFightinBackgroundSound");
//        Media fightBackSoundFile = new Media(getClass().getResource("FightingSound.mp3").toString());
//        try {
//            fightBackgroundSound = new MediaPlayer(fightBackSoundFile);
//            fightBackgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
//            fightBackgroundSound.setVolume(0.7);
////            fightBackgroundSound.play();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.exit(0);
//            e.printStackTrace();
//        }

        String mediaFile = "src/game/Resources/FightingSound.mp3";
        try {
            Media media = new Media(Paths.get(mediaFile).toUri().toString());
            AudioClip audioClip = new AudioClip(media.getSource());
            audioClip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playInnBackgroundSound() {
        //sätt ett lugnt ljud här
        System.out.println("you've arrived in playInnBackgroundSound");
    }

    public void playShopBackgroundSound() {
        System.out.println("you've arrived in playShopBackgroundSound");
    }

    public void playCityBackgroundSound() { // ha ljud i city?
        System.out.println("you've arrived in playCityBackgroundSound");
    }

    public void playShortSound() {

    }

//    public void playBackgroundSound(String newBackgroundSound){ //ha någon sorts input, string?
//        
//        if(newBackgroundSound == "fight"){
//            fightBackgroundSound = new MediaPlayer(fightBackSoundFile);
//            fightBackgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
////            fightBackgroundSound.play();
//        }
//        
//        //kolla om muteUmMute är satt som try
//        //kolla om newBackgroundSound har ett visst namn, spela upp motsvarande ljud.
//        //använd if-satser
//        
//        //kolla om muteUnMute är falskt
//        
//        
//        
//    }
//    
//    
//    //plays individual sounds, f.ex. when the hero attacks one sound appears
//    public void playIndividualSound(String newIndividualSound){ // ha någon sorts input, string?
//        
//        //kolla om muteUnMute är sant
//        //kolla om newIndividualSound har ett visst namn, spela upp motsvarande ljud
//        //använd if-satser
//        
//        //kolla om muteUnMute är falskt
//    }
//    
    public void stopTheSound(String stopSound) {
        //läs av parametern och stäng av det specifika ljudet.

        System.out.println("Stop sound in: " + stopSound);

        if (stopSound == "Fight") {
//            fightBackgroundSound.stop();
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
