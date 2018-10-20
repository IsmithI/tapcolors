package com.silencestudio.tapcolor.utils;

import java.util.HashMap;

public class Translator {

    private static Translator instance = null;

    public static Translator getInstance() {
        if (instance == null) {
            instance = new Translator();
        }
        return instance;
    }

    public Translator() {
        init();
    }

    private HashMap<Keys, String> english = new HashMap<Keys, String>();

    private void init() {
        english.put(Keys.PLAY, "Play");
        english.put(Keys.SINGLE, "Solo");
        english.put(Keys.TWO_PLAYERS, "2 Players");
        english.put(Keys.DIFFICULTY, "Difficulty");
        english.put(Keys.COMPUTER, "Computer");
        english.put(Keys.TIME, "Time attack");
        english.put(Keys.LIVES, "Lives");
    }

    public String translate(Keys key) {
        if (getLocale().containsKey(key)) return english.get(key);
        else return "Key '" + key + "' is not found!";
    }

    public enum Keys {
        PLAY, SINGLE, TWO_PLAYERS, COMPUTER, DIFFICULTY, TIME, LIVES
    }

    public HashMap<Keys, String> getLocale() {
        return english;
    }
}
