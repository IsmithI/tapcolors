package com.silencestudio.tapcolor.entity.gui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.silencestudio.tapcolor.entity.gui.Button;

public abstract class HomeButton extends Button {

    public HomeButton(float x, float y) {
        super(new Texture(Gdx.files.internal("buttons/buttonHomeUp.png")), new Texture(Gdx.files.internal("buttons/buttonHomeUp.png")), x, y);
    }
}
