package com.silencestudio.tapcolor.entity.game;

import com.badlogic.gdx.graphics.Color;

import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.badlogic.gdx.graphics.Color.RED;

public interface PickableColors {

    Color[] colors = {
            new Color(209/255f, 75/255f, 75/255f, 1),    //Red
            new Color(76/255f, 126/255f, 232/255f, 1),    //Blue
            new Color(70/255f, 214/255f, 89/255f, 1)     //Green
    };
}
