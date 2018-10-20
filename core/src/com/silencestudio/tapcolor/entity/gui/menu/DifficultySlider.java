package com.silencestudio.tapcolor.entity.gui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.silencestudio.tapcolor.entity.gui.Slider;
import com.silencestudio.tapcolor.entity.gui.Text;
import com.silencestudio.tapcolor.screen.MyScreen;
import com.silencestudio.tapcolor.utils.Translator;

public class DifficultySlider extends Slider {

    public DifficultySlider(float x, float y) {
        super(new Sprite(new Texture(Gdx.files.internal("buttons/slider.png"))), x, y);

        Text difficultyLabel = new Text.TextBuilder()
                .withText(Translator.getInstance().translate(Translator.Keys.DIFFICULTY))
                .withFont(Text.generateFont(Gdx.files.internal("fonts/Ikaros.ttf"), MyScreen.scaleFont(Text.SMALL), false), Color.BLACK)
                .atPosition(getSprite().getWidth() / 2, getSprite().getHeight() * 2)
                .alignBy(Align.center)
                .build();

        addActor(difficultyLabel);
    }
}
