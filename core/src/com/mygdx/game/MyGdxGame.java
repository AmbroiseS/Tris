package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter {
    public static SpriteBatch batch;
    Texture j;
    private OrthographicCamera camera;

    public Rectangle jfalling;
    public T_Piece t_piece;
    public Vector3 touchPos;
    public ShapeRenderer renderer;
    private Viewport viewport;

    public  static int  squareSize=40;
    public static int repeatTimeMillis=90;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 540, 960);
        viewport = new FitViewport(540, 960, camera);
        j = new Texture("j_piece.jpg");

        jfalling = new Rectangle();

        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);
        linesVert = new Array<Vector2>();
        linesHori = new Array<Vector2>();

        touchPos = new Vector3();
        fallj();
        t_piece = new T_Piece();


    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        t_piece.update();
        batch.draw(j, jfalling.x, jfalling.y, 64, 64);

        batch.end();

        if (jfalling.y <= 0)
            fallj();

        jfalling.y -= 150 * Gdx.graphics.getDeltaTime();
        setupMatrixlines();
        setupMatrixlines();
        t_piece.updateInput();
    }


    @Override
    public void dispose() {
        batch.dispose();
        j.dispose();
        renderer.dispose();

    }

    private void fallj() {

        jfalling.x = 540 / 2 - 16;
        jfalling.y = 960;
    }

    public Array<Vector2> linesHori;
    public Array<Vector2> linesVert;

    private void setupMatrixlines() {


        renderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i != 11; i++) {
            linesVert.add(new Vector2(50 + i * squareSize, 50));
            linesVert.add(new Vector2(50 + i * squareSize, 50 + 20 * squareSize));
            renderer.line(linesVert.get(2 * i), linesVert.get(2 * i + 1));
        }
        for (int i = 0; i != 21; i++) {
            linesHori.add(new Vector2(50, 50 + i * squareSize));
            linesHori.add(new Vector2(50 + 10 * squareSize, 50 + i * squareSize));
            renderer.line(linesHori.get(i * 2), linesHori.get(2 * i + 1));

        }
        renderer.end();

    }

}
