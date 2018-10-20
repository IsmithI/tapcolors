package com.silencestudio.tapcolor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.silencestudio.tapcolor.GameMain;
import com.silencestudio.tapcolor.entity.game.ColorRectangle;
import com.silencestudio.tapcolor.entity.game.GameMode;
import com.silencestudio.tapcolor.entity.game.GameScreen;
import com.silencestudio.tapcolor.entity.game.ScoreBoard;
import com.silencestudio.tapcolor.entity.game.controller.GameplayController;
import com.silencestudio.tapcolor.entity.game.controller.Player1Controller;
import com.silencestudio.tapcolor.entity.game.controller.Player2Controller;
import com.silencestudio.tapcolor.entity.game.controller.PlayerController;
import com.silencestudio.tapcolor.entity.gui.Button;
import com.silencestudio.tapcolor.entity.gui.Dialog;
import com.silencestudio.tapcolor.entity.gui.Text;
import com.silencestudio.tapcolor.entity.gui.game.HomeButton;
import com.silencestudio.tapcolor.entity.gui.game.RestartButton;

public class GameScreen2Players extends MyScreen implements GameScreen {

    private GameplayController gameplayController;
    private Group gameOverGroup;
    private Text gameOverText;

    public GameScreen2Players(GameMain gameMain, GameMode target) {
        super(gameMain);

        Gdx.app.debug("GameScreen2Players", target.lives + "");

        gameplayController = new GameplayController(
                target,
                new GameMode(),
                this
        );

        addScoreBoard();
    }

    private void addScoreBoard() {
        ScoreBoard scoreBoard = new ScoreBoard(getScreenWidth() / 2 - 128, gameplayController);
        scoreBoard.setPosition(getScreenWidth() / 2, getScreenHeight() / 2);

        ColorRectangle colorRectangle = new ColorRectangle(getScreenWidth() / 2, getScreenHeight() / 2, scoreBoard);

        PlayerController player1Controller = new Player1Controller(getScreenWidth() / 2, getScreenHeight() * 0.1f, colorRectangle);
        PlayerController player2Controller = new Player2Controller(getScreenWidth() / 2, getScreenHeight() * 0.9f, colorRectangle);

        gameStage.addActor(colorRectangle);
        gameStage.addActor(player1Controller);
        gameStage.addActor(player2Controller);
        gameStage.addActor(scoreBoard);
    }

    @Override
    public void initialize() {
        gameOverGroup = new Group();
        gameOverGroup.setPosition(0, -getScreenHeight());
        Dialog dialog = new Dialog();
        Button restart = new RestartButton(-96, 128) {
            @Override
            public void act() {
                restart();
            }
        };
        Button home = new HomeButton(96, 128) {
            @Override
            public void act() {
                back();
            }
        };
        gameOverText = new Text.TextBuilder()
                .withText("")
                .atPosition(0, -128)
                .alignBy(Align.center)
                .withFont(Text.generateFont(Gdx.files.internal("fonts/Ikaros.ttf"), MyScreen.scaleFont(Text.SMALL), false), Color.WHITE)
                .build();

        dialog.addActor(restart);
        dialog.addActor(home);
        dialog.addActor(gameOverText);

        gameOverGroup.addActor(dialog);


        guiStage.addActor(gameOverGroup);
    }

    @Override
    public void restart() {
        getGameMain().startGame(gameplayController.getTarget());
    }

    @Override
    public void gameOver(int player) {
        gameOverText.setValue("#" + player + " has won!");
        gameOverGroup.addAction(Actions.moveTo(getScreenWidth() / 2, getScreenHeight() / 2, 0.3f));
        pause();
        Gdx.app.debug("GameScreen", "Game Over!");
    }

    @Override
    public void back() {
        getGameMain().startMenu();
    }
}
