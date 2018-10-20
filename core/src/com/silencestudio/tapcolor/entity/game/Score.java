package com.silencestudio.tapcolor.entity.game;

import com.badlogic.gdx.graphics.Color;
import com.silencestudio.tapcolor.utils.Function;

public interface Score {

    int getPlayer1Score();
    int getPlayer2Score();
    int getScoreDifference();
    int getTimeElapsed();
    int getPlayer1Lives();
    int getPlayer2Lives();

    void handleMatch(int player);
    void handleError(int player);
}
