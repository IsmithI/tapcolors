package com.silencestudio.tapcolor.entity.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RadioGroup extends Group {

    public void addButton(RadioButton radioButton) {
        addActor(radioButton);
    }

    @Override
    public void addActor(Actor actor) {
        if (!(actor instanceof RadioButton))
            throw new NotImplementedException();
        else super.addActor(actor);
    }

    public void resetButtons() {
        for (Actor actor : getChildren()) {
            if (actor instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) actor;
                radioButton.setChecked(false);
            }
        }
    }

    public void selectButton(RadioButton radioButton) {
        resetButtons();
        radioButton.setChecked(true);
    }
}
