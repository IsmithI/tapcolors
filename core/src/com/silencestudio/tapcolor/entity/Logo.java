package com.silencestudio.tapcolor.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Logo extends Actor {

    private Sprite logo;

    public Logo(float x, float y) {
        setPosition(x, y);
        setColor(getColor().r, getColor().g, getColor().b, 0);

        logo = new Sprite(new Texture(Gdx.files.internal("SS-2.png")));
        logo.setPosition(x - logo.getWidth() / 2, y - logo.getHeight() / 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        if (hasActions()) {
            logo.setAlpha(getColor().a);
            logo.draw(batch);
//        }
    }

    public static Action getIntroAction(float fadeIn, float duration, float fadeOut) {
        return Actions.sequence(
                Actions.delay(0.75f),
                Actions.delay(1f, Actions.fadeIn(fadeIn)), Actions.delay(duration, Actions.fadeOut(fadeOut))
        );
    }
}
