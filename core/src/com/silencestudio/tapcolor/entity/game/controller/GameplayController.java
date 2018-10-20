package com.silencestudio.tapcolor.entity.game.controller;

import com.badlogic.gdx.Gdx;
import com.silencestudio.tapcolor.entity.game.GameMode;
import com.silencestudio.tapcolor.entity.game.GameScreen;
import com.silencestudio.tapcolor.entity.game.Score;

import java.util.Observable;
import java.util.Observer;

public class GameplayController implements Observer {

    private GameMode target;
    private GameMode start;
    private GameScreen gameScreen;

    public GameplayController(GameMode target, GameMode start, GameScreen gameScreen) {
        this.target = target;
        this.start = start;
        this.gameScreen = gameScreen;

        Gdx.app.debug("GameplayController", "Start " + target.lives + "");
    }

    @Override
    public void update(Observable o, Object arg) {
        Score score = ((ScoreBoardObserver) o).getScoreBoard();
        Gdx.app.debug("GameplayController", "Lives left 1:" + score.getPlayer1Lives() + "| 2: " + score.getPlayer2Lives());

        int winner = isGameOver(score);
        if (winner != -1) {
            gameScreen.gameOver(winner);
        }
    }

    private int isGameOver(Score score) {
        if (target.time != -1) {
            if (score.getTimeElapsed() >= target.time) {
                int player = 0;
                if (score.getPlayer1Score() > score.getPlayer2Score()) player = 1;      //Player 1
                if (score.getPlayer2Score() > score.getPlayer1Score()) player = 2;      //Player 2
                if (score.getPlayer1Score() == score.getPlayer2Score()) player = 0;     //Draw

                return player;
            }
        }
        if (target.lives != -1) {
            if (score.getPlayer1Lives() <= 0) {
                return 2;
            }
            if (score.getPlayer2Lives() <= 0) {
                return 1;
            }
        }
        if (target.score != -1) {
            if (score.getPlayer1Score() >= target.score) {
                return 1;
            }
            if (score.getPlayer2Score() >= target.score) {
                return 2;
            }
        }
//        if (target.scoreDifference != -1) {
//            return score.getScoreDifference() >= target.scoreDifference;
//        }

        return -1;
    }

    public GameMode getTarget() {
        return target;
    }

    public GameMode getStart() {
        return start;
    }
}
