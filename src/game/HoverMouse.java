/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Mattias, Jonathan, Johan, Fredrik, Mohini
 */
public class HoverMouse {

    static void inHover(Button button) {
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                button.blendModeProperty().setValue(BlendMode.HARD_LIGHT);
            }
        });
    }

    static void outHover(Button button) {
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                button.blendModeProperty().setValue(BlendMode.SRC_OVER);
            }
        });
    }

    static void inHoverSize(Button button) {
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                button.setScaleX(2);
                button.setScaleY(2);
            }
        });
    }

    static void outHoverSize(Button button) {
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                button.setScaleX(1);
                button.setScaleY(1);
            }
        });

    }

    static void inClick(Button button) {
        button.blendModeProperty().setValue(BlendMode.HARD_LIGHT);
    }

    static void outClick(Button button) {
        button.blendModeProperty().setValue(BlendMode.SRC_OVER);
    }
}
