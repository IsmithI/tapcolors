package com.silencestudio.tapcolor.entity.gui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.silencestudio.tapcolor.entity.gui.Button;
import com.silencestudio.tapcolor.entity.gui.Text;
import com.silencestudio.tapcolor.screen.MyScreen;
import com.silencestudio.tapcolor.utils.Translator;

public abstract class StartButton extends Button {

    public StartButton(float x, float y) {
        super(new Texture(Gdx.files.internal("buttons/start.png")), new Texture(Gdx.files.internal("buttons/start.png")), x, y);

        addText(
                new Text.TextBuilder()
                        .withText(Translator.getInstance().translate(Translator.Keys.PLAY))
                        .alignBy(Align.center)
                        .withFont(Text.generateFont(Gdx.files.internal("fonts/Ikaros.ttf"), MyScreen.scaleFont(Text.LARGE), false), Color.WHITE)
                        .build()
        );

        getText().setRotation(60f);
    }

    @Override
    protected void touchDown() {
        super.touchDown();
        addAction(Actions.scaleTo(1.3f, 1.3f, 0.1f));
    }

    @Override
    protected void touchUp() {
        super.touchUp();
        addAction(Actions.scaleTo(1f, 1f, 0.1f));
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        getSprite().setScale(getScaleX(), getScaleY());
        super.draw(batch, parentAlpha);
    }
}
