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
    public static final int REPEATTIMEMILLIS = 80;

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
        randomPiece = new RandomPiece();
        currentPiece = randomPiece.getRandomPiece();

        //Matrix
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);
        matrix = new Matrix(LEFT_M, BOTTOM_M, SQUARESIZE, batch, renderer);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    private long o = 0;


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
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) ) {
            int[][] temp2 = currentPiece.getPiecePosition();
            int[][] mat = matrix.getMatrix();

            int a, b, c, d;
            int e, f, g, h;
            boolean dropFully = false;

            //get the x
            a = (temp2[0][0] - LEFT_M) / SQUARESIZE;
            b = (temp2[0][2] - LEFT_M) / SQUARESIZE;
            c = (temp2[1][0] - LEFT_M) / SQUARESIZE;
            d = (temp2[1][2] - LEFT_M) / SQUARESIZE;

            //get the y
            e = 19 - ((temp2[0][1] - BOTTOM_M) / SQUARESIZE);
            f = 19 - ((temp2[0][3] - BOTTOM_M) / SQUARESIZE);
            g = 19 - ((temp2[1][1] - BOTTOM_M) / SQUARESIZE);
            h = 19 - ((temp2[1][3] - BOTTOM_M) / SQUARESIZE);
            int max = max(e, f, g, h);

            int k = 0;

            for (int y = max; y != 20; y++) {

                if (mat[e + k][a] == 1 || mat[f + k][b] == 1 || mat[g + k][c] == 1 || mat[h + k][d] == 1) {

                    temp2[0][1] -= (k - 1) * SQUARESIZE;
                    temp2[0][3] -= (k - 1) * SQUARESIZE;
                    temp2[1][1] -= (k - 1) * SQUARESIZE;
                    temp2[1][3] -= (k - 1) * SQUARESIZE;

                    matrix.saveInMatrix(temp2);

                    currentPiece = randomPiece.getRandomPiece();
                    break;

                }
                k++;
                if(y==19)
                    dropFully=true;
            }

            if (dropFully) {
                temp2[0][1] -= (19-max) * SQUARESIZE;
                temp2[0][3] -= (19-max) * SQUARESIZE;
                temp2[1][1] -= (19-max) * SQUARESIZE;
                temp2[1][3] -= (19-max) * SQUARESIZE;
                matrix.saveInMatrix(temp2);
                currentPiece = randomPiece.getRandomPiece();
            }
        }
    }

    public static int max(int a, int b, int c, int d) {

        int tmp1 = a > b ? a : b;
        int tmp2 = c > d ? c : d;

        return tmp1 > tmp2 ? tmp1 : tmp2;
    }

}


