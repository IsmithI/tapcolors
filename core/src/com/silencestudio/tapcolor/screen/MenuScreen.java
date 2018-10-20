package com.silencestudio.tapcolor.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.silencestudio.tapcolor.GameMain;
import com.silencestudio.tapcolor.entity.game.GameMode;
import com.silencestudio.tapcolor.entity.gui.RadioButton;
import com.silencestudio.tapcolor.entity.gui.RadioGroup;
import com.silencestudio.tapcolor.entity.gui.Slider;
import com.silencestudio.tapcolor.entity.gui.menu.*;
import com.silencestudio.tapcolor.entity.gui.menu.controller.GameModeController;

public class MenuScreen extends MyScreen {

    private Group top, bottom;
    private StartButton startButton;
    private GameModeController gameModeController;

    private Group gameModesGroup;
    private Group hideGroup;

    public MenuScreen(GameMain gameMain) {
        super(gameMain);
    }

    @Override
    public void initialize() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        gameModeController = new GameModeController(this);

        top = new Group();
        top.setPosition(getScreenWidth() / 2, getScreenHeight() * 2);


        gameStage.addActor(top);

        bottom = new Group();
        bottom.setPosition(getScreenWidth() / 2, -getScreenHeight());

        RadioGroup radioGroup = new RadioGroup();
        radioGroup.setPosition(0, getScreenHeight() / 12f);
        radioGroup.addButton(new SinglePlayerRadioButton(-352, 0));
        radioGroup.addButton(new TwoPlayerRadioButton(0, 0));

        ComputerRadioButton computerRadioButton = new ComputerRadioButton(352, 0);
        computerRadioButton.addObserver(gameModeController);
        radioGroup.addButton(computerRadioButton);

        ((RadioButton) radioGroup.getChildren().get(0)).setChecked(true);

        bottom.addActor(radioGroup);

        Slider difficultySlider = new DifficultySlider(0, -144);
        hideGroup = new Group();
        hideGroup.setPosition(getScreenWidth(), 0);
        hideGroup.addActor(difficultySlider);
        bottom.addActor(hideGroup);

        bottom.addAction(Actions.moveTo(getScreenWidth() / 2, getScreenHeight() / 2, 1f));
        gameStage.addActor(bottom);

        gameModesGroup = new Group();
        gameModesGroup.setPosition(0, -getScreenHeight() / 3f);

        RadioGroup gameModesRadioGroup = new RadioGroup();
        final RadioButton timeRadioButton = new TimeRadioButton(-256f, 0);
        RadioButton livesRadioButton = new LivesRadioButton(256f, 0);

        gameModesRadioGroup.addActor(timeRadioButton);
        gameModesRadioGroup.addActor(livesRadioButton);
        ((RadioButton) gameModesRadioGroup.getChildren().get(0)).setChecked(true);

        gameModesGroup.addActor(gameModesRadioGroup);
        bottom.addActor(gameModesGroup);

        startButton = new StartButton(0, 0) {
            @Override
            public void act() {
                GameModeController.Goal goal;
                if (timeRadioButton.isChecked()) goal = GameModeController.Goal.TIME;
                else goal = GameModeController.Goal.LIVES;

                Gdx.app.debug("MenuScreen", "Pressed start");
                gameModeController.startGame(GameModeController.Mode.TWO_PLAYERS, goal);
            }
        };

        top.addActor(startButton);
        top.addAction(Actions.moveTo(getScreenWidth() / 2, getScreenHeight() - startButton.getSprite().getHeight(), 1f));
    }

    public void hideElements() {
        top.addAction(Actions.moveTo(getScreenWidth() / 2, getScreenHeight() * 2, 0.7f));
        bottom.addAction(Actions.moveTo(getScreenWidth() / 2, -getScreenHeight(), 0.7f));
    }

    public void toggleDifficulty(boolean difficultyIsHidden) {
        if (difficultyIsHidden) {
            hideGroup.addAction(Actions.moveTo(getScreenWidth(), 0, 0.3f));
        }
        else {
            hideGroup.addAction(Actions.moveTo(0, 0, 0.3f));
        }
    }

    public void startGame(GameMode gameMode) {
        hideElements();
        getGameMain().startGame(gameMode);
    }

}
