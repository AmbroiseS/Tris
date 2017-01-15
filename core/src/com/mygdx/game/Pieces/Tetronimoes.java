package com.mygdx.game.Pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Game.MoveCheck;
import com.mygdx.game.Game.PiecePosition;
import com.mygdx.game.GameScreen;
import com.mygdx.game.System.GraphicElements;
import com.mygdx.game.Game.Matrix;
import com.mygdx.game.Tris;

/**
 * Created by Sikanla on 04/01/2017.
 */

public abstract class Tetronimoes extends MoveCheck {

    int SQSIZE;
    int pieceRotation = 0;

    Rectangle square;
    long repeatTimeMillis;

    private Texture unit_texture;
    private GraphicElements graphicElements;
    PiecePosition piecePosition;

    int[][] xy1234 = new int[2][4];
    int[][] xy1234test = new int[2][4];

    int x, y;

    int LEFT_M, BOTTOM_M, RIGHT_M;

    private int[][] mat;

    long timeDown = 0;
    long timeLeft = 0;
    long timeRight = 0;

    public Tetronimoes() {
        this.SQSIZE = GameScreen.SQUARESIZE;
        repeatTimeMillis = GameScreen.REPEATTIMEMILLIS;
        unit_texture = GameScreen.unit_texture;
        LEFT_M = GameScreen.LEFT_M;
        RIGHT_M = GameScreen.RIGHT_M - SQSIZE;
        BOTTOM_M = GameScreen.BOTTOM_M;
        xy1234 = rotation0();
        piecePosition=new PiecePosition(rotation0());
        graphicElements=new GraphicElements();

    }

    public void drawPosition() {
        switch (pieceRotation) {
            //each case for a rotation
            case 0:
                xy1234 = rotation0();
                break;
            case 1:
                xy1234 = rotation1();
                break;
            case 2:
                xy1234 = rotation2();
                break;
            case 3:
                xy1234 = rotation3();
                break;
        }
        graphicElements.drawPiece(unit_texture,piecePosition.getPiecePosition(),SQSIZE);

    }

    private void getCurrentMatrix(){
        mat = Matrix.matrix;
    }

    public abstract void updateInput();



    public abstract int[][] rotation0();

    public abstract int[][] rotation1();

    public abstract int[][] rotation2();

    public abstract int[][] rotation3();

  /*  public void updateRight() {
        xy1234[0][0] += SQSIZE;
        xy1234[0][2] += SQSIZE;
        xy1234[1][0] += SQSIZE;
        xy1234[1][2] += SQSIZE;
    }

    public void updateLeft() {
        xy1234[0][0] -= SQSIZE;
        xy1234[0][2] -= SQSIZE;
        xy1234[1][0] -= SQSIZE;
        xy1234[1][2] -= SQSIZE;
    }

    public void updateDown() {
        xy1234[0][1] -= SQSIZE;
        xy1234[0][3] -= SQSIZE;
        xy1234[1][1] -= SQSIZE;
        xy1234[1][3] -= SQSIZE;
    }
    */

    public int[][] getPiecePosition() {
        return piecePosition.getPiecePosition();
    }

    int[][] getAdequateRotation(int nextPositionNumber) {
        int[][] xy1234test = piecePosition.getPiecePosition();
        if (nextPositionNumber == 0)
            xy1234test = rotation0();

        if (nextPositionNumber == 1)
            xy1234test = rotation1();

        if (nextPositionNumber == 2)
            xy1234test = rotation2();

        if (nextPositionNumber == 3)
            xy1234test = rotation3();

        return xy1234test;
    }


}
