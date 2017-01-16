package com.mygdx.game.Pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Game.PiecePosition;
import com.mygdx.game.GameScreen;
import com.mygdx.game.System.GraphicElements;
import com.mygdx.game.System.InputInterface;

/**
 * Created by Sikanla on 04/01/2017.
 */

public abstract class Tetronimoes extends InputInterface {

    int SQSIZE;
    private int pieceRotation = 0;

    private Texture unit_texture;
    private GraphicElements graphicElements;


    int[][] xy1234 = new int[2][4];

    int LEFT_M, BOTTOM_M, RIGHT_M;


    public Tetronimoes() {
        this.SQSIZE = GameScreen.SQUARESIZE;
        unit_texture = GameScreen.unit_texture;
        LEFT_M = GameScreen.LEFT_M;
        RIGHT_M = GameScreen.RIGHT_M - SQSIZE;
        BOTTOM_M = GameScreen.BOTTOM_M;

        piecePosition = new PiecePosition();
        piecePosition.setPiecePosition(initiatePiece());

        graphicElements = GraphicElements.getInstance();

    }


    public void drawPosition() {
        graphicElements.drawPiece(unit_texture, piecePosition.getPiecePosition(), SQSIZE);

    }

    public abstract int[][] initiatePiece();

    public abstract int[][] rotation0();

    public abstract int[][] rotation1();

    public abstract int[][] rotation2();

    public abstract int[][] rotation3();


    public int[][] getPiecePosition() {
        return piecePosition.getPiecePosition();
    }

    int[][] getAdequateRotation(int nextPositionNumber) {
        int[][] xy1234 = piecePosition.getPiecePosition();
        if (nextPositionNumber == 0)
            xy1234 = rotation0();

        if (nextPositionNumber == 1)
            xy1234 = rotation1();

        if (nextPositionNumber == 2)
            xy1234 = rotation2();

        if (nextPositionNumber == 3)
            xy1234 = rotation3();

        return xy1234;
    }

    @Override
    public void rotateCW() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (isRotationPossible(getAdequateRotation(pieceRotation == 3 ? 0 : (pieceRotation + 1)))) {
                pieceRotation = pieceRotation == 3 ? 0 : pieceRotation + 1;

                switch (pieceRotation) {
                    //each case for a rotation
                    case 0:
                        piecePosition.setPiecePosition(rotation0());
                        break;
                    case 1:
                        piecePosition.setPiecePosition(rotation1());
                        break;
                    case 2:
                        piecePosition.setPiecePosition(rotation2());
                        break;
                    case 3:
                        piecePosition.setPiecePosition(rotation3());
                        break;
                }


            }

        }

    }
}
