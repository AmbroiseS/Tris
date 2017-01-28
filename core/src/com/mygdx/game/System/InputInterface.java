package com.mygdx.game.System;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Game.MoveCheck;
import com.mygdx.game.Game.PiecePosition;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Pieces.Tetronimoes;

/**
 * Created by Sikanla on 15/01/2017.
 */

public abstract class InputInterface extends MoveCheck {

    long timeLeft, timeRight, timeDown;
    int repeatTimeMillis, SQSIZE;

    public PiecePosition piecePosition;

    public int x,y=0;

    public InputInterface() {
        repeatTimeMillis= GameScreen.REPEATTIMEMILLIS;
        SQSIZE=GameScreen.SQUARESIZE;

    }

    public void refreshInput() {
        inputDown();
        inputRight();
        inputLeft();
        rotateCW();
    }

    private void inputLeft() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && TimeUtils.millis() - timeLeft > repeatTimeMillis) {
            if (isLeftPossible(piecePosition.getPiecePosition())) {
                piecePosition.updateLeft();
                x -= SQSIZE;
                timeLeft = TimeUtils.millis();
            }

        }
    }

    private void inputRight() {

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && TimeUtils.millis() - timeRight > repeatTimeMillis)

        {
            if (isRightPossible(piecePosition.getPiecePosition())) {
                piecePosition.updateRight();
                x += SQSIZE;
                timeRight = TimeUtils.millis();
            }
        }
    }

    private void inputDown() {

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && TimeUtils.millis() - timeDown > repeatTimeMillis)

        {
            if (isDownPossible(piecePosition.getPiecePosition())) {
                piecePosition.updateDown();
                y -= SQSIZE;
                timeDown = TimeUtils.millis();
            }

        }

    }
     public void rotateCW(){

     }
}
