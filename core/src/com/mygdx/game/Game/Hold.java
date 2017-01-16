package com.mygdx.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Pieces.Tetronimoes;
import com.mygdx.game.System.GraphicElements;
import com.mygdx.game.Tris;

/**
 * Created by Sikanla on 10/01/2017.
 */

public class Hold {

    private Tetronimoes holdPiece;
    private Tetronimoes temp;
    private boolean holdHasBeenUsedOnce = false;
    private boolean firstHoldUse = true;
    private int BOTTOM_M, SQUARESIZE, LEFT_M;
    private Texture unit_square_texture;
    private com.mygdx.game.System.GraphicElements graphicElements;


    public Hold() {
        unit_square_texture = new Texture("unit_square.png");
        BOTTOM_M = GameScreen.BOTTOM_M;
        SQUARESIZE = GameScreen.SQUARESIZE;
        LEFT_M = GameScreen.LEFT_M;
        graphicElements = GraphicElements.getInstance();

    }

    public void setHoldHasBeenUsedOnce(boolean b) {
        holdHasBeenUsedOnce = b;
    }


    public void inputHold(Tetronimoes currentPiece, Preview preview) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
            if (!firstHoldUse) {
                //hold can only be used once
                if (!holdHasBeenUsedOnce) {
                    temp = holdPiece;
                    try {
                        holdPiece = currentPiece.getClass().newInstance();
                    } catch (InstantiationException e) {
                        System.out.print(e.toString());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    try {
                        GameScreen.currentPiece=temp.getClass().newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    holdHasBeenUsedOnce = true;
                }
            }

            if (firstHoldUse) {
                try {
                    holdPiece = currentPiece.getClass().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                firstHoldUse = false;
                GameScreen.currentPiece=preview.getNextPiece();

            }
        }
    }

    public void drawHold() {
        if (holdPiece != null) {
            int[][] temp = holdPiece.rotation0();
            temp[0][0] -= 7 * SQUARESIZE;
            temp[0][2] -= 7 * SQUARESIZE;
            temp[1][0] -= 7 * SQUARESIZE;
            temp[1][2] -= 7 * SQUARESIZE;
            temp[0][1] -= SQUARESIZE;
            temp[0][3] -= SQUARESIZE;
            temp[1][1] -= SQUARESIZE;
            temp[1][3] -= SQUARESIZE;
            graphicElements.drawPiece(unit_square_texture, temp, SQUARESIZE / 4 * 3);
        }
    }
}
