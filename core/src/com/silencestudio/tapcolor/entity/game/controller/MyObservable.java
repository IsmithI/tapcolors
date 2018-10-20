package com.silencestudio.tapcolor.entity.game.controller;

import java.util.Observer;

public interface MyObservable {
    void trigger();
    void addObserver(Observer observer);
}
