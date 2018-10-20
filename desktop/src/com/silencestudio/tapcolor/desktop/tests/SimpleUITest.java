package com.silencestudio.tapcolor.desktop.tests;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.silencestudio.tapcolor.tests.UISimpleTest;

public class SimpleUITest {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new UISimpleTest(), config);
    }
}
