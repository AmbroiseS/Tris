package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by Sikanla on 08/01/2017.
 */

public class Sound {

    private com.badlogic.gdx.audio.Sound breaklines;
    private com.badlogic.gdx.audio.Sound dropPiece;


    public Sound() {
        breaklines = Gdx.audio.newSound(Gdx.files.internal("breakLines.wav"));
        dropPiece = Gdx.audio.newSound(Gdx.files.internal("dropPiece.wav"));

    }

    public void playBreakLines() {
        breaklines.play();

    }

    public void playDropPiece() {
        dropPiece.play();
    }
    public void dispose(){
        breaklines.dispose();
        dropPiece.dispose();
    }

}
