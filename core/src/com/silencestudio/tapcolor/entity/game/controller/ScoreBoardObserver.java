package com.silencestudio.tapcolor.entity.game.controller;

import com.silencestudio.tapcolor.entity.game.Score;
import com.silencestudio.tapcolor.entity.game.ScoreBoard;

import java.util.Observable;

public class ScoreBoardObserver extends Observable {

    private Score scoreBoard;

    public ScoreBoardObserver(ScoreBoard scoreBoard, GameplayController gameplayController) {
        this.scoreBoard = scoreBoard;
        addObserver(gameplayController);
    }

    public void trigger() {
        setChanged();
        notifyObservers();
    }

    public Score getScoreBoard() {
        return scoreBoard;
    }
}
