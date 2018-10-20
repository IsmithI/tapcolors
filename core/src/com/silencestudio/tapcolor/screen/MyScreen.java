package com.silencestudio.tapcolor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.silencestudio.tapcolor.GameMain;

import java.util.Comparator;

public abstract class MyScreen implements Screen {

    private final float worldWidth = 1200;
    private final GameMain gameMain;

    protected SpriteBatch batch;
    protected SpriteBatch guiBatch;

    protected OrthographicCamera camera;

    protected ExtendViewport viewport;
    protected FillViewport guiViewport;

    protected Stage gameStage, guiStage;

    protected InputMultiplexer inputMultiplexer;

    public MyScreen(GameMain gameMain) {
        this.gameMain = gameMain;

        float ratio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        float worldHeight = worldWidth * ratio;

        this.camera = new OrthographicCamera(worldWidth, worldHeight);

        this.viewport = new ExtendViewport(worldWidth, worldHeight, camera);
        this.guiViewport = new FillViewport(worldWidth, worldHeight);

        batch = new SpriteBatch();

        this.guiBatch = new SpriteBatch();
        guiBatch.setProjectionMatrix(camera.combined);

        gameStage = new Stage(viewport, batch);
        guiStage = new Stage(guiViewport, guiBatch);

        initialize();
    }

    public abstract void initialize();

    @Override
    public void show() {
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(guiStage);
        inputMultiplexer.addProcessor(gameStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

        camera.update();

        gameStage.getActors().sort(new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                return o1.getZIndex() - o2.getZIndex();
            }
        });

        gameStage.act(delta);
        guiStage.act(delta);

        gameStage.draw();
        guiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        guiViewport.update(width, height);
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(guiStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void resume() {
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(guiStage);
        inputMultiplexer.addProcessor(gameStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        guiBatch.dispose();

        gameStage.dispose();
        guiStage.dispose();
    }

    public float getScreenWidth() {
        return camera.viewportWidth;
    }

    public float getScreenHeight() {
        return camera.viewportHeight;
    }

    public GameMain getGameMain() {
        return gameMain;
    }

    public static int scaleFont(int sizeInPixels) {
        float base = Math.max(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return (int) (sizeInPixels * base / 1000f);
    }
}
