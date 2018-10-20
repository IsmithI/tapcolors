package com.silencestudio.tapcolor.entity.gui.menu;

import com.silencestudio.tapcolor.entity.game.controller.MyObservable;

import java.util.Observable;
import java.util.Observer;

public class ButtonObservable extends Observable implements MyObservable {

   private ButtonInterface button;

    public ButtonObservable(ButtonInterface button) {
        this.button = button;
    }

    public void trigger() {
        setChanged();
        notifyObservers();
    }

    public ButtonInterface getButton() {
        return button;
    }
}
