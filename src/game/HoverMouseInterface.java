/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 *
 * @author gul_h_000
 */
public interface HoverMouseInterface {
    
    public void inHover(Button button);
    public void outHover(Button button);
    public void inHoverSize(Button button, int ID, ListView list);
    public void outHoverSize(Button button, ListView list);
    public void ClickEffect(Button button);
    public void handeClickEffect(Button button);
    public void inClick(Button button);
    public void outClick(Button button);
    public void buyWeaponsAdd(ListView list, int index);
    public void buyArmorsAdd(ListView list, int index);
    public void ifArmorOrWeapon(int ID, ListView list);
    
}
