package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tris extends Game {
    public static SpriteBatch batch;
    public BitmapFont font;


    @Override
    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font=new BitmapFont();
        this.setScreen(new MainMenuScreen(this));

    }


    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
    }
}


