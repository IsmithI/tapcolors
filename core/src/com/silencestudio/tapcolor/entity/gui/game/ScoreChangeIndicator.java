package com.silencestudio.tapcolor.entity.gui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.silencestudio.tapcolor.entity.gui.Text;
import com.silencestudio.tapcolor.screen.MyScreen;

public class ScoreChangeIndicator extends Text {

    public ScoreChangeIndicator(boolean flipped, boolean error, Color color, float x, float y) {
        super(
                Text.generateFont(Gdx.files.internal("fonts/Ikaros.ttf"), MyScreen.scaleFont(Text.NORMAL), flipped),
                error ? "-1" : "+1",
                color,
                x,
                y
        );
        Gdx.app.debug("ScoreChangeIndicator", "Created score change");

        addAction(
                Actions.fadeOut(0.5f)
        );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color fontColor = getFont().getColor();
        getFont().setColor(fontColor.r, fontColor.g, fontColor.b, getColor().a);
        if (getFont().getColor().a == 0) remove();

        super.draw(batch, parentAlpha);
    }
}
