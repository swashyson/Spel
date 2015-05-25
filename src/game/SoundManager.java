/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Random;
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

    // Arrays used for keeping track of which sounds to choose between when random sounds are needed
    private String[] chooseArmorWeaponVoice = {};
    private String[] heroHurt = {"hero_hit_1", "hero_hit_2"};
    private String[] heroAttack = {"warrior_attack", "bowman_attack", "mage_attack"};
    private String[] victory = {"thatwaseasy", "nailedit"};
    private String[] enemyDeath = {"itsgone"};
    private String[] innKeeperComments = {};
    private String[] innSound = {"snoring", "inn_ambience"};

    private Random random = new Random();
    private int soundToPlay;

    
    // Every sound in the game is defined here, from background sounds to shorter sounds
    public void defineSound(String sound) {

        try {

            readTheConfigFile();

            if (sound == "Fight" && soundOn == true) {
                defineBackgroundSoundFile = new Media(getClass().getResource("sounds/FightingSound.mp3").toString());
                System.out.println("playing " + sound);
                playBackgroundSound();
            } else if (sound == "City" && soundOn == true) {
                defineBackgroundSoundFile = new Media(getClass().getResource("sounds/CitySound_ChirpingBirds.mp3").toString());
                System.out.println("playing " + sound);
                playBackgroundSound();
            } else if (sound == "inn_ambience" && soundOn == true) {
                defineBackgroundSoundFile = new Media(getClass().getResource("sounds/inn_ambience.mp3").toString());
                System.out.println("playing " + sound);
                playBackgroundSound();
            } else if (sound == "snoring" && soundOn == true) {
                defineBackgroundSoundFile = new Media(getClass().getResource("sounds/snoring.mp3").toString());
                System.out.println("playing " + sound);
                playBackgroundSound();
            } else if (sound == "purchase" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/purchaseItem.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "button_click" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/buttonEffect.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "error" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/error_sound.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "level_up" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/level_up.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "entrance" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/entranceBell.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "nailedit" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/nailedit.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "thatwaseasy" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/thatwaseasy.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "itsgone" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/itsgone.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "heroDeath" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/hero_death.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "warrior_attack" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/warrior_attack_1.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "bowman_attack" && soundOn == true) {
                System.out.println("played " + sound);
                defineShortSoundFile = new Media(getClass().getResource("sounds/bowman_attack_1.mp3").toString());
                playShortSound();
            } else if (sound == "mage_attack" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/mage_attack_1.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "bear_attack" && soundOn == true) {
                System.out.println("played " + sound);
                defineShortSoundFile = new Media(getClass().getResource("sounds/BearAttack.mp3").toString());
                playShortSound();
            } else if (sound == "snake_attack" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/HissingSnake.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "hero_hit_1" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/hero_hit_1.mmp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "hero_hit_2" && soundOn == true) {
                defineShortSoundFile = new Media(getClass().getResource("sounds/hero_hit_2.mp3").toString());
                System.out.println("played " + sound);
                playShortSound();
            } else if (sound == "applause" && soundOn == true) {
                defineBackgroundSoundFile = new Media(getClass().getResource("sounds/applause.mp3").toString());
                System.out.println("played " + sound);
                playBackgroundSound();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //Randomizes the sounds in the global arrays defined at the top
    public void randomizeSounds(String soundsToRandomize, int heroType) {
        //The method is using references to the herotype (1-3 for the specific class), that is to be 
        //able to play the correct sounds for each herotype in the fight
        //since there is sounds applying to every herotype, 0 is used for putting it to all of them
        try {

            readTheConfigFile();

            if (soundsToRandomize == "hero_being_hit" && soundOn == true && heroType == 0) {
                soundToPlay = random.nextInt(2);
                System.out.println(soundToPlay);
                if (soundToPlay == 0) {
                    defineSound(heroHurt[0]);
                    System.out.println("played " + heroHurt[0]);
                } else if (soundToPlay == 1) {
                    defineSound(heroHurt[1]);
                    System.out.println("played " + heroHurt[1]);
                }
            } else if (soundsToRandomize == "hero_attacking" && soundOn == true && heroType == 1) {
                defineSound(heroAttack[heroType - 1]);
                System.out.println("played " + heroAttack[heroType - 1]);
            } else if (soundsToRandomize == "hero_attacking" && soundOn == true && heroType == 2) {
                defineSound(heroAttack[heroType - 1]);
                System.out.println("played " + heroAttack[heroType - 1]);
            } else if (soundsToRandomize == "hero_attacking" && soundOn == true && heroType == 3) {
                defineSound(heroAttack[heroType - 1]);
                System.out.println("played " + heroAttack[heroType - 1]);
            } else if (soundsToRandomize == "victory" && soundOn == true && heroType == 0) {
                soundToPlay = random.nextInt(2);
                System.out.println(soundToPlay);
                if (soundToPlay == 0) {
                    defineSound(victory[0]);
                    System.out.println("played " + victory[0]);
                } else if (soundToPlay == 1) {
                    defineSound(victory[1]);
                    System.out.println("played " + victory[1]);
                }
            } else if (soundsToRandomize == "inn" && soundOn == true && heroType == 0) {
                soundToPlay = random.nextInt(2);
                System.out.println(soundToPlay);
                if (soundToPlay == 0) {
                    defineSound(innSound[0]);
                    System.out.println("played " + innSound[0]);
                } else if (soundToPlay == 1) {
                    defineSound(innSound[1]);
                    System.out.println("played " + innSound[1]);
                }
            }
        } catch (Exception e) {

        }

    }

    //All defined short sounds uses this method to play once
    private void playShortSound() {
        try {
            System.out.println(defineShortSoundFile);
            shortSound = new MediaPlayer(defineShortSoundFile);
            shortSound.setVolume(1.5);
            shortSound.setAutoPlay(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //All defined background sounds uses this method for looping the sound until it is stopped
    private void playBackgroundSound() {
        try {
            System.out.println(defineBackgroundSoundFile);
            backgroundSound = new MediaPlayer(defineBackgroundSoundFile);
            backgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundSound.setVolume(0.5);
//            backgroundSound.setAutoPlay(true);
            backgroundSound.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method turns off the sound, whatever sound it is that is playing
    public void stopTheSound() {
        if (soundOn == true) {
            backgroundSound.stop();
        }
    }

    public boolean getSoundOn() {
        return soundOn;
    }

    private void readTheConfigFile() {
        cF.readConfigFile();
        if (cF.getSound() == 1) {// ha kvar???
            soundOn = true;
        } else if (cF.getSound() == 0) {
            soundOn = false;
        }
    }

}
