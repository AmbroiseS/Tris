package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Sikanla on 03/01/2017.
 */

public class T_Piece {

    Rectangle t_piece;

    public T_Piece(){
        t_piece=new Rectangle();
        t_piece.x = 50 + 5 * 15;
        t_piece.y = 50 + 19 * 45;


    }
    public void update(){
        MyGdxGame.batch.draw(MyGdxGame.unit_texture,t_piece.x,t_piece.y,15,45);
        MyGdxGame.batch.draw(MyGdxGame.unit_texture,t_piece.x-15,t_piece.y-45,15,45);
        MyGdxGame.batch.draw(MyGdxGame.unit_texture,t_piece.x,t_piece.y-45,15,45);
        MyGdxGame.batch.draw(MyGdxGame.unit_texture,t_piece.x+15,t_piece.y-45,15,45);
    }

    long timeLeft = 0;
    long timeRight = 0;
    long repeatTimeMillis = 90;

    public void inputUpdate(){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && TimeUtils.millis() - timeLeft > repeatTimeMillis) {
            if (t_piece.x > 65) {
                t_piece.x -= 15;
                timeLeft = TimeUtils.millis();
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && TimeUtils.millis() - timeRight > repeatTimeMillis) {
            if (t_piece.x < 50-15 + 9 * 15)
                t_piece.x += 15;
            timeRight = TimeUtils.millis();
        }

    }
}
