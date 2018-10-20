package com.silencestudio.tapcolor;

import com.badlogic.gdx.Game;
import com.silencestudio.tapcolor.entity.game.GameMode;
import com.silencestudio.tapcolor.screen.GameScreen2Players;
import com.silencestudio.tapcolor.screen.MenuScreen;
import com.silencestudio.tapcolor.screen.MyScreen;
import com.silencestudio.tapcolor.screen.SplashScreen;

public class GameMain extends Game {

	private MyScreen splashScreen, menuScreen, gameScreen;

	@Override
	public void create () {
		splashScreen = new SplashScreen(this);
		menuScreen = new MenuScreen(this);
		setScreen(splashScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		splashScreen.dispose();
		menuScreen.dispose();
		gameScreen.dispose();
	}

	public void startMenu() {
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	public void startGame(GameMode gameMode) {
		gameScreen = new GameScreen2Players(this, gameMode);
		setScreen(gameScreen);
	}
}
