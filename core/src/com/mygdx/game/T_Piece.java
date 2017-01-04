package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Sikanla on 03/01/2017.
 */

public class T_Piece {

    private Rectangle t_piece;
    private int pieceRotation = 0;
    private int squareSize;

    public T_Piece() {
        squareSize=MyGdxGame.squareSize;
        t_piece = new Rectangle();
        t_piece.x = 50 + 5 * squareSize;
        t_piece.y = 50 + 19 * squareSize;



    }


    public void update() {
        switch (pieceRotation) {
            //each case for a rotation

            case (0):

                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x, t_piece.y, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x - squareSize, t_piece.y - squareSize, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x, t_piece.y - squareSize, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x + squareSize, t_piece.y - squareSize, squareSize, squareSize);
                break;

            case (1):

                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x, t_piece.y, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x, t_piece.y - squareSize * 2, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x, t_piece.y - squareSize, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x + squareSize, t_piece.y - squareSize, squareSize, squareSize);
                break;


            case (2):

                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x - squareSize, t_piece.y - squareSize, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x, t_piece.y - squareSize * 2, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x, t_piece.y - squareSize, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x + squareSize, t_piece.y - squareSize, squareSize, squareSize);
                break;

            case (3):

                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x - squareSize, t_piece.y - squareSize, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x, t_piece.y - squareSize * 2, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x, t_piece.y - squareSize, squareSize, squareSize);
                MyGdxGame.batch.draw(MyGdxGame.unit_texture, t_piece.x, t_piece.y, squareSize, squareSize);
                break;

        }
    }

    long timeDown = 0;
    long timeLeft = 0;
    long timeRight = 0;
    long timeRotate = 0;
    long repeatTimeMillis = 90;

    public void inputUpdate() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && TimeUtils.millis() - timeLeft > repeatTimeMillis) {
            if (t_piece.x > 50+squareSize) {
                t_piece.x -= squareSize;
                timeLeft = TimeUtils.millis();
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && TimeUtils.millis() - timeRight > repeatTimeMillis) {
            if (t_piece.x < 50 -squareSize + 9 * squareSize) {
                t_piece.x += squareSize;
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
            if (t_piece.y > 50+squareSize ) {
                t_piece.y -= squareSize;
                timeDown = TimeUtils.millis();
            }

        }
    }
}


