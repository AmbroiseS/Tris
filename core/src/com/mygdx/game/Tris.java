package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Pieces.Tetronimoes;

public class Tris extends ApplicationAdapter {
    public static SpriteBatch batch;

    private Texture backgroundTexture;
    public static Texture unit_texture;

    private Viewport viewport;
    private OrthographicCamera camera;
    private ShapeRenderer renderer;

    private Tetronimoes currentPiece;
    private RandomPiece randomPiece;
    private Matrix matrix;

    //global settings
    public static final int REPEATTIMEMILLIS = 100;

    //rendering size
    private final int WIDTH = 960;
    private final int HEIGHT = 960;
    private final int RATIO = WIDTH / HEIGHT;
    private float uRight = 0;
    private float vTop = 0;

    //Matrix position
    public static final int SQUARESIZE = 40;
    public static final int LEFT_M = 50;
    public static final int RIGHT_M = 50 + 10 * SQUARESIZE;
    public static final int BOTTOM_M = 50;


    @Override
    public void create() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("congruent_outline.png");
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        uRight = WIDTH * RATIO / backgroundTexture.getWidth();
        vTop = HEIGHT * RATIO / backgroundTexture.getHeight();

        //set up camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        viewport = new FitViewport(WIDTH, HEIGHT, camera);

        unit_texture = new Texture("unit.png");

        //new random piece
        randomPiece=new RandomPiece();
        currentPiece = randomPiece.getRandomPiece();

        //Matrix
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);
        matrix = new Matrix(LEFT_M, BOTTOM_M, SQUARESIZE, batch,renderer);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    long o = 0;


    @Override
    public void render() {
        //super.render();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        //draw
        batch.begin();
        //get fps
        Gdx.graphics.setTitle("" + Gdx.graphics.getFramesPerSecond());
        //background texture
        batch.draw(backgroundTexture, 0, 0, WIDTH, HEIGHT, 0, 0, uRight, vTop);

        currentPiece.drawPosition();
        matrix.renderMatrix();

        batch.end();


        hardDrop();
        matrix.setupMatrixLines()
                .clearLines();
        currentPiece.updateInput();
    }


    @Override
    public void dispose() {
        batch.dispose();
        renderer.dispose();
        unit_texture.dispose();

    }

    private void hardDrop() {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && TimeUtils.millis() - o > REPEATTIMEMILLIS) {
            int[][] temp = currentPiece.getPiecePosition();

            matrix.save(temp);
            currentPiece = randomPiece.getRandomPiece();
            o = TimeUtils.millis();
        }
    }


}


