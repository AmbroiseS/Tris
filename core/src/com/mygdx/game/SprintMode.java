package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game.Matrix;


/**
 * Created by Sikanla on 12/01/2017.
 */

public class SprintMode {
    private int lineCount = 40;

    private boolean start = false;
    private BitmapFont font;
    private SprintModeInterface sprintModeInterface;

    public interface SprintModeInterface {
        void newGame();
        void gameOver();
    }

    public SprintMode(Tris tris) {
        font = new BitmapFont();
    }

    public void registerListener(SprintModeInterface listener) {
        sprintModeInterface = listener;
    }


    private float deltaTime = 0;
    private String str;
    private int seconds;
    private int minutes;

    private void drawLinesCounter(SpriteBatch batch) {
        if (start) {
            lineCount = 40 - Matrix.getLinesCleared();
            font.getData().setScale((float) GameScreen.SQUARESIZE / 16,
                    (float) GameScreen.SQUARESIZE / 16);

            font.draw(batch, String.valueOf(lineCount), GameScreen.SQUARESIZE + GameScreen.SQUARESIZE / 2,
                    GameScreen.HEIGHT - GameScreen.SQUARESIZE);

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
            font.getData().setScale((float) GameScreen.SQUARESIZE / 18,
                    (float) GameScreen.SQUARESIZE / 18);
        }
    }

    private void goalReached(SpriteBatch batch) {
        if (Matrix.getLinesCleared() >= 4) {
            float finalTime = deltaTime;
            start = false;
            minutes = (int) finalTime / 60;
            seconds = (int) finalTime % 60;
            str = String.format("%02d:%02d", minutes, seconds);

            sprintModeInterface.gameOver();

            font.draw(batch, "Your time= " + str, GameScreen.WIDTH - 8 * GameScreen.SQUARESIZE,
                    GameScreen.HEIGHT - 3 * GameScreen.SQUARESIZE);
            font.getData().setScale((float) GameScreen.SQUARESIZE / 18,
                    (float) GameScreen.SQUARESIZE / 18);


        }
    }


    public void start() {
        drawTime(Tris.batch);
        drawLinesCounter(Tris.batch);
        goalReached(Tris.batch);

        if (start) {
            font.getData().setScale((float) GameScreen.SQUARESIZE / 18, (float) GameScreen.SQUARESIZE / 18);
            font.draw(Tris.batch, "Enter to Restart", GameScreen.WIDTH - 8 * GameScreen.SQUARESIZE, GameScreen.SQUARESIZE);
        }

        if (!start) {
            font.getData().setScale((float) GameScreen.SQUARESIZE / 18, (float) GameScreen.SQUARESIZE / 18);
            font.draw(Tris.batch, "Enter to Start", GameScreen.WIDTH - 8 * GameScreen.SQUARESIZE, GameScreen.HEIGHT - GameScreen.SQUARESIZE);

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && !start) {
            System.out.print("enter");
            sprintModeInterface.newGame();
            start = true;
            deltaTime = 0;
            Matrix.resetLineCount();


        }
    }


}
