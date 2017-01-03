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

public class MyGdxGame extends ApplicationAdapter {
    public static SpriteBatch batch;
    Texture j;
    public static Texture unit_texture;
    private OrthographicCamera camera;
    private Rectangle jpiece;
    private Rectangle unit;
    public Rectangle jfalling;
    public T_Piece t_piece;
    public Vector3 touchPos;
    public ShapeRenderer renderer;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 540, 960);
        j = new Texture("j_piece.jpg");
        unit_texture = new Texture("unit.png");
        setup_unit();

        jpiece = new Rectangle();
        jpiece.x = 540 / 2 - 32 / 2;
        jpiece.y = 20;
        jpiece.height = 32;
        jpiece.width = 32;
        jfalling = new Rectangle();

        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);
        linesVert = new Array<Vector2>();
        linesHori = new Array<Vector2>();

        touchPos = new Vector3();
        fallj();
        t_piece = new T_Piece();


    }

    public float x = 15;
    public float y = 45;

    @Override
    public void render() {
        super.render();
        handle_imput();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        t_piece.update();

        batch.draw(j, jfalling.x, jfalling.y);
        //batch.draw(unit_texture, unit.x, unit.y, 15, 45);
        if (jfalling.y <= 0)
            fallj();
        batch.end();

        if (jpiece.x > 540 - 32)
            jpiece.x = 540 - 32;
        if (jpiece.x < 0)
            jpiece.x = 0;

        jfalling.y -= 200 * Gdx.graphics.getDeltaTime();
        lines();
        t_piece.inputUpdate();
    }

    long timeLeft = 0;
    long timeRight = 0;
    long repeatTimeMillis = 90;

    private void handle_imput() {
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            jfalling.x = touchPos.x - 32 / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && TimeUtils.millis() - timeLeft > repeatTimeMillis) {
            if (unit.x > 50) {
                unit.x -= 15;
                timeLeft = TimeUtils.millis();
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && TimeUtils.millis() - timeRight > repeatTimeMillis) {
            if (unit.x < 50 + 9 * 15)
                unit.x += 15;
            timeRight = TimeUtils.millis();
        }
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
        jfalling.width = 32;
        jfalling.height = 32;


    }

    public Array<Vector2> linesHori;
    public Array<Vector2> linesVert;

    private void lines() {


        renderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i != 11; i++) {
            linesVert.add(new Vector2(50 + i * 15, 50));
            linesVert.add(new Vector2(50 + i * 15, 50 + 15 * 60));
            renderer.line(linesVert.get(2 * i), linesVert.get(2 * i + 1));
        }
        for (int i = 0; i != 21; i++) {
            linesHori.add(new Vector2(50, 50 + i * 45));
            linesHori.add(new Vector2(50 + 10 * 15, 50 + i * 45));
            renderer.line(linesHori.get(i * 2), linesHori.get(2 * i + 1));

        }
        renderer.end();

    }

    private void setup_unit() {
        unit = new Rectangle();
        unit.height = 45;
        unit.width = 15;
        unit.x = 50 + 5 * 15;
        unit.y = 50 + 19 * 45;

    }
}
