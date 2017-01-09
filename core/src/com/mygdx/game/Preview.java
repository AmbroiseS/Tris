package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Pieces.Tetronimoes;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Sikanla on 08/01/2017.
 */

public class Preview {

    private RandomPiece randomPiece;

    private ArrayList<Tetronimoes> list;
    private Texture texture;
    private int squareSize;
    private Tetronimoes nextPiece;
    private Tetronimoes tempTetro;

    public Preview(Texture texture_unit, int squareSize) {
        this.texture = texture_unit;
        this.squareSize = squareSize;
        list=new ArrayList<Tetronimoes>();
        randomPiece = new RandomPiece();
        for (int i = 0; i != 5; i++) {
            list.add(i, randomPiece.getRandomPiece());
        }


    }

    public Tetronimoes getNextPiece() {
        nextPiece=list.get(0);
        list.remove(0);
        list.add(4,randomPiece.getRandomPiece());


        return nextPiece;
    }


    public void displayPreview() {

        for (int j=1;j!=6;j++) {
            tempTetro=list.get(j-1);
            draw(texture, tempTetro.rotation0(), (squareSize/(j+1)),(j-1)*4*squareSize+squareSize);
        }


    }

    private void draw(Texture unit, int[][] xy, int size,int decay) {
        int x=8*squareSize;
        Tris.batch.draw(unit, xy[0][0]+x, xy[0][1]-decay, size, size);
        Tris.batch.draw(unit, xy[0][2]+x, xy[0][3]-decay, size, size);
        Tris.batch.draw(unit, xy[1][0]+x, xy[1][1]-decay, size, size);
        Tris.batch.draw(unit, xy[1][2]+x, xy[1][3]-decay, size, size);
    }
}
