package com.silencestudio.tapcolor.entity.gui;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.silencestudio.tapcolor.entity.gui.menu.ButtonInterface;


/**
 * Created by ANTON on 5/5/2017.
 */
public abstract class Button extends Group implements ButtonInterface {
    private Texture textureTouchUp;
    private Texture textureTouchDown;

    private Sprite sprite;

    private Text text;

    private boolean checkTouch;

    public Button(Texture textureTouchUp, Texture textureTouchDown, float x, float y) {
        this.textureTouchUp = textureTouchUp;
        this.textureTouchDown = textureTouchDown;

        sprite = new Sprite(this.textureTouchUp);

        setBounds(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());
        addListener(new InputListener() {
            //            Нажимаем на кнопку
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Button.this.touchDown();
                return true;
            }

            //            Уводим в сторону палец/курсор с кнопки
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Button.this.exit();
            }

            //            Отпускаем кнопку
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Button.this.touchUp();
            }
        });

        sprite.setPosition(getX(), getY());
        this.textureTouchUp.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.textureTouchDown.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public Button(final String touchUp, final String touchDown, float x, float y) {
        this.textureTouchUp = new Texture(touchUp);
        this.textureTouchDown = new Texture(touchDown);
        sprite = new Sprite(this.textureTouchUp);

        setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        setPosition(x, y);
        addListener(new InputListener() {
            //            Нажимаем на кнопку
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Button.this.touchDown();
                return true;
            }

            //            Уводим в сторону палец/курсор с кнопки
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Button.this.exit();
            }

            //            Отпускаем кнопку
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Button.this.touchUp();
            }
        });
    }

    public Button(TextureRegion touchUp, TextureRegion touchDown, float x, float y) {

    }

    //Позволяет наложить на кнопку текст
    public void addText(Text text) {
        this.text = text;
        GlyphLayout layout = new GlyphLayout(text.getFont(), text.getValue());
        this.text.setPosition(0, layout.height/2);
    }

    protected void touchUp() {
        if (checkTouch)
            act();
    }

    protected void exit() {
        checkTouch = false;
        sprite.setTexture(textureTouchUp);
    }

    protected void touchDown() {
        checkTouch = true;
        sprite.setTexture(textureTouchDown);
    }

    public abstract void act();


    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.draw(batch, parentAlpha);

        if (text != null)
            text.draw(batch, parentAlpha);
    }

    //    Сеттеры и Геттеры
    public void setTextureTouchUp(String touchUp) {
        textureTouchUp = new Texture(touchUp);
    }

    public void setTextureTouchDown(String touchDown) {
        textureTouchDown = new Texture(touchDown);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Texture getTextureTouchUp() {
        return textureTouchUp;
    }

    public Texture getTextureTouchDown() {
        return textureTouchDown;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
