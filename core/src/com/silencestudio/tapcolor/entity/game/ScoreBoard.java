package com.silencestudio.tapcolor.entity.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import com.silencestudio.tapcolor.entity.game.controller.GameplayController;
import com.silencestudio.tapcolor.entity.game.controller.ScoreBoardObserver;
import com.silencestudio.tapcolor.entity.gui.Text;
import com.silencestudio.tapcolor.entity.gui.game.ScoreChangeIndicator;
import com.silencestudio.tapcolor.screen.MyScreen;

public class ScoreBoard extends Group implements Score {

    private int player1Score, player2Score, player1lives, player2lives;

    private TextureRegion score;
    private Text player1ScoreText, player2ScoreText, timeText;
    private float offset;

    private float timer = 1f;
    private int seconds, targetSeconds = -1;

    private ScoreBoardObserver scoreBoardObserver;
    private GameplayController gameplayController;

    public ScoreBoard(float offset, GameplayController gameplayController) {
        this.offset = offset;
        this.gameplayController = gameplayController;
        this.scoreBoardObserver = new ScoreBoardObserver(this, this.gameplayController);

        score = new TextureRegion(new Texture(Gdx.files.internal("scoreTip.png")));
        score.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        player1Score = 0;
        player2Score = 0;

        player1lives = gameplayController.getTarget().lives;
        player2lives = gameplayController.getTarget().lives;

        targetSeconds = gameplayController.getTarget().time;

        Color textColor = new Color();
        textColor.a = 1;
        textColor.r = 2.3f / 255f;
        textColor.g = 13.5f / 255f;
        textColor.b = 28.7f / 255f;

        player1ScoreText = new Text.TextBuilder()
                .withText(player1Score + "")
                .atPosition(-this.offset, -64)
                .alignBy(Align.center)
                .withFont(Text.generateFont(Gdx.files.internal("fonts/Ikaros.ttf"), MyScreen.scaleFont(Text.LARGE), false), textColor)
                .build();

        player2ScoreText = new Text.TextBuilder()
                .withText(player2Score + "")
                .atPosition(this.offset, 64)
                .alignBy(Align.center)
                .withFont(Text.generateFont(Gdx.files.internal("fonts/Ikaros.ttf"), MyScreen.scaleFont(Text.LARGE), true), textColor)
                .build();

        addActor(player1ScoreText);
        addActor(player2ScoreText);

        if (gameplayController.getTarget().time != -1) {
            timeText = new Text.TextBuilder()
                    .withText(seconds + "")
                    .atPosition(0, 32)
                    .alignBy(Align.center)
                    .withFont(Text.generateFont(Gdx.files.internal("fonts/Ikaros.ttf"), MyScreen.scaleFont(Text.EXTRA_LARGE), false), Color.WHITE)
                    .build();
            addActor(timeText);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (timer <= 0) {
            timer = 1;
            seconds++;

            scoreBoardObserver.trigger();
        }
        timer -= Gdx.graphics.getDeltaTime();

        if (gameplayController.getTarget().score != -1) {
            player1ScoreText.setValue(player1Score + "");
            player2ScoreText.setValue(player2Score + "");
        }
        if (gameplayController.getTarget().lives != -1) {
            player1ScoreText.setValue(player1lives + "");
            player2ScoreText.setValue(player2lives + "");
        }

        super.draw(batch, parentAlpha);
        batch.draw(score, getX() - this.offset - score.getTexture().getWidth() / 2, getY() - offset / 2f);
        batch.draw(score, getX() + this.offset - score.getTexture().getWidth() / 2, getY() + offset / 2f - score.getTexture().getHeight(),
                score.getTexture().getWidth() / 2,
                score.getTexture().getHeight() / 2,
                score.getTexture().getWidth(),
                score.getTexture().getHeight(),
                1f,
                1f,
                180
        );

        if (timeText != null) {
            if (targetSeconds - seconds >= 0) timeText.setValue((targetSeconds - seconds) + "");
            timeText.draw(batch, parentAlpha);
        }
    }

    @Override
    public int getPlayer1Score() {
        return player1Score;
    }

    @Override
    public int getPlayer2Score() {
        return player2Score;
    }

    @Override
    public int getScoreDifference() {
        return Math.abs(player1Score - player2Score);
    }

    @Override
    public int getTimeElapsed() {
        return seconds;
    }

    @Override
    public int getPlayer1Lives() {
        return player1lives;
    }

    @Override
    public int getPlayer2Lives() {
        return player2lives;
    }

    @Override
    public void handleMatch(int player) {
        Gdx.app.log("Score Board", "Player " + player + " has a match!");
        if (gameplayController.getTarget().lives == -1) {
            switch (player) {
                case 1:
                    setPlayer1Score(player1Score + 1);
                    addActor(
                            new ScoreChangeIndicator(false, false, Color.GREEN, -this.offset, 32)
                    );
                    break;
                case 2:
                    setPlayer2Score(player2Score + 1);
                    addActor(
                            new ScoreChangeIndicator(true, false, Color.GREEN, this.offset, -32)
                    );
                    break;
            }
        }
    }

    @Override
    public void handleError(int player) {
        Gdx.app.log("Score Board", "Player " + player + " has a mismatch!");
        if (gameplayController.getTarget().lives == -1) {
            switch (player) {
                case 1:
                    if (getPlayer1Score() > 0) {
                        addActor(
                                new ScoreChangeIndicator(false, true, Color.RED, -this.offset, 32)
                        );
                        setPlayer1Score(player1Score - 1);
                    }


                    break;
                case 2:
                    if (getPlayer2Score() > 0) {
                        addActor(
                                new ScoreChangeIndicator(true, true, Color.RED, this.offset, -32)
                        );
                        setPlayer2Score(player2Score - 1);
                    }

                    break;
            }
        }
        else {
            removePlayerLife(player);
        }
    }

    private void removePlayerLife(int player) {
        if (player == 1 && player1lives > 0) player1lives--;
        if (player == 2 && player2lives > 0) player2lives--;

    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
        scoreBoardObserver.trigger();
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
        scoreBoardObserver.trigger();
    }
}
