package com.mygdx.game.Game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Tris;

/**
 * Created by Sikanla on 09/01/2017.
 */

public class Ghost {

    private int[][] matrix;
    private int SQUARESIZE;
    private int LEFT_M;
    private int BOTTOM_M;
    private Texture ghostUnitTexture;

    public Ghost() {
        this.SQUARESIZE = Tris.SQUARESIZE;
        this.LEFT_M = Tris.LEFT_M;
        this.BOTTOM_M = Tris.BOTTOM_M;
        ghostUnitTexture = new Texture("ghost.png");
    }


    public void drawGhost(int[][] piecePosition) {
        draw(calculatePiecePlacement(piecePosition));
    }

    private void draw(int[][] ghost) {
        Tris.batch.draw(ghostUnitTexture, ghost[0][0], ghost[0][1], SQUARESIZE, SQUARESIZE);
        Tris.batch.draw(ghostUnitTexture, ghost[0][2], ghost[0][3], SQUARESIZE, SQUARESIZE);
        Tris.batch.draw(ghostUnitTexture, ghost[1][0], ghost[1][1], SQUARESIZE, SQUARESIZE);
        Tris.batch.draw(ghostUnitTexture, ghost[1][2], ghost[1][3], SQUARESIZE, SQUARESIZE);
    }

    private int[][] calculatePiecePlacement(int[][] piecePosition) {
        matrix= Matrix.matrix;
        int[][] temp2 = piecePosition;


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
        int max = Tris.max(e, f, g, h);

        int k = 0;

        for (int y = max; y != 20; y++) {
            if (a < 10 && b < 10 && c < 10 && d < 10) {
                if (a >= 0 && b >= 0 && c >= 0 && d >= 0) {
                    if (e < 20 && f < 20 && g < 20 && h < 20) {

                        if (matrix[e + k][a] == 1 || matrix[f + k][b] == 1 || matrix[g + k][c] == 1 || matrix[h + k][d] == 1) {

                            temp2[0][1] -= (k - 1) * SQUARESIZE;
                            temp2[0][3] -= (k - 1) * SQUARESIZE;
                            temp2[1][1] -= (k - 1) * SQUARESIZE;
                            temp2[1][3] -= (k - 1) * SQUARESIZE;


                            break;

                        }
                        k++;
                        if (y == 19)
                            dropFully = true;
                    }
                }
            }
        }

        if (dropFully) {
            temp2[0][1] -= (19 - max) * SQUARESIZE;
            temp2[0][3] -= (19 - max) * SQUARESIZE;
            temp2[1][1] -= (19 - max) * SQUARESIZE;
            temp2[1][3] -= (19 - max) * SQUARESIZE;
        }
        return temp2;

    }

    public void dispose() {
        ghostUnitTexture.dispose();
    }
}
