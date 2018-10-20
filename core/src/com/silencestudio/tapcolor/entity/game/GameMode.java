package com.silencestudio.tapcolor.entity.game;

public class GameMode {

    public int score = -1;
    public int time = -1;
    public int scoreDifference = -1;
    public int lives = -1;

    public static GameMode time(int time) {
        GameMode gameMode = new GameMode();
        gameMode.time = time;

        return gameMode;
    }
}
