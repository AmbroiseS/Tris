package com.mygdx.game.Pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Tris;

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
        this.squareSize= Tris.squareSize;
        repeatTimeMillis= Tris.repeatTimeMillis;
        unit_texture = new Texture("unit.png");
    }

    public abstract void updateRotation();

    public abstract void updateInput();
}
