package sample;

import java.util.Scanner;

public class Model {
        Scanner console = new Scanner(System.in);
        private int size = console.nextInt();
        private int[][] field = new int[size][size];
        private int Score = 0;


    Model() {
        generateCell();
        generateCell();
    }


    int GetField(int i, int j) {
        return field[i][j];
    }

    int GetScore() {
        return Score;
    }

    int GetSize() {
        return size;
    }

    void generateCell() {
        int i = (int)(Math.random()*size);
        int j = (int)(Math.random()*size);

        while (field[i][j] != 0) {
            i = (int)(Math.random()*size);
            j = (int)(Math.random()*size);
        }

        if(Math.random() <= 0.2)
            field[i][j] = 4;
        else
            field[i][j] = 2;
    }

    void reset() {
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = 0;
            }
        }

        generateCell();
        generateCell();

        Score = 0;
    }

    boolean checkGameOver() {
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == 0) {
                    return false;
                }
            }
        }

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i > 0) if(field[i][j] == field[i - 1][j]) return false;
                if(i < size - 1) if(field[i][j] == field[i + 1][j]) return false;
                if(j > 0) if(field[i][j] == field[i][j - 1]) return false;
                if(j < size - 1) if(field[i][j] == field[i][j + 1]) return false;
            }
        }
        return true;
    }


    void up(int haveModified, int newcell) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if (field[i][j] != 0) continue;

                for(int jj = j + 1; jj < size; jj++) {
                    if (field[i][jj] == 0) continue;

                    field[i][j] = field[i][jj];
                    field[i][jj] = 0;

                    newcell = 1;
                    break;
                }
            }
        }

        if(haveModified == 0) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size - 1; j++) {
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
        for(int i = 0; i < size; i++) {
            for(int j = size - 1; j >= 0; j--) {
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
            for (int i = 0; i < size; i++) {
                for(int j = size - 1; j > 0; j--) {
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
        for(int j = 0; j < size; j++) {
            for(int i = 0; i < size; i++) {
                if(field[i][j] == 0) {
                    for(int ii = i + 1; ii < size; ii++) {
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
            for (int j = 0; j < size; j++) {
                for (int i = 0; i < size - 1; i++) {
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
        for(int j = 0; j < size; j++) {
            for(int i = size - 1; i >= 0; i--) {
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
            for (int j = 0; j < size; j++) {
                for (int i = size - 1; i > 0; i--) {
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
