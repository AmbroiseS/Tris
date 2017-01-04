package com.mygdx.game.Pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Tris;

/**
 * Created by Sikanla on 03/01/2017.
 */

public class T_Piece extends Tetronimoes {

    public T_Piece() {
        square = new Rectangle();
        square.x = 50 + 5 * squareSize;
        square.y = 50 + 19 * squareSize;
    }


    public void updateRotation() {
        switch (pieceRotation) {
            //each case for a rotation

            case (0):

                Tris.batch.draw(unit_texture, square.x, square.y, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x - squareSize, square.y - squareSize, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x, square.y - squareSize, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x + squareSize, square.y - squareSize, squareSize, squareSize);
                break;

            case (1):

                Tris.batch.draw(unit_texture, square.x, square.y - squareSize * 2, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x, square.y - squareSize, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x + squareSize, square.y - squareSize, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x, square.y, squareSize, squareSize);
                break;


            case (2):

                Tris.batch.draw(unit_texture, square.x - squareSize, square.y - squareSize, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x, square.y - squareSize * 2, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x, square.y - squareSize, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x + squareSize, square.y - squareSize, squareSize, squareSize);
                break;

            case (3):

                Tris.batch.draw(unit_texture, square.x - squareSize, square.y - squareSize, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x, square.y - squareSize * 2, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x, square.y - squareSize, squareSize, squareSize);
                Tris.batch.draw(unit_texture, square.x, square.y, squareSize, squareSize);
                break;

        }
    }

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
            if (pieceRotation == 3) {
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


