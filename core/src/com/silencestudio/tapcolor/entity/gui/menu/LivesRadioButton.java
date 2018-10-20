package com.silencestudio.tapcolor.entity.gui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;
import com.silencestudio.tapcolor.entity.gui.RadioButton;
import com.silencestudio.tapcolor.entity.gui.Text;
import com.silencestudio.tapcolor.screen.MyScreen;
import com.silencestudio.tapcolor.utils.Translator;

public class LivesRadioButton extends RadioButton {

    public LivesRadioButton(float x, float y) {
        super(new Texture(Gdx.files.internal("K-off.png")), new Texture(Gdx.files.internal("K-on.png")), x, y);

        addText(
                new Text.TextBuilder()
                        .alignBy(Align.center)
                        .withFont(Text.generateFont(Gdx.files.internal("fonts/Ikaros.ttf"), MyScreen.scaleFont(Text.SMALL), false), Color.BLACK)
                        .withText(Translator.getInstance().translate(Translator.Keys.LIVES))
                        .build()
        );

        getText().setPosition(getX() + getTextureTouchUp().getWidth() / 2, getY() + 304);
    }

    @Override
    public void act() {

    }
}
