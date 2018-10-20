package com.silencestudio.tapcolor.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.silencestudio.tapcolor.GameMain;
import com.silencestudio.tapcolor.entity.Logo;

public class SplashScreen extends MyScreen {

    private float duration, fadeIn, fadeOut;
    private float timer;
    private Logo logo;

    public SplashScreen(GameMain gameMain) {
        super(gameMain);
        timer = 0;
    }

    @Override
    public void initialize() {
        duration = 1f;
        fadeIn = 0.5f;
        fadeOut = 0.5f;
        logo = new Logo(getScreenWidth() / 2, getScreenHeight() / 2);
        logo.addAction(
                Logo.getIntroAction(fadeIn, duration, fadeOut)
        );
        guiStage.addActor(logo);
    }

    @Override
    public void render(float delta) {
        if (timer >= getTotalDuration()) {
            getGameMain().startMenu();
            return;
        }
        else  {
            super.render(delta);
        }
        timer += delta;
    }

    private float getTotalDuration() {
        return fadeIn + duration + fadeOut + 2.5f;
    }
}
