package com.mygdx.game.Pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Sikanla on 04/01/2017.
 */

public class I_Piece extends Tetronimoes {
    public I_Piece() {
        square = new Rectangle();
        x = LEFT_M + 5 * SQSIZE;
        y = BOTTOM_M + 19 * SQSIZE;
    }

    @Override
    public void updateInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && TimeUtils.millis() - timeLeft > repeatTimeMillis) {
            if (isLeftPossible(piecePosition.getPiecePosition())) {
                piecePosition.updateLeft();
                x -= SQSIZE;
                timeLeft = TimeUtils.millis();
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && TimeUtils.millis() - timeRight > repeatTimeMillis) {
            if (isRightPossible(piecePosition.getPiecePosition())) {
                piecePosition.updateRight();
                x += SQSIZE;
                timeRight = TimeUtils.millis();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (isRotationPossible(getAdequateRotation(pieceRotation == 3 ? 0 : 1)) ){
                if (pieceRotation == 1) {
                    pieceRotation = 0;
                } else {
                    pieceRotation += 1;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && TimeUtils.millis() - timeDown > repeatTimeMillis) {
            if (isDownPossible(piecePosition.getPiecePosition())) {
                piecePosition.updateDown();
                y -= SQSIZE;
                timeDown = TimeUtils.millis();
            }

        }
    }

    @Override
    public int[][] rotation0() {
        xy1234test = xy1234;
        xy1234test[0][0] = x;
        xy1234test[0][1] = y;
        xy1234test[0][2] = x - SQSIZE;
        xy1234test[0][3] = y;
        xy1234test[1][0] = x - 2 * SQSIZE;
        xy1234test[1][1] = y;
        xy1234test[1][2] = x + SQSIZE;
        xy1234test[1][3] = y;
        return xy1234test;
    }

    @Override
    public int[][] rotation1() {
        xy1234test = xy1234;
        xy1234test[0][0] = x;
        xy1234test[0][1] = y;
        xy1234test[0][2] = x;
        xy1234test[0][3] = y -3* SQSIZE;
        xy1234test[1][0] = x;
        xy1234test[1][1] = y - SQSIZE;
        xy1234test[1][2] = x;
        xy1234test[1][3] = y - 2 * SQSIZE;
        return xy1234test;
    }

    @Override
    public int[][] rotation2() {
        return new int[0][];
    }

    @Override
    public int[][] rotation3() {
        return new int[0][];
    }
}
