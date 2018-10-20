package com.silencestudio.tapcolor.entity.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.badlogic.gdx.graphics.Color.GRAY;

public class ColorRectangle extends Actor {

    private Score scoreBoard;

    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("White.png")));
    private Color[] colors = PickableColors.colors;

    private Color current = GRAY;

    private float timeout = 1f;
    private boolean flag = false;

    public ColorRectangle(float x, float y, ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
        setPosition(x, y);
        setBounds(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2, sprite.getWidth(), sprite.getHeight());

        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setScale(2.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (timeout <= 0 && !flag) {
            setRandomColor();
            flag = true;
        }
        if (!flag) timeout -= Gdx.graphics.getDeltaTime();

        sprite.setColor(current);
        sprite.setPosition(getX(), getY());
        sprite.draw(batch, parentAlpha);
    }

    private void setRandomColor() {
        current = colors[MathUtils.random(colors.length-1)];
    }

    public Color getRandomColor() {
        return colors[MathUtils.random(colors.length-1)];
    }

    public boolean hasMatch(Color color) {
        return current.equals(color);
    }

    public void pick(Color color, int player) {
        Gdx.app.log("ColorRectangle.pick()", color + "|" + player);
        if (hasMatch(color)) {
            scoreBoard.handleMatch(player);
            setRandomColor();
        }
        else
            scoreBoard.handleError(player);
    }

}
