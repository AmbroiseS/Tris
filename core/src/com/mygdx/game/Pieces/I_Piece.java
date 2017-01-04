package com.mygdx.game.Pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Tris;

/**
 * Created by Sikanla on 04/01/2017.
 */

public class I_Piece extends Tetronimoes {
    public I_Piece() {
        square = new Rectangle();
        square.x = 50 + 5 * squareSize;
        square.y = 50 + 19 * squareSize;
    }

    @Override
    public void updateRotation() {
        switch (pieceRotation) {
            case (0):
                Tris.batch.draw(unit_texture, square.x, square.y, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x - squareSize, square.y, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x - 2 * squareSize, square.y, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x + squareSize, square.y, squareSize, squareSize);
                break;

            case (1):
                Tris.batch.draw(unit_texture, square.x, square.y, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x, square.y - squareSize, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x, square.y - 2 * squareSize, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x, square.y + squareSize, squareSize, squareSize);
                break;
        }
    }


    @Override
    public void updateInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && TimeUtils.millis() - timeLeft > repeatTimeMillis) {
            if (square.x > 50 + squareSize) {
                square.x -= squareSize;
                timeLeft = TimeUtils.millis();
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && TimeUtils.millis() - timeRight > repeatTimeMillis) {
            if (square.x < 50 - squareSize + 9 * squareSize) {
                square.x += squareSize;
                timeRight = TimeUtils.millis();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && TimeUtils.millis() - timeRotate > repeatTimeMillis) {
            if (pieceRotation == 1) {
                pieceRotation = 0;
                timeRotate = TimeUtils.millis();
            } else {
                pieceRotation += 1;
                timeRotate = TimeUtils.millis();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && TimeUtils.millis() - timeDown > repeatTimeMillis) {
            if (square.y > 50 + squareSize) {
                square.y -= squareSize;
                timeDown = TimeUtils.millis();
            }

        }


    }
}
