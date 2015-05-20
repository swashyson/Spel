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
 * @author gul_h_000
 */
public class ConfigFile {

    private File file = new File("Config.cfg"); //Direction
    private int configSize = 8; //Storleken på config
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

    public void readConfigFile() {

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setSoundConfig() {

        switch (stringLastCharSound) {
            case "1":
                System.out.println("Ljud på");
                break;
            case "0":
                System.out.println("Ljud av");
                break;
            default:
                System.out.println("Ljudet kan bara vara 0 eller 1");
                setSound(1);
                break;
        }
    }

    public void setSound(int sound) {
        this.sound = sound;
        try (FileOutputStream FOS = new FileOutputStream(file);
                DataOutputStream DOS = new DataOutputStream(FOS);) {

            DOS.writeBytes("Sound: " + getSound());
            System.out.println("Du har ändrat ljudet till : " + getSound());
            readConfigFile();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getSound() {
        return sound;
    }
}
