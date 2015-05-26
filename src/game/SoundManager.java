/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Mohini, Mattias, Johan, Jonathan, Fredrik
 */
public class SoundManager {

    private boolean soundOn;

    private MediaPlayer backgroundSound;
    private Media defineBackgroundSoundFile;

    private MediaPlayer primaryShortSound;
    private Media definePrimaryShortSoundFile;

    private MediaPlayer secondaryShortSound;
    private Media defineSecondaryShortSoundFile;

    private final ConfigFile cF = new ConfigFile();

    // Arrays used for keeping track of which sounds to choose between when special occations occur
    private String[] heroHurt = {"hero_hit_1", "hero_hit_2"};

    private String[] heroAttack = {"warrior_attack", "bowman_attack", "mage_attack"};
    private String[] specialAttack_2 = {"warrior_special_2", "bowman_special_2", "mage_special_2"};

    private String[] innSound = {"snoring", "inn_ambience"};

    private final Random random = new Random();
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
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/purchaseItem.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "button_click" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/buttonEffect.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "error" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/error_sound.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "level_up" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/level_up.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "entrance" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/entranceBell.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "applause" && soundOn == true) {
                defineSecondaryShortSoundFile = new Media(getClass().getResource("sounds/applause.mp3").toString());
                System.out.println("played " + sound);
                playSecondaryShortSound();
            } else if (sound == "gameover" && soundOn == true) {
                defineSecondaryShortSoundFile = new Media(getClass().getResource("sounds/gameover.mp3").toString());
                System.out.println("played " + sound);
                playSecondaryShortSound();
            } else if (sound == "hero_hit_1" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/hero_hit_1.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "hero_hit_2" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/hero_hit_2.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "heroDeath" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/hero_death.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "warrior_attack" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/warrior_attack_1.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "bowman_attack" && soundOn == true) {
                System.out.println("played " + sound);
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/bowman_attack_1.mp3").toString());
                playPrimaryShortSound();
            } else if (sound == "mage_attack" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/mage_attack_1.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "warrior_special_2" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/warrior_special_2.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "bowman_special_2" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/bowman_special_2.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "mage_special_2" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/mage_special_2.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "hero_special_3" && soundOn == true) {
                definePrimaryShortSoundFile = new Media(getClass().getResource("sounds/explosion.mp3").toString());
                System.out.println("played " + sound);
                playPrimaryShortSound();
            } else if (sound == "bear_attack" && soundOn == true) {
                System.out.println("played " + sound);
                defineSecondaryShortSoundFile = new Media(getClass().getResource("sounds/BearAttack.mp3").toString());
                playSecondaryShortSound();
            } else if (sound == "snake_attack" && soundOn == true) {
                defineSecondaryShortSoundFile = new Media(getClass().getResource("sounds/hissingSnake.mp3").toString());
                System.out.println("played " + sound);
                playSecondaryShortSound();
            } else if (sound == "spider_attack" && soundOn == true) {
                defineSecondaryShortSoundFile = new Media(getClass().getResource("sounds/spider_squeek.mp3").toString());
                System.out.println("played " + sound);
                playSecondaryShortSound();
            } else if (sound == "scorpion_attack" && soundOn == true) {
                defineSecondaryShortSoundFile = new Media(getClass().getResource("sounds/swoosh_hit.mp3").toString());
                System.out.println("played " + sound);
                playSecondaryShortSound();
            } else if (sound == "wolf_attack" && soundOn == true) {
                defineSecondaryShortSoundFile = new Media(getClass().getResource("sounds/wolf_barking.mp3").toString());
                System.out.println("played " + sound);
                playSecondaryShortSound();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //Plays the sounds in the global arrays defined at the top, at occations where the
    //sounds need to be specific to the hero, or at occations where the sounds need 
    //to be random
    public void playSoundAtSpecialOccation(String specialOccationSound, int heroType) {
        //The method is using references to the herotype (1-3 for the specific class), that is to be 
        //able to play the correct sounds for each herotype in the fight
        //since there is sounds applying to every herotype, 0 is used for applying it to all of them
        try {

            readTheConfigFile();

            if (specialOccationSound == "hero_being_hit" && soundOn == true && heroType == 0) {
                soundToPlay = random.nextInt(2);
                System.out.println(soundToPlay);
                if (soundToPlay == 0) {
                    defineSound(heroHurt[0]);
                    System.out.println("played " + heroHurt[0]);
                } else if (soundToPlay == 1) {
                    defineSound(heroHurt[1]);
                    System.out.println("played " + heroHurt[1]);
                }
            } else if (specialOccationSound == "hero_special_1" && soundOn == true && heroType == 1) {
                defineSound(heroAttack[0]);
                System.out.println("played " + heroAttack[0]);
            } else if (specialOccationSound == "hero_special_1" && soundOn == true && heroType == 2) {
                defineSound(heroAttack[0]);
                System.out.println("played " + heroAttack[0]);
            } else if (specialOccationSound == "hero_special_1" && soundOn == true && heroType == 3) {
                defineSound(heroAttack[0]);
                System.out.println("played " + heroAttack[0]);
            } else if (specialOccationSound == "hero_special_2" && soundOn == true && heroType == 1) {
                defineSound(specialAttack_2[0]);
                System.out.println("played " + specialAttack_2[0]);
            } else if (specialOccationSound == "hero_special_2" && soundOn == true && heroType == 2) {
                defineSound(specialAttack_2[1]);
                System.out.println("played " + specialAttack_2[1]);
            } else if (specialOccationSound == "hero_special_2" && soundOn == true && heroType == 3) {
                defineSound(specialAttack_2[2]);
                System.out.println("played " + specialAttack_2[2]);
            } else if (specialOccationSound == "hero_special_3" && soundOn == true && heroType == 0) {
                defineSound("hero_special_3");
                System.out.println("played hero_special_3");
            } else if (specialOccationSound == "hero_attacking" && soundOn == true && heroType == 1) {
                defineSound(heroAttack[0]);
                System.out.println("played " + heroAttack[0]);
            } else if (specialOccationSound == "hero_attacking" && soundOn == true && heroType == 2) {
                defineSound(heroAttack[1]);
                System.out.println("played " + heroAttack[1]);
            } else if (specialOccationSound == "hero_attacking" && soundOn == true && heroType == 3) {
                defineSound(heroAttack[2]);
                System.out.println("played " + heroAttack[2]);
            } else if (specialOccationSound == "inn" && soundOn == true && heroType == 0) {
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

    // Plays short sounds 
    private void playPrimaryShortSound() {
        try {
            System.out.println(definePrimaryShortSoundFile);
            primaryShortSound = new MediaPlayer(definePrimaryShortSoundFile);
            primaryShortSound.setVolume(1.5);
            primaryShortSound.setAutoPlay(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // To avoid short sounds not playing in the previous method,
    // this method acts as a failsafe just to be
    // able to play sounds in parallel to each other
    private void playSecondaryShortSound() {
        try {
            System.out.println(defineSecondaryShortSoundFile);
            secondaryShortSound = new MediaPlayer(defineSecondaryShortSoundFile);
            secondaryShortSound.setVolume(1.5);
            secondaryShortSound.setAutoPlay(true);
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

    //This method turns off the specified sound in the class it's called from, whatever sound it is that it is playing
    public void stopTheSound(String stop) {
        if (soundOn == true) {
            if (stop == "background") {
                backgroundSound.stop();
            } else if (stop == "primary") {
                primaryShortSound.stop();
            } else if (stop == "secondary") {
                secondaryShortSound.stop();
            }
        }
    }

    public void delay() {

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
