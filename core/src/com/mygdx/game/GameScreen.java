package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Pieces.Tetronimoes;
import com.mygdx.game.System.GraphicElements;

/**
 * Created by Sikanla on 12/01/2017.
 */

public class GameScreen implements Screen {

    private Texture backgroundTexture;
    public static Texture unit_texture;

    private Viewport viewport;
    private OrthographicCamera camera;
    private ShapeRenderer renderer;

    public static Tetronimoes currentPiece;
    private com.mygdx.game.Game.Preview preview;
    private com.mygdx.game.Game.Matrix matrix;
    private com.mygdx.game.System.Sound sound;
    private com.mygdx.game.Game.Ghost ghost;
    private com.mygdx.game.System.GraphicElements graphicElements;
    private com.mygdx.game.Game.Hold hold;
    private SprintMode sprintMode;


    //global settings
    public static final int REPEATTIMEMILLIS = 70;

    //rendering size
    public static final int WIDTH = 960;
    public static final int HEIGHT = 960;
    public static final int RATIO = WIDTH / HEIGHT;
    private float uRight = 0;
    private float vTop = 0;

    //Matrix position
    public static final int SQUARESIZE = 40;
    public static final int LEFT_M = 200;
    public static final int RIGHT_M = LEFT_M + 10 * SQUARESIZE;
    public static final int BOTTOM_M = 40;

    private Tris tris;

    public GameScreen(final Tris tris) {
        this.tris = tris;

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
        preview = new com.mygdx.game.Game.Preview(unit_texture, SQUARESIZE);
        currentPiece = preview.getNextPiece();
        ghost = new com.mygdx.game.Game.Ghost();
        hold = new com.mygdx.game.Game.Hold();

        //Sound
        sound = new com.mygdx.game.System.Sound();

        //Matrix
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);
        matrix = new com.mygdx.game.Game.Matrix(tris.batch, renderer);

        //graphics
        graphicElements = GraphicElements.getInstance();

        //gameMode
        sprintMode=new SprintMode(tris);


    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tris.batch.setProjectionMatrix(camera.combined);
        camera.update();

        //drawInterface
        tris.batch.begin();
        //get fps
        Gdx.graphics.setTitle("" + Gdx.graphics.getFramesPerSecond());
        //background texture
        tris.batch.draw(backgroundTexture, 0, 0, WIDTH, HEIGHT, 0, 0, uRight, vTop);

        preview.displayPreview();

        currentPiece.drawPosition();
        ghost.drawGhost(currentPiece.getPiecePosition());

        matrix.renderMatrix();
        graphicElements.drawInterface();
        hold.drawHold();
        sprintMode.startAnimation();

        tris.batch.end();


        hardDrop();
        hold.inputHold(currentPiece, preview);
        matrix.setupMatrixLines()
                .clearLines();
        currentPiece.refreshInput();
    }


    @Override
    public void dispose() {
        sound.dispose();
        tris.batch.dispose();
        renderer.dispose();
        unit_texture.dispose();
        ghost.dispose();
        matrix.dispose();
        graphicElements.dispose();

    }

    private void hardDrop() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
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
                if (a < 10 && b < 10 && c < 10 && d < 10) {
                    if (a >= 0 && b >= 0 && c >= 0 && d >= 0) {
                        if (e < 20 && f < 20 && g < 20 && h < 20) {

                            if (mat[e + k][a] == 1 || mat[f + k][b] == 1 || mat[g + k][c] == 1 || mat[h + k][d] == 1) {

                                temp2[0][1] -= (k - 1) * SQUARESIZE;
                                temp2[0][3] -= (k - 1) * SQUARESIZE;
                                temp2[1][1] -= (k - 1) * SQUARESIZE;
                                temp2[1][3] -= (k - 1) * SQUARESIZE;

                                matrix.saveInMatrix(temp2);
                                currentPiece = preview.getNextPiece();
                                hold.setHoldHasBeenUsedOnce(false);

                                break;

                            }
                            k++;
                            if (y == 19)
                                dropFully = true;
                        }

                        if (dropFully) {
                            temp2[0][1] -= (19 - max) * SQUARESIZE;
                            temp2[0][3] -= (19 - max) * SQUARESIZE;
                            temp2[1][1] -= (19 - max) * SQUARESIZE;
                            temp2[1][3] -= (19 - max) * SQUARESIZE;
                            matrix.saveInMatrix(temp2);
                            currentPiece = preview.getNextPiece();
                        }
                        hold.setHoldHasBeenUsedOnce(false);
                        sound.playDropPiece();
                    }
                }
            }
        }
    }

    public static int max(int a, int b, int c, int d) {

        int tmp1 = a > b ? a : b;
        int tmp2 = c > d ? c : d;

        return tmp1 > tmp2 ? tmp1 : tmp2;
    }


    @Override
    public void show() {

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

}


