package com.silencestudio.tapcolor.entity.gui.menu.controller;

import com.badlogic.gdx.Gdx;
import com.silencestudio.tapcolor.entity.game.GameMode;
import com.silencestudio.tapcolor.entity.gui.RadioButton;
import com.silencestudio.tapcolor.entity.gui.menu.ButtonObservable;
import com.silencestudio.tapcolor.screen.MenuScreen;

import java.util.Observable;
import java.util.Observer;

public class GameModeController implements Observer {

    private MenuScreen menuScreen;

    public GameModeController(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    @Override
    public void update(Observable o, Object arg) {
        RadioButton radioButton = (RadioButton) ((ButtonObservable) o).getButton();

        Gdx.app.log("GameModeController", radioButton.isChecked() + "");

        menuScreen.toggleDifficulty(!radioButton.isChecked());
    }

    public void startGame(Mode mode, Goal goal) {
        GameMode gameMode = new GameMode();
        switch (goal) {
            case TIME:
                gameMode.time = 30;
                break;
            case LIVES:
                gameMode.lives = 3;
                break;
        }
        switch (mode) {
            case SOLO:
                // Start solo game
                break;
            case COMPUTER:
                // Start game against computer
                break;
            case TWO_PLAYERS:
                menuScreen.startGame(gameMode);
                break;
        }
        Gdx.app.debug("GameModeController", mode.toString() + "|" + goal.toString());
    }

    public enum Mode {
        SOLO, TWO_PLAYERS, COMPUTER
    }

    public enum Goal {
        TIME, LIVES
    }
}
