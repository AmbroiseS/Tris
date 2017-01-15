
package com.mygdx.game.Game;

import com.mygdx.game.GameScreen;

/**
 * Created by Sikanla on 13/01/2017.
 */

public class PiecePosition {
    private int SQSIZE;
    private int[][] piecePosition = new int[2][4];

    public PiecePosition(int[][] xy1234) {
        SQSIZE = GameScreen.SQUARESIZE;
        piecePosition = xy1234;


    }

    public int[][] getPiecePosition() {
        return piecePosition;
    }

    public void setPiecePosition(int[][] piecePosition) {
        this.piecePosition = piecePosition;

    }

    public void updateRight() {
        piecePosition[0][0] += SQSIZE;
        piecePosition[0][2] += SQSIZE;
        piecePosition[1][0] += SQSIZE;
        piecePosition[1][2] += SQSIZE;
    }

    public void updateLeft() {
        piecePosition[0][0] -= SQSIZE;
        piecePosition[0][2] -= SQSIZE;
        piecePosition[1][0] -= SQSIZE;
        piecePosition[1][2] -= SQSIZE;
    }

    public void updateDown() {
        piecePosition[0][1] -= SQSIZE;
        piecePosition[0][3] -= SQSIZE;
        piecePosition[1][1] -= SQSIZE;
        piecePosition[1][3] -= SQSIZE;
    }

}


