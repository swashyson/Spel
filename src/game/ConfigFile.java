/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author Mohini, Mattias, Johan, Fredrik, Jonathan
 */
public class ConfigFile {

    private final File file = new File("Config.cfg"); //Direction
    private final int configSize = 8; //Storleken på config
    private int sound; //Ljudet

    //Ljud värden
    private String soundValue = "";
    private Character lastCharSound;
    private String stringLastCharSound;

    public void createConfigFile() {

        try (FileOutputStream FOS = new FileOutputStream(file);
                DataOutputStream DOS = new DataOutputStream(FOS);) {

            setSound(1);
            DOS.writeBytes("Sound: " + getSound());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void readConfigFile() { // läser config filen

        try (FileInputStream FIS = new FileInputStream(file);
                DataInputStream DIS = new DataInputStream(FIS)) {

            if (!file.exists()) {
                file.createNewFile();
                createConfigFile();
                System.out.println("Config fil finns inte, skapar en");
            } else {

                byte[] byteArray = new byte[configSize];

                for (int i = 0; i < configSize; i++) {

                    byteArray[i] = DIS.readByte();
                }
                soundValue = new String(byteArray);
                System.out.println(soundValue);
            }
            lastCharSound = soundValue.charAt(soundValue.length() - 1);
            stringLastCharSound = lastCharSound.toString();

            setSoundConfig();

        } catch (EOFException ex) {
            System.out.println("Förstår inte config filen");
            createConfigFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setSoundConfig() { //ändrar ljudet

        switch (stringLastCharSound) {
            case "1":
                System.out.println("Ljud på");
                sound = 1;
                break;
            case "0":
                System.out.println("Ljud av");
                sound = 0;
                break;
            default:
                System.out.println("Ljudet kan bara vara 0 eller 1");
                setSound(1);
                break;
        }
    }

    public void setSound(int sound) { //ändrar ljuder i config filen
        this.sound = sound;
        try (FileOutputStream FOS = new FileOutputStream(file);
                DataOutputStream DOS = new DataOutputStream(FOS);) {

            DOS.writeBytes("Sound: " + sound);
            System.out.println("Du har ändrat ljudet till : " + sound);
            readConfigFile();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getSound() { //hämta ljudet
        
        return sound;
    }
}
