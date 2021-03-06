package com.mygdx.game.Pieces;

/**
 * Created by Sikanla on 06/01/2017.
 */

public class O_Piece extends Tetronimoes {

    public O_Piece() {
        x = LEFT_M + 4 * SQSIZE;
        y = BOTTOM_M + 19 * SQSIZE;
    }

    @Override
    public int[][] initiatePiece() {
        x = LEFT_M + 4 * SQSIZE;
        y = BOTTOM_M + 19 * SQSIZE;
        xy1234[0][0] = x;
        xy1234[0][1] = y;
        xy1234[0][2] = x + SQSIZE;
        xy1234[0][3] = y;
        xy1234[1][0] = x ;
        xy1234[1][1] = y-SQSIZE;
        xy1234[1][2] = x + SQSIZE;
        xy1234[1][3] = y-SQSIZE;
        return xy1234;
    }



    @Override
    public int[][] rotation0() {
        xy1234 = piecePosition.getPiecePosition();
        xy1234[0][0] = x;
        xy1234[0][1] = y;
        xy1234[0][2] = x + SQSIZE;
        xy1234[0][3] = y;
        xy1234[1][0] = x ;
        xy1234[1][1] = y-SQSIZE;
        xy1234[1][2] = x + SQSIZE;
        xy1234[1][3] = y-SQSIZE;
        return xy1234;
    }

    @Override
    public int[][] rotation1() {
        return rotation0();
    }

    @Override
    public int[][] rotation2() {
        return rotation0();
    }

    @Override
    public int[][] rotation3() {
        return rotation0();
    }
}
