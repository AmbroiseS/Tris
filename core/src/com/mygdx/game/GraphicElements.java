package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Sikanla on 10/01/2017.
 */

public class GraphicElements {
    private int BOTTOM_M, SQUARESIZE, LEFT_M;
    private SpriteBatch batch;
    private BitmapFont font;

    public GraphicElements(SpriteBatch batch) {
        this.batch=batch;
        int ratio=Tris.RATIO;
        BOTTOM_M=Tris.BOTTOM_M;
        SQUARESIZE=Tris.SQUARESIZE;
        LEFT_M=Tris.LEFT_M;
        font = new BitmapFont();

    }

    public void draw(){
        drawTexts();
    }

    private void drawTexts() {
        //Todo better scaled text!
        font.getData().setScale((float) SQUARESIZE/19,(float) SQUARESIZE/19);
        font.draw(batch,"Hold",SQUARESIZE+SQUARESIZE/2,BOTTOM_M+15*SQUARESIZE+SQUARESIZE/2);
        font.getData().setScale((float) SQUARESIZE/16,(float) SQUARESIZE/16);
        font.draw(batch,"Line Race",LEFT_M+3*SQUARESIZE,BOTTOM_M+21*SQUARESIZE+SQUARESIZE/2);

    }
    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
