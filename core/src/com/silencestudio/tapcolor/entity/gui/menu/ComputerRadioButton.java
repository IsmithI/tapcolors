package com.silencestudio.tapcolor.entity.gui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;
import com.silencestudio.tapcolor.entity.game.controller.MyObservable;
import com.silencestudio.tapcolor.entity.gui.RadioButton;
import com.silencestudio.tapcolor.entity.gui.Text;
import com.silencestudio.tapcolor.screen.MyScreen;
import com.silencestudio.tapcolor.utils.Translator;

import java.util.Observer;

public class ComputerRadioButton extends RadioButton implements MyObservable {

    private ButtonObservable buttonObservable;

    public ComputerRadioButton(float x, float y) {
        super(new Texture(Gdx.files.internal("K-off.png")), new Texture(Gdx.files.internal("K-on.png")), x, y);

        addText(
                new Text.TextBuilder()
                        .alignBy(Align.center)
                        .withFont(Text.generateFont(Gdx.files.internal("fonts/Ikaros.ttf"), MyScreen.scaleFont(Text.SMALL), false), Color.BLACK)
                        .withText(Translator.getInstance().translate(Translator.Keys.COMPUTER))
                        .build()
        );

        getText().setPosition(getX() + getTextureTouchUp().getWidth() / 2, getY() + 304);
    }

    @Override
    public void act() {

    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        trigger();
    }

    @Override
    public void trigger() {
        if (buttonObservable == null) buttonObservable = new ButtonObservable(this);
        buttonObservable.trigger();
    }

    @Override
    public void addObserver(Observer observer) {
        if (buttonObservable == null) buttonObservable = new ButtonObservable(this);
        buttonObservable.addObserver(observer);
    }
}
