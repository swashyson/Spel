/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author gul_h_000
 */
public interface HoverMouseInterface {

    // Interface för att inte kunna ändra på på dessa metoder i hoverMouse //
    public void inHover(Button button);

    public void outHover(Button button);

    public void inHoverSize(Button button, int ID, ListView list, Label fel);

    public void outHoverSize(Button button, ListView list);

    public void ClickEffect(Button button);

    public void handeClickEffect(Button button);

    public void inClick(Button button);

    public void outClick(Button button);

    public void buyWeaponsAdd(ListView list, int index, Label fel);

    public void buyArmorsAdd(ListView list, int index, Label fel);

    public void ifArmorOrWeapon(int ID, ListView list, Label fel);

}
