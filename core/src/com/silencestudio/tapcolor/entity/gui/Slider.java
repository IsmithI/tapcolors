package com.silencestudio.tapcolor.entity.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import static com.badlogic.gdx.math.MathUtils.floor;

public class Slider extends Group {

    private Sprite sprite;
    private Actor slider;
    private float canvas = 48;

    public Slider(Sprite sprite, float x, float y) {
        setPosition(x, y);
        setBounds(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());
        this.sprite = sprite;
        this.sprite.setPosition(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2);

        createSlider();

        addListener(new InputListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touch up");
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                calculatePosition(x);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                calculatePosition(x);
            }
        });

        this.sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public Sprite getSprite() {
        return sprite;
    }

    private void calculatePosition(float touchX) {
        int i = 0;
        float startX = canvas;
        float startY = sprite.getHeight() / 2;
        float cellWidth = (sprite.getWidth() - canvas * 2) / 4;
        float dist = touchX - startX;

        int nc = floor(dist / cellWidth);

        float cellTouch = touchX - cellWidth * nc;
        i = nc;

        if (cellTouch >= cellWidth / 2) i++;

        if (i >= 0 && i < 5) {
            slider.addAction(Actions.moveTo(startX + cellWidth * i, startY, 0.1f));
        }
    }

    @Override
    protected void positionChanged() {
        float startX = canvas;

        if (sprite != null) {
            sprite.setPosition(sprite.getWidth() / 2, sprite.getHeight() / 2);
        }
    }

    private void createSlider(int limits) {
        float startX = canvas;
        float startY = sprite.getHeight() / 2;
        slider = new SliderPointer(new Sprite(new Texture(Gdx.files.internal("ZN.png"))), startX, startY);
        addActor(slider);
    }

    private void createSlider() {
        createSlider(5);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch, parentAlpha);
        super.draw(batch, parentAlpha);
    }

    public Actor getSlider() {
        return slider;
    }

    private class SliderPointer extends Actor {

        private Sprite sprite;

        SliderPointer(Sprite sprite, float x, float y) {
            setPosition(x, y);
            this.sprite = sprite;
            this.sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
            this.sprite.setScale(0.5f);
            this.sprite.setPosition(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2);
            this.sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            sprite.setPosition(getX() - sprite.getWidth() / 2, getY() - sprite.getHeight() / 2);
            sprite.draw(batch, parentAlpha);
        }
    }

}
