package com.silencestudio.tapcolor.entity.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.silencestudio.tapcolor.entity.game.ColorRectangle;
import com.silencestudio.tapcolor.entity.game.PickableColors;
import com.silencestudio.tapcolor.entity.gui.Button;

public abstract class PlayerController extends Group implements Controller {

    protected Color[] colors = PickableColors.colors;
    protected ColorRectangle colorRectangle;

    public PlayerController(float x, float y, ColorRectangle colorRectangle) {
        this.colorRectangle = colorRectangle;
        setPosition(x, y);

        createButtons();
    }

    public abstract void createButtons();

    @Override
    public abstract void pickColor(Color color);


    protected PickRectangle createPickRectangle(Color color, float x, float y) {
        return new PickRectangle(color, x, y);
    }

    private class PickRectangle extends Button {

        private Color color;

        public PickRectangle(final Color color, float x, float y) {
            super(new Texture(Gdx.files.internal("White.png")), new Texture(Gdx.files.internal("Gray.png")), x, y);
            this.color = color;
        }

        @Override
        public void act() {
            Controller controller = (Controller) getParent();
            controller.pickColor(color);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            getSprite().setColor(color);
            getSprite().setScale(getScaleX(), getScaleY());
            super.draw(batch, parentAlpha);
        }

        @Override
        protected void touchDown() {
            act();
            addAction(Actions.scaleTo(1.3f, 1.3f, 0.1f));
            super.touchDown();
        }

        @Override
        protected void touchUp() {
            addAction(Actions.scaleTo(1f, 1f, 0.1f));
        }
    }
}
