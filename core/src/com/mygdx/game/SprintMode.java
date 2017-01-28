package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Game.Matrix;


/**
 * Created by Sikanla on 12/01/2017.
 */

public class SprintMode {
    private int lineCount = 40;

    private long time = 0;
    private boolean start = false;

    static private BitmapFont font;

    public SprintMode(Tris tris) {
        font = new BitmapFont();
    }


    public void sprintMode() {
        if (lineCount == 0) {
            time = TimeUtils.millis() - time;
        }

    }

    private float deltaTime = 0;
    private String str;
    private int seconds;
    private int minutes;

    private void drawLinesCounter(SpriteBatch batch) {
        if (start) {
            lineCount =40- Matrix.getLinesCleared();
            font.draw(batch, String.valueOf(lineCount), GameScreen.SQUARESIZE+GameScreen.SQUARESIZE/2,
                    GameScreen.HEIGHT - GameScreen.SQUARESIZE);
            font.getData().setScale((float) GameScreen.SQUARESIZE / 13,
                    (float) GameScreen.SQUARESIZE / 13);

        }

    }

    private void drawTime(SpriteBatch batch) {
        if (start) {
            deltaTime += Gdx.graphics.getDeltaTime();

            minutes = (int) deltaTime / 60;
            seconds = (int) deltaTime % 60;
            str = String.format("%02d:%02d", minutes, seconds);
            font.draw(batch, str, GameScreen.WIDTH - 8 * GameScreen.SQUARESIZE,
                    GameScreen.HEIGHT - GameScreen.SQUARESIZE);
            font.getData().setScale((float) GameScreen.SQUARESIZE / 13,
                    (float) GameScreen.SQUARESIZE / 13);
        }
    }

    private void animationEndGame(SpriteBatch batch){
        if (Matrix.getLinesCleared()==5) {
            float finalTime=deltaTime;
            start=false;
            minutes = (int) finalTime / 60;
            seconds = (int) finalTime % 60;
            str = String.format("%02d:%02d", minutes, seconds);
            font.draw(batch, "Your time= "+str, GameScreen.WIDTH - 8 * GameScreen.SQUARESIZE,
                    GameScreen.HEIGHT - 3*GameScreen.SQUARESIZE);
            font.getData().setScale((float) GameScreen.SQUARESIZE / 13,
                    (float) GameScreen.SQUARESIZE / 13);


        }
    }


    public void startAnimation() {
        drawTime(Tris.batch);
        drawLinesCounter(Tris.batch);
        animationEndGame(Tris.batch);

        if (!start) {
            font.draw(Tris.batch, "Enter to Start", GameScreen.WIDTH - 8 * GameScreen.SQUARESIZE, GameScreen.HEIGHT - GameScreen.SQUARESIZE);
            font.getData().setScale((float) GameScreen.SQUARESIZE / 16, (float) GameScreen.SQUARESIZE / 16);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            start = true;
            deltaTime=0;
            Matrix.resetLineCount();


        }
    }


}
