package com.silencestudio.tapcolor.entity.gui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.silencestudio.tapcolor.entity.gui.Button;

public abstract class RestartButton extends Button {

    public RestartButton(float x, float y) {
        super(new Texture(Gdx.files.internal("buttons/buttonRestartUp.png")), new Texture(Gdx.files.internal("buttons/buttonRestartUp.png")), x, y);
    }
}
