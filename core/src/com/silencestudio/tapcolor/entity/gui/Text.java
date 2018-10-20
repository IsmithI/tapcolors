package com.silencestudio.tapcolor.entity.gui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by smith on 14.05.17.
 */

public class Text extends Actor {

    public static final int
            EXTRA_LARGE = 96,
            LARGE       = 64,
            NORMAL      = 48,
            SMALL       = 32,
            VERY_SMALL  = 18;

    private BitmapFont font;
    private String value;
    private int textAlign;

    protected Text(BitmapFont font, String value, Color color, float x, float y) {
        setPosition(x, y);
        this.font = font;
        this.value = value;
        font.setColor(color);
        textAlign = Align.center;

    }

    protected Text(TextBuilder builder) {
        setPosition(builder.x, builder.y);
        font = builder.font;
        value = builder.value;
        textAlign = builder.textAlign;
//        if (builder.color != null)
        font.setColor(builder.color);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, getColor().a);

        font.draw(batch, value, getX(), getY(), 0, textAlign, false);
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(int textAlign) {
        this.textAlign = textAlign;
    }

    public static class TextBuilder {

        private BitmapFont font;
        private String value;
        private float x, y;
        private int textAlign;
        private Color color;

        public TextBuilder withFont(BitmapFont font, Color color) {
            this.font = font;
            this.color = color;
            return this;
        }

        public TextBuilder withText(String value) {
            this.value = value;
            return this;
        }

        public TextBuilder atPosition(float x, float y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public TextBuilder alignBy(int textAlign) {
            this.textAlign = textAlign;
            return this;
        }

        public Text build() {
            return new Text(this);
        }

    }

    //Статический генератор шрифтов
    public static BitmapFont generateFont(FileHandle font, int size, boolean flipped) {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(font);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = size;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;

        BitmapFont bitmapFont = generator.generateFont(parameter);
        generator.dispose();

        if (flipped) {
            bitmapFont.getData().setScale(-1, -1);
        }

        return bitmapFont;
    }
}