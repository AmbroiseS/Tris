package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Sikanla on 04/01/2017.
 */

public abstract class Tetronimoes {

    int squareSize;
    int pieceRotation = 0;

    Rectangle square;
    long repeatTimeMillis;
    Texture unit_texture;

    long timeDown = 0;
    long timeLeft = 0;
    long timeRight = 0;
    long timeRotate = 0;

    public Tetronimoes(){
        squareSize=MyGdxGame.squareSize;
        repeatTimeMillis=MyGdxGame.repeatTimeMillis;
        unit_texture = new Texture("unit.png");
    }

    protected void update() {
    }

    protected void updateInput(){

    }
}
