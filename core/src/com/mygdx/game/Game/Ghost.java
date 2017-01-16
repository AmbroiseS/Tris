package com.mygdx.game.Game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameScreen;
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
    private int [][] temp;

    public Ghost() {
        this.SQUARESIZE = GameScreen.SQUARESIZE;
        this.LEFT_M = GameScreen.LEFT_M;
        this.BOTTOM_M = GameScreen.BOTTOM_M;
        ghostUnitTexture = new Texture("ghost.png");
        temp=new int[2][4];
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

    private int[][] calculatePiecePlacement(int[][] xy1234) {
        matrix= Matrix.matrix;

        //shallow copy to prevent reference
        for (int i=0;i!=2;i++){
            for (int j=0;j!=4;j++){
                temp[i][j] = xy1234[i][j];
            }
        }


        int a, b, c, d;
        int e, f, g, h;
        boolean dropFully = false;

        //get the x
        a = (temp[0][0] - LEFT_M) / SQUARESIZE;
        b = (temp[0][2] - LEFT_M) / SQUARESIZE;
        c = (temp[1][0] - LEFT_M) / SQUARESIZE;
        d = (temp[1][2] - LEFT_M) / SQUARESIZE;

        //get the y
        e = 19 - ((temp[0][1] - BOTTOM_M) / SQUARESIZE);
        f = 19 - ((temp[0][3] - BOTTOM_M) / SQUARESIZE);
        g = 19 - ((temp[1][1] - BOTTOM_M) / SQUARESIZE);
        h = 19 - ((temp[1][3] - BOTTOM_M) / SQUARESIZE);
        int max = GameScreen.max(e, f, g, h);

        int k = 0;

        for (int y = max; y != 20; y++) {
            if (a < 10 && b < 10 && c < 10 && d < 10) {
                if (a >= 0 && b >= 0 && c >= 0 && d >= 0) {
                    if (e < 20 && f < 20 && g < 20 && h < 20) {

                        if (matrix[e + k][a] == 1 || matrix[f + k][b] == 1 || matrix[g + k][c] == 1 || matrix[h + k][d] == 1) {

                            temp[0][1] -= (k - 1) * SQUARESIZE;
                            temp[0][3] -= (k - 1) * SQUARESIZE;
                            temp[1][1] -= (k - 1) * SQUARESIZE;
                            temp[1][3] -= (k - 1) * SQUARESIZE;


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
            temp[0][1] -= (19 - max) * SQUARESIZE;
            temp[0][3] -= (19 - max) * SQUARESIZE;
            temp[1][1] -= (19 - max) * SQUARESIZE;
            temp[1][3] -= (19 - max) * SQUARESIZE;
        }
        return temp;

    }

    public void dispose() {
        ghostUnitTexture.dispose();
    }
}
