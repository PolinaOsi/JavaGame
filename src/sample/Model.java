package sample;

public class Model {
    int[][] field = new int[4][4];
    int Score = 0;

    Model() {
        generateCell();
        generateCell();
    }

    void generateCell() {
        int i = (int)(Math.random()*4);
        int j = (int)(Math.random()*4);

        while (field[i][j] != 0) {
            i = (int)(Math.random()*4);
            j = (int)(Math.random()*4);
        }

        if(Math.random() <= 0.2)
            field[i][j] = 4;
        else
            field[i][j] = 2;
    }

    void reset() {
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                field[i][j] = 0;
            }
        }

        generateCell();
        generateCell();

        Score = 0;
    }

    boolean checkGameOver() {
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (field[i][j] == 0) {
                    return false;
                }
            }
        }

        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(i > 0) if(field[i][j] == field[i - 1][j]) return false;
                if(i < 3) if(field[i][j] == field[i + 1][j]) return false;
                if(j > 0) if(field[i][j] == field[i][j - 1]) return false;
                if(j < 3) if(field[i][j] == field[i][j + 1]) return false;
            }
        }

        return true;
    }


    void up(int haveModified, int newcell) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if (field[i][j] != 0) continue;

                for(int jj = j + 1; jj < 4; jj++) {
                    if (field[i][jj] == 0) continue;

                    field[i][j] = field[i][jj];
                    field[i][jj] = 0;

                    newcell = 1;
                    break;
                }
            }
        }

        if(haveModified == 0) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] != 0) {
                        if (field[i][j] == field[i][j + 1]) {
                            field[i][j] += field[i][j + 1];
                            field[i][j + 1] = 0;

                            Score += field[i][j];
                            newcell = 1;
                            break;
                        }
                    } else break;
                }
            }
            this.up(1, newcell);
            return;
        }

        if(newcell == 1) generateCell();

        return;
    }


    void down(int haveModified, int newcell) {
        for(int i = 0; i < 4; i++) {
            for(int j = 3; j >= 0; j--) {
                if(field[i][j] == 0) {
                    for(int jj = j - 1; jj >= 0; jj--) {
                        if(field[i][jj] != 0) {
                            field[i][j] = field[i][jj];
                            field[i][jj] = 0;

                            newcell = 1;
                            break;
                        }
                    }
                }
            }
        }

        if(haveModified == 0) {
            for (int i = 0; i < 4; i++) {
                for(int j = 3; j > 0; j--) {
                    if(field[i][j] != 0) {
                        if (field[i][j] == field[i][j - 1]) {
                            field[i][j] += field[i][j - 1];
                            field[i][j - 1] = 0;

                            Score += field[i][j];
                            newcell = 1;
                            break;
                        }
                    } else break;
                }
            }
            this.down(1, newcell);
            return;
        }

        if(newcell == 1) generateCell();

        return;
    }


    void left(int haveModified, int newcell) {
        for(int j = 0; j < 4; j++) {
            for(int i = 0; i < 4; i++) {
                if(field[i][j] == 0) {
                    for(int ii = i + 1; ii < 4; ii++) {
                        if(field[ii][j] != 0) {
                            field[i][j] = field[ii][j];
                            field[ii][j] = 0;

                            newcell = 1;
                            break;
                        }
                    }
                }
            }
        }

        if(haveModified == 0) {
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 3; i++) {
                    if (field[i][j] != 0) {
                        if (field[i][j] == field[i + 1][j]) {
                            field[i][j] += field[i + 1][j];
                            field[i + 1][j] = 0;

                            Score += field[i][j];
                            newcell = 1;
                            break;
                        }
                    } else break;
                }
            }
            this.left(1, newcell);
            return;
        }

        if(newcell == 1) generateCell();

        return;
    }


    void right(int haveModified, int newcell) {
        for(int j = 0; j < 4; j++) {
            for(int i = 3; i >= 0; i--) {
                if(field[i][j] == 0) {
                    for(int ii = i - 1; ii >= 0; ii--) {
                        if(field[ii][j] != 0) {
                            field[i][j] = field[ii][j];
                            field[ii][j] = 0;

                            newcell = 1;
                            break;
                        }
                    }
                }
            }
        }

        if(haveModified == 0) {
            for (int j = 0; j < 4; j++) {
                for (int i = 3; i > 0; i--) {
                    if (field[i][j] != 0) {
                        if (field[i][j] == field[i - 1][j]) {
                            field[i][j] += field[i - 1][j];
                            field[i - 1][j] = 0;

                            Score += field[i][j];
                            newcell = 1;
                            break;
                        }
                    } else break;
                }
            }
            this.right(1, newcell);
            return;
        }

        if(newcell == 1) generateCell();

        return;
    }
}
