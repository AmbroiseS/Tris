package com.mygdx.game.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Tris;

/**
 * Created by Sikanla on 07/01/2017.
 */

public class Matrix {

    public static int[][] matrix = new int[20][10];
    private Array<Vector2> linesHori;
    private Array<Vector2> linesVert;

    private int linesCleared;
    private int BOTTOM_M, SQUARESIZE, LEFT_M;
    private SpriteBatch batch;
    private Texture unit_square_texture;
    private ShapeRenderer renderer;

    private com.mygdx.game.System.Sound sound;

    public Matrix(SpriteBatch batch, ShapeRenderer renderer) {
        this.batch = batch;
        unit_square_texture = new Texture("unit_square.png");
        BOTTOM_M= GameScreen.BOTTOM_M;
        SQUARESIZE=GameScreen.SQUARESIZE;
        LEFT_M=GameScreen.LEFT_M;
        linesVert = new Array<Vector2>();
        linesHori = new Array<Vector2>();
        this.renderer = renderer;
        sound = new com.mygdx.game.System.Sound();
    }


    public Matrix saveInMatrix(int[][] temp) {

        for (int i = 0; i != 2; i++) {
            for (int j = 0; j != 4; ) {
                //matrix[y,x]=1-->
                matrix[19 - (((temp[i][j + 1]) - BOTTOM_M) / SQUARESIZE)][(((temp[i][j]) - LEFT_M) / SQUARESIZE)] = 1;
                j += 2;
            }
        }
        return this;
    }

    public Matrix setupMatrixLines() {

        renderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i != 11; i++) {
            linesVert.add(new Vector2(LEFT_M + i * SQUARESIZE, BOTTOM_M));
            linesVert.add(new Vector2(LEFT_M + i * SQUARESIZE, BOTTOM_M + 20 * SQUARESIZE));
            renderer.line(linesVert.get(2 * i), linesVert.get(2 * i + 1));
        }
        for (int i = 0; i != 21; i++) {
            linesHori.add(new Vector2(LEFT_M, BOTTOM_M + i * SQUARESIZE));
            linesHori.add(new Vector2(LEFT_M + 10 * SQUARESIZE, BOTTOM_M + i * SQUARESIZE));
            renderer.line(linesHori.get(i * 2), linesHori.get(2 * i + 1));
        }
        renderer.end();
        //drawing hold
        for (int i = 0; i != 4; i++) {
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.rect(SQUARESIZE / 2, BOTTOM_M + 16 * SQUARESIZE, 140, 140);
            renderer.rect(SQUARESIZE / 2 + 6, BOTTOM_M + 16 * SQUARESIZE + 6, 128, 128);
            renderer.end();
        }

        return this;

    }

    public Matrix renderMatrix() {
        for (int j = 0; j != 20; j++) {
            for (int i = 0; i != 10; i++) {
                if (matrix[j][i] == 1)
                    batch.draw(unit_square_texture, SQUARESIZE * i + LEFT_M, SQUARESIZE * (19 - j) + BOTTOM_M, SQUARESIZE, SQUARESIZE);
            }
        }
        return this;
    }


    public Matrix clearLines() {
        boolean b = false;
        linesCleared = 0;
        for (int i = 0; i != 20; i++) {
            int sum = 0;
            for (int j = 0; j != 10; j++) {
                sum += matrix[i][j];
            }
            if (sum == 10) {
                b = true;
                linesCleared += 1;
                matrix = removeRowFromMatrix(matrix, i);
                i -= 1;
            }

        }
        if (b)
            sound.playBreakLines();
        return this;
    }

    private int[][] removeRowFromMatrix(int[][] array, int row) {
        int[][] arrayToReturn = new int[20][10];
        System.arraycopy(array, 0, arrayToReturn, 1, row);
        System.arraycopy(array, row + 1, arrayToReturn, row + 1, 20 - (row + 1));
        return arrayToReturn;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void dispose() {
        unit_square_texture.dispose();
        sound.dispose();
    }


}
