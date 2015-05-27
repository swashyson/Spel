/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Mattias
 */
public abstract class Item {

    private final String name;

    public Item(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

}
