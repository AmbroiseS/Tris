package com.mygdx.game.Pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Sikanla on 03/01/2017.
 */

public class T_Piece extends Tetronimoes {

    public T_Piece() {
        square = new Rectangle();
        x = 50 + 5 * SQSIZE;
        y = 50 + 19 * SQSIZE;
    }



    public void updateInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && TimeUtils.millis() - timeLeft > repeatTimeMillis) {
            if (isLeftPossible()) {
                x -= SQSIZE;
                timeLeft = TimeUtils.millis();
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && TimeUtils.millis() - timeRight > repeatTimeMillis) {
            if (isRightPossible()) {
                x += SQSIZE;
                timeRight = TimeUtils.millis();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && TimeUtils.millis() - timeRotate > repeatTimeMillis) {
            if (pieceRotation == 3) {
                pieceRotation = 0;
                timeRotate = TimeUtils.millis();
            } else {
                pieceRotation += 1;
                timeRotate = TimeUtils.millis();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && TimeUtils.millis() - timeDown > repeatTimeMillis) {
            if (true) {
                y -= SQSIZE;
                timeDown = TimeUtils.millis();
            }

        }

    }

    @Override
    public int[][] rotation0() {
        xy1234test=xy1234;
        xy1234test[0][0] = x;
        xy1234test[0][1] = y;
        xy1234test[0][2] = x - SQSIZE;
        xy1234test[0][3] = y - SQSIZE;
        xy1234test[1][0] = x;
        xy1234test[1][1] = y - SQSIZE;
        xy1234test[1][2] = x + SQSIZE;
        xy1234test[1][3] = y - SQSIZE;
        return xy1234test;
    }

    @Override
    public int[][] rotation1() {
        xy1234test=xy1234;
        xy1234test[0][0] = x;
        xy1234test[0][1] = y;
        xy1234test[0][2] = x;
        xy1234test[0][3] = y - 2 * SQSIZE;
        xy1234test[1][0] = x;
        xy1234test[1][1] = y - SQSIZE;
        xy1234test[1][2] = x + SQSIZE;
        xy1234test[1][3] = y - SQSIZE;
        return xy1234test;
    }

    @Override
    public int[][] rotation2() {
        xy1234test=xy1234;
        xy1234test[0][0] = x - SQSIZE;
        xy1234test[0][1] = y - SQSIZE;
        xy1234test[0][2] = x;
        xy1234test[0][3] = y - 2 * SQSIZE;
        xy1234test[1][0] = x;
        xy1234test[1][1] = y - SQSIZE;
        xy1234test[1][2] = x + SQSIZE;
        xy1234test[1][3] = y - SQSIZE;
        return xy1234test;
    }

    @Override
    public int[][] rotation3() {
        xy1234test=xy1234;
        xy1234test[0][0] = x - SQSIZE;
        xy1234test[0][1] = y - SQSIZE;
        xy1234test[0][2] = x;
        xy1234test[0][3] = y - 2 * SQSIZE;
        xy1234test[1][0] = x;
        xy1234test[1][1] = y - SQSIZE;
        xy1234test[1][2] = x;
        xy1234test[1][3] = y;

        return xy1234test;
    }

}

