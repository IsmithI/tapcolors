package com.silencestudio.tapcolor.entity.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class RadioButton extends Button {

    private boolean checked = false;


    public RadioButton(Texture textureTouchUp, Texture textureTouchDown, float x, float y) {
        super(textureTouchUp, textureTouchDown, x, y);
    }

    @Override
    protected void touchDown() {
        if (!checked) {
            super.touchDown();
        }
    }

    @Override
    protected void touchUp() {
        if (!checked) {
            if (hasParent()) {
                RadioGroup radioGroup = (RadioGroup) getParent();
                radioGroup.selectButton(this);
            }
            super.touchUp();
        }
    }

    public boolean isChecked() {
        return checked;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (checked) getSprite().setTexture(getTextureTouchDown());
        else getSprite().setTexture(getTextureTouchUp());

        getSprite().draw(batch, parentAlpha);

        if (getText() != null)
            getText().draw(batch, parentAlpha);
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
