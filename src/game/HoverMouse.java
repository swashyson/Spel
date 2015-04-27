/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;

/**
 *
 * @author gul_h_000
 */
public class HoverMouse {
    
    
    static void inHover(Button button){
        
        button.blendModeProperty().setValue(BlendMode.HARD_LIGHT);
        
    }
    static void outHover(Button button){
        
        button.blendModeProperty().setValue(BlendMode.SRC_OVER);
    
    }
}
