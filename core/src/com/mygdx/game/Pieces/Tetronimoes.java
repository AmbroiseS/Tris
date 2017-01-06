package com.mygdx.game.Pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Tris;

/**
 * Created by Sikanla on 04/01/2017.
 */

public abstract class Tetronimoes {

    int SQSIZE;
    int pieceRotation = 0;

    Rectangle square;
    long repeatTimeMillis;
    Texture unit_texture;

    int[][] xy1234 = new int[2][4];
    int[][] xy1234test = new int[2][4];

    int x, y, x1, y1, x2, y2, x3, y3, x4, y4;

    int LEFT_M, BOTTOM_M, RIGHT_M;

    long timeDown = 0;
    long timeLeft = 0;
    long timeRight = 0;
    long timeRotate = 0;

    public Tetronimoes() {
        this.SQSIZE = Tris.SQUARESIZE;
        repeatTimeMillis = Tris.REPEATTIMEMILLIS;
        unit_texture = Tris.unit_texture;
        LEFT_M = Tris.LEFT_M;
        RIGHT_M = Tris.RIGHT_M - SQSIZE;
        BOTTOM_M = Tris.BOTTOM_M;
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
        x1 = xy1234[0][0];
        y1 = xy1234[0][1];
        x2 = xy1234[0][2];
        y2 = xy1234[0][3];
        x3 = xy1234[1][0];
        y3 = xy1234[1][1];
        x4 = xy1234[1][2];
        y4 = xy1234[1][3];

        Tris.batch.draw(unit_texture, x1, y1, SQSIZE, SQSIZE);
        Tris.batch.draw(unit_texture, x2, y2, SQSIZE, SQSIZE);
        Tris.batch.draw(unit_texture, x3, y3, SQSIZE, SQSIZE);
        Tris.batch.draw(unit_texture, x4, y4, SQSIZE, SQSIZE);
    }

    public abstract void updateInput();


    public boolean isLeftPossible() {
        return xy1234test[0][0] != LEFT_M &&
                xy1234test[0][2] != LEFT_M &&
                xy1234test[1][0] != LEFT_M &&
                xy1234test[1][2] != LEFT_M;
    }

    public boolean isRightPossible() {
        return xy1234test[0][0] < RIGHT_M &&
                xy1234test[0][2] < RIGHT_M &&
                xy1234test[1][0] < RIGHT_M &&
                xy1234test[1][2] < RIGHT_M;
    }

    public boolean isDownPossible(){
        return xy1234test[0][1] > BOTTOM_M &&
                xy1234test[0][3] > BOTTOM_M &&
                xy1234test[1][1] > BOTTOM_M &&
                xy1234test[1][3] > BOTTOM_M;
    }
    public boolean isRotationPossible(int nextPositionNumber){
        if(nextPositionNumber==0)
            xy1234test=rotation0();
        if(nextPositionNumber==1)
            xy1234test=rotation1();
        if(nextPositionNumber==2)
            xy1234test=rotation2();
        if(nextPositionNumber==3)
            xy1234test=rotation3();

        return xy1234test[0][1] >= BOTTOM_M &&
                xy1234test[0][3] >= BOTTOM_M &&
                xy1234test[1][1] >= BOTTOM_M &&
                xy1234test[1][3] >= BOTTOM_M &&

                xy1234test[0][0] <= RIGHT_M &&
                xy1234test[0][2] <= RIGHT_M &&
                xy1234test[1][0] <= RIGHT_M &&
                xy1234test[1][2] <= RIGHT_M &&

                xy1234test[0][0] >= LEFT_M &&
                xy1234test[0][2] >=LEFT_M &&
                xy1234test[1][0] >=LEFT_M &&
                xy1234test[1][2] >= LEFT_M;

    }

    public abstract int[][] rotation0();

    public abstract int[][] rotation1();

    public abstract int[][] rotation2();

    public abstract int[][] rotation3();

    public int[][] getPiecePosition(){
        return xy1234;
    }


}
