package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Sikanla on 12/01/2017.
 */

public class MainMenuScreen implements Screen {
    private Tris tris;
    private OrthographicCamera camera;

    public  MainMenuScreen(Tris tris) {
        this.tris=tris;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 400);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        tris.batch.setProjectionMatrix(camera.combined);

        tris.batch.begin();
        tris.font.draw(tris.batch, "Welcome to Tris! ", 100, 350);
        tris.font.draw(tris.batch, "Tap anywhere to begin!", 100, 300);
        tris.batch.end();

        if (Gdx.input.isTouched()) {
            tris.setScreen(new GameScreen(tris));
            dispose();
        }
    }




    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
