package com.silencestudio.tapcolor.entity.game.controller;

import com.badlogic.gdx.graphics.Color;
import com.silencestudio.tapcolor.entity.game.ColorRectangle;

public class Player1Controller extends PlayerController {

    public Player1Controller(float x, float y, ColorRectangle colorRectangle) {
        super(x, y, colorRectangle);
    }

    @Override
    public void createButtons() {
        addActor(
                createPickRectangle(colors[0], -264, 0)
        );
        addActor(
                createPickRectangle(colors[1], 0, 0)
        );
        addActor(
                createPickRectangle(colors[2], 264, 0)
        );
    }

    @Override
    public void pickColor(Color color) {
        colorRectangle.pick(color, 1);
    }
}
