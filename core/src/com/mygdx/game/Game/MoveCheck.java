package com.mygdx.game.Game;

import com.mygdx.game.GameScreen;

/**
 * Created by Sikanla on 13/01/2017.
 */

public class MoveCheck {
    private int[][] xy1234test = new int[2][4];

    private int LEFT_M, BOTTOM_M, RIGHT_M;
    private int SQSIZE;

    private int[][] mat;


    public MoveCheck(){
        this.SQSIZE = GameScreen.SQUARESIZE;
        LEFT_M = GameScreen.LEFT_M;
        RIGHT_M = GameScreen.RIGHT_M - SQSIZE;
        BOTTOM_M = GameScreen.BOTTOM_M;

    }

    public boolean isLeftPossible(int [][] xy1234) {
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

    public boolean isRightPossible(int [][] xy1234) {
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

    public boolean isDownPossible(int [][] xy1234) {
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


    public boolean isRotationPossible(int [][] xy1234) {
        getCurrentMatrix();

        int a, b, c, d;
        int e, f, g, h;


        xy1234test = xy1234;

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


        if (xy1234test[0][1] < BOTTOM_M ||
                xy1234test[0][3] < BOTTOM_M ||
                xy1234test[1][1] < BOTTOM_M ||
                xy1234test[1][3] < BOTTOM_M ||

                xy1234test[0][2] > RIGHT_M ||
                xy1234test[1][0] > RIGHT_M ||
                xy1234test[1][2] > RIGHT_M ||
                xy1234test[0][0] > RIGHT_M ||

                xy1234test[0][0] < LEFT_M ||
                xy1234test[0][2] < LEFT_M ||
                xy1234test[1][0] < LEFT_M ||
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



    private void getCurrentMatrix(){
        mat = Matrix.matrix;
    }
}
