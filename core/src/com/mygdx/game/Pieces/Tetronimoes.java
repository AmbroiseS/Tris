package com.mygdx.game.Pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameScreen;
import com.mygdx.game.System.GraphicElements;
import com.mygdx.game.Game.Matrix;
import com.mygdx.game.Tris;

/**
 * Created by Sikanla on 04/01/2017.
 */

public abstract class Tetronimoes {

    int SQSIZE;
    int pieceRotation = 0;

    Rectangle square;
    long repeatTimeMillis;

    private Texture unit_texture;
    private GraphicElements graphicElements;

    int[][] xy1234 = new int[2][4];
    int[][] xy1234test = new int[2][4];

    int x, y;

    int LEFT_M, BOTTOM_M, RIGHT_M;

    private int[][] mat;

    long timeDown = 0;
    long timeLeft = 0;
    long timeRight = 0;

    public Tetronimoes() {
        this.SQSIZE = GameScreen.SQUARESIZE;
        repeatTimeMillis = GameScreen.REPEATTIMEMILLIS;
        unit_texture = GameScreen.unit_texture;
        LEFT_M = GameScreen.LEFT_M;
        RIGHT_M = GameScreen.RIGHT_M - SQSIZE;
        BOTTOM_M = GameScreen.BOTTOM_M;
        xy1234 = rotation0();
        graphicElements=new GraphicElements();

    }

    public void drawPosition() {
        switch (pieceRotation) {
            //each case for a rotation
            case 0:
                xy1234 = rotation0();
                break;
            case 1:
                xy1234 = rotation1();
                break;
            case 2:
                xy1234 = rotation2();
                break;
            case 3:
                xy1234 = rotation3();
                break;
        }
        graphicElements.drawPiece(unit_texture,xy1234,SQSIZE);

    }

    private void getCurrentMatrix(){
        mat = Matrix.matrix;
    }

    public abstract void updateInput();


    public boolean isLeftPossible() {
        getCurrentMatrix();
        int a, b, c, d;
        int e, f, g, h;

        //get the x
        a = (xy1234[0][0] - LEFT_M) / SQSIZE;
        b = (xy1234[0][2] - LEFT_M) / SQSIZE;
        c = (xy1234[1][0] - LEFT_M) / SQSIZE;
        d = (xy1234[1][2] - LEFT_M) / SQSIZE;

        //get the y
        e = 19 - ((xy1234[0][1] - BOTTOM_M) / SQSIZE);
        f = 19 - ((xy1234[0][3] - BOTTOM_M) / SQSIZE);
        g = 19 - ((xy1234[1][1] - BOTTOM_M) / SQSIZE);
        h = 19 - ((xy1234[1][3] - BOTTOM_M) / SQSIZE);

        xy1234test = xy1234;

        //test borders first

        if (xy1234test[0][0] == LEFT_M || xy1234test[0][2] == LEFT_M ||
                xy1234test[1][0] == LEFT_M || xy1234test[1][2] == LEFT_M) {
            return false;
        } else {
            if (a > 9 || b > 9 || c > 9 || d > 9) {
                return false;
            } else {
                if (a < 0 || b < 0 || c < 0 || d < 0) {
                    return false;
                } else {
                    if (e > 20 || f > 20 || g > 20 || h > 20) {
                        return false;
                    } else {


                        //test for other pieces
                        if (mat[e][a - 1] == 1 || mat[f][b - 1] == 1 || mat[g][c - 1] == 1 || mat[h][d - 1] == 1) {
                            return false;
                        }
                        return true;

                    }
                }
            }
        }
    }


    public boolean isRightPossible() {
        getCurrentMatrix();

        int a, b, c, d;
        int e, f, g, h;

        //get the x
        a = (xy1234[0][0] - LEFT_M) / SQSIZE;
        b = (xy1234[0][2] - LEFT_M) / SQSIZE;
        c = (xy1234[1][0] - LEFT_M) / SQSIZE;
        d = (xy1234[1][2] - LEFT_M) / SQSIZE;

        //get the y
        e = 19 - ((xy1234[0][1] - BOTTOM_M) / SQSIZE);
        f = 19 - ((xy1234[0][3] - BOTTOM_M) / SQSIZE);
        g = 19 - ((xy1234[1][1] - BOTTOM_M) / SQSIZE);
        h = 19 - ((xy1234[1][3] - BOTTOM_M) / SQSIZE);

        xy1234test = xy1234;

        //test borders first

        if (xy1234test[0][0] == RIGHT_M || xy1234test[0][2] == RIGHT_M ||
                xy1234test[1][0] == RIGHT_M || xy1234test[1][2] == RIGHT_M) {
            return false;
        } else {
            if (a > 9 || b > 9 || c > 9 || d > 9) {
                return false;
            } else {
                if (a < 0 || b < 0 || c < 0 || d < 0) {
                    return false;
                } else {
                    if (e > 20 || f > 20 || g > 20 || h > 20) {
                        return false;
                    } else {
                        //test for other pieces
                        if (mat[e][a + 1] == 1 || mat[f][b + 1] == 1 || mat[g][c + 1] == 1 || mat[h][d + 1] == 1) {
                            return false;
                        }
                    }

                    return true;
                }
            }
        }
    }

    public boolean isDownPossible() {
        getCurrentMatrix();

        int a, b, c, d;
        int e, f, g, h;

        //get the x
        a = (xy1234[0][0] - LEFT_M) / SQSIZE;
        b = (xy1234[0][2] - LEFT_M) / SQSIZE;
        c = (xy1234[1][0] - LEFT_M) / SQSIZE;
        d = (xy1234[1][2] - LEFT_M) / SQSIZE;

        //get the y
        e = 19 - ((xy1234[0][1] - BOTTOM_M) / SQSIZE);
        f = 19 - ((xy1234[0][3] - BOTTOM_M) / SQSIZE);
        g = 19 - ((xy1234[1][1] - BOTTOM_M) / SQSIZE);
        h = 19 - ((xy1234[1][3] - BOTTOM_M) / SQSIZE);

        xy1234test = xy1234;


        //test borders first

        if (xy1234test[0][1] == BOTTOM_M || xy1234test[0][3] == BOTTOM_M ||
                xy1234test[1][1] == BOTTOM_M || xy1234test[1][3] == BOTTOM_M) {
            return false;
        } else {
            if (a > 9 || b > 9 || c > 9 || d > 9) {
                return false;
            } else {
                if (a < 0 || b < 0 || c < 0 || d < 0) {
                    return false;
                } else {
                    if (e > 20 || f > 20 || g > 20 || h > 20) {
                        return false;
                    } else {
                        //test for other pieces
                        if (mat[e + 1][a] == 1 || mat[f + 1][b] == 1 || mat[g + 1][c] == 1 || mat[h + 1][d] == 1) {
                            return false;
                        }

                        return true;

                    }
                }
            }
        }
    }


    public boolean isRotationPossible(int nextPositionNumber) {
        getCurrentMatrix();

        if (nextPositionNumber == 0)
            xy1234test = rotation0();
        if (nextPositionNumber == 1)
            xy1234test = rotation1();
        if (nextPositionNumber == 2)
            xy1234test = rotation2();
        if (nextPositionNumber == 3)
            xy1234test = rotation3();

        int a, b, c, d;
        int e, f, g, h;

        //get the x
        a = (xy1234test[0][0] - LEFT_M) / SQSIZE;
        b = (xy1234test[0][2] - LEFT_M) / SQSIZE;
        c = (xy1234test[1][0] - LEFT_M) / SQSIZE;
        d = (xy1234test[1][2] - LEFT_M) / SQSIZE;

        //get the y
        e = 19 - ((xy1234test[0][1] - BOTTOM_M) / SQSIZE);
        f = 19 - ((xy1234test[0][3] - BOTTOM_M) / SQSIZE);
        g = 19 - ((xy1234test[1][1] - BOTTOM_M) / SQSIZE);
        h = 19 - ((xy1234test[1][3] - BOTTOM_M) / SQSIZE);

        xy1234test = xy1234;

        if (xy1234test[0][1] < BOTTOM_M &&
                xy1234test[0][3] < BOTTOM_M &&
                xy1234test[1][1] < BOTTOM_M &&
                xy1234test[1][3] < BOTTOM_M &&

                xy1234test[0][2] > RIGHT_M &&
                xy1234test[1][0] > RIGHT_M &&
                xy1234test[1][2] > RIGHT_M &&
                xy1234test[0][0] > RIGHT_M &&

                xy1234test[0][0] < LEFT_M &&
                xy1234test[0][2] < LEFT_M &&
                xy1234test[1][0] < LEFT_M &&
                xy1234test[1][2] < LEFT_M) {
            return false;
        } else

            //test for other pieces
            //test if we can test
            if (a > 9 || b > 9 || c > 9 || d > 9) {
                return false;
            } else {
                if (a < 0 || b < 0 || c < 0 || d < 0) {
                    return false;
                } else {
                    if (e > 20 || f > 20 || g > 20 || h > 20) {
                        return false;
                    } else {
                        if (mat[e][a] == 1 || mat[f][b] == 1 || mat[g][c] == 1 || mat[h][d] == 1) {
                            return false;
                        }
                        return true;

                    }
                }
            }
    }

    public abstract int[][] rotation0();

    public abstract int[][] rotation1();

    public abstract int[][] rotation2();

    public abstract int[][] rotation3();

    public void updateRight() {
        xy1234[0][0] += SQSIZE;
        xy1234[0][2] += SQSIZE;
        xy1234[1][0] += SQSIZE;
        xy1234[1][2] += SQSIZE;
    }

    public void updateLeft() {
        xy1234[0][0] -= SQSIZE;
        xy1234[0][2] -= SQSIZE;
        xy1234[1][0] -= SQSIZE;
        xy1234[1][2] -= SQSIZE;
    }

    public void updateDown() {
        xy1234[0][1] -= SQSIZE;
        xy1234[0][3] -= SQSIZE;
        xy1234[1][1] -= SQSIZE;
        xy1234[1][3] -= SQSIZE;
    }

    public int[][] getPiecePosition() {
        return xy1234;
    }


}
