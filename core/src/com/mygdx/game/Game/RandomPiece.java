package com.mygdx.game.Game;

import com.mygdx.game.Pieces.I_Piece;
import com.mygdx.game.Pieces.J_Piece;
import com.mygdx.game.Pieces.L_Piece;
import com.mygdx.game.Pieces.O_Piece;
import com.mygdx.game.Pieces.S_Piece;
import com.mygdx.game.Pieces.T_Piece;
import com.mygdx.game.Pieces.Tetronimoes;
import com.mygdx.game.Pieces.Z_Piece;

/**
 * Created by Sikanla on 07/01/2017.
 */

public class RandomPiece {
    private Tetronimoes currentPiece;


    public RandomPiece() {
    }


    public Tetronimoes getRandomPiece() {
        int nextpiece = (int) (Math.random() * 7);
        switch (nextpiece) {
            case 0:
                currentPiece = new T_Piece();
                break;
            case 1:
                currentPiece = new I_Piece();
                break;
            case 2:
                currentPiece = new O_Piece();
                break;
            case 3:
                currentPiece = new J_Piece();
                break;
            case 4:
                currentPiece = new L_Piece();
                break;
            case 5:
                currentPiece = new Z_Piece();
                break;
            case 6:
                currentPiece = new S_Piece();
                break;
        }
        return currentPiece;


    }
}
