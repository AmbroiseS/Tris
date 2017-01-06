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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Pieces.I_Piece;
import com.mygdx.game.Pieces.O_Piece;
import com.mygdx.game.Pieces.T_Piece;
import com.mygdx.game.Pieces.Tetronimoes;

import java.util.Arrays;

public class Tris extends ApplicationAdapter {
    public static SpriteBatch batch;
    private Texture j;
    private Texture backgroundTexture;
    public static Texture unit_texture;
    private Texture unit_square_texture;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Rectangle jfalling;
    private ShapeRenderer renderer;


    private Tetronimoes currentPiece;


    //global settings
    public static final int REPEATTIMEMILLIS = 90;

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

        j = new Texture("j_piece.jpg");
        unit_square_texture = new Texture("unit_square.png");
        unit_texture = new Texture("unit.png");
        jfalling = new Rectangle();

        //to draw the matrix
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);
        linesVert = new Array<Vector2>();
        linesHori = new Array<Vector2>();

        //new random piece
        randomPiece();

        fallj();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    long o = 0;

    private void testrandom() {
        if (Gdx.input.isKeyPressed(Input.Keys.X) && TimeUtils.millis() - o > REPEATTIMEMILLIS) {
            randomPiece();
            o = TimeUtils.millis();
        }

    }

    private void randomPiece() {
        int nextpiece = (int) (Math.random() * 3);
        switch (nextpiece) {
            case 0:
                currentPiece = new T_Piece();
                break;
            case 1:
                currentPiece = new I_Piece();
                break;
            case 2:
                currentPiece = new O_Piece();
                break;
        }


    }

    @Override
    public void render() {
        //super.render();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();

        Gdx.graphics.setTitle("" + Gdx.graphics.getFramesPerSecond());
        batch.draw(backgroundTexture, 0, 0, WIDTH, HEIGHT, 0, 0, uRight, vTop);

        currentPiece.drawPosition();
        renderMatrix();

        batch.draw(j, jfalling.x, jfalling.y, 64, 64);

        batch.end();


        if (jfalling.y <= 0)
            fallj();
        jfalling.y -= 150 * Gdx.graphics.getDeltaTime();
        setupMatrixlines();
        testrandom();
        dropAndSave();
        clearLines();
        currentPiece.updateInput();
    }


    @Override
    public void dispose() {
        batch.dispose();
        j.dispose();
        renderer.dispose();

    }

    private void fallj() {
        jfalling.x = (float) (0.75 * WIDTH);
        jfalling.y = HEIGHT;
    }

    public Array<Vector2> linesHori;
    public Array<Vector2> linesVert;

    private void setupMatrixlines() {

        renderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i != 11; i++) {
            linesVert.add(new Vector2(LEFT_M + i * SQUARESIZE, BOTTOM_M));
            linesVert.add(new Vector2(LEFT_M + i * SQUARESIZE, BOTTOM_M + 20 * SQUARESIZE));
            renderer.line(linesVert.get(2 * i), linesVert.get(2 * i + 1));
        }
        for (int i = 0; i != 21; i++) {
            linesHori.add(new Vector2(LEFT_M, BOTTOM_M + i * SQUARESIZE));
            linesHori.add(new Vector2(LEFT_M + 10 * SQUARESIZE, 50 + i * SQUARESIZE));
            renderer.line(linesHori.get(i * 2), linesHori.get(2 * i + 1));

        }
        renderer.end();

    }

    private int[][] temp = new int[2][4];
    private int[][] matrix = new int[20][10];

    private void clearLines(){
        for (int i=0;i!=20;i++){
            int sum=0;
            for(int j=0;j!=10;j++){
                sum+=matrix[i][j];
            }
            if (sum==10){
                for(int j=0;j!=10;j++){
                    matrix[i][j]=0;
                }

            }

        }
    }

    private void renderMatrix() {
        for (int j = 0; j != 20; j++) {
            for (int i = 0; i != 10; i++) {
                if (matrix[j][i] == 1)
                    batch.draw(unit_square_texture, SQUARESIZE * i + LEFT_M, SQUARESIZE * (19 - j) + BOTTOM_M, SQUARESIZE, SQUARESIZE);
            }
        }
    }

    private void dropAndSave() {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && TimeUtils.millis() - o > REPEATTIMEMILLIS) {
            temp = currentPiece.getPiecePosition();

            for (int i = 0; i != 2; i++) {
                for (int j = 0; j != 4; ) {
                    //matrix[y,x]=1-->
                    matrix[19 - (((temp[i][j + 1]) - BOTTOM_M) / SQUARESIZE)][(((temp[i][j]) - LEFT_M) / SQUARESIZE)] = 1;
                    j += 2;
                }
            }
            System.out.println(Arrays.deepToString(matrix));

            randomPiece();
            o = TimeUtils.millis();
        }

    }

}
