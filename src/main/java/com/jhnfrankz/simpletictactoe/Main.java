package com.jhnfrankz.simpletictactoe;

import java.util.Scanner;

public class Main {

    public static  Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] x = new char[3][3];

        startGame(x);
    }

    private static void startGame(char[][] x) {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                x[i][j] = ' ';
            }
        }

        printGrid(x);
        enterMove(x);
    }

    private static void printGrid(char[][] x) {
        System.out.println("---------");
        for (int i = 0; i < x.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < x[i].length; j++) {
                System.out.printf("%s ", x[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static void enterMove(char[][] x) {
        int coordX = 0;
        int coordY = 0;

        for (int move = 1; move <= 9; move++) {
            boolean isValidCoord;

            do {
                try {
                    System.out.print("Enter the coordinates: ");
                    coordX = Integer.parseInt(scanner.next());
                    coordY = Integer.parseInt(scanner.next());

                    if (!(coordX >= 1 && coordX <= 3) || !(coordY >= 1 && coordY <= 3)) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        isValidCoord = false;
                        continue;
                    }

                    if (x[coordX - 1][coordY - 1] != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                        isValidCoord = false;
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                    isValidCoord = false;
                }

                isValidCoord = true;
            } while (!isValidCoord);

            if (move % 2 == 1) {
                x[coordX - 1][coordY - 1] = 'X';
            } else {
                x[coordX - 1][coordY - 1] = 'O';
            }

            printGrid(x);

            if (checkGameStatus(x)) {
                System.exit(0);
            }
        }
    }

    private static boolean checkGameStatus(char[][] x) {
        boolean gameFinished = true;
        int conX = 0;
        int conO = 0;
        boolean existLineO = false;
        boolean existLineX = false;
        int sumDiagonal1 = 0;
        int sumDiagonal2 = 0;

        for (int i = 0; i < x.length; i++) {
            int sumRow = 0;
            int sumColumn = 0;

            for (int j = 0; j < x[i].length; j++) {
                if (x[i][j] == 'O') {
                    conO++;
                } else if (x[i][j] == 'X') {
                    conX++;
                }

                sumRow += x[i][j];
                sumColumn += x[j][i];
                sumDiagonal1 += x[j][j];
                sumDiagonal2 += x[j][2 - j];
            }

            if (sumRow == 237 || sumColumn == 237 ||
                    sumDiagonal1 == 237 || sumDiagonal2 == 237) {
                existLineO = true;
            }

            if (sumRow == 264 || sumColumn == 264 ||
                    sumDiagonal1 == 264 || sumDiagonal2 == 264) {
                existLineX = true;
            }

            if (existLineX && existLineO) {
                break;
            }
        }

        if (!existLineO && !existLineX && conX + conO == 9) {
            System.out.println("Draw");
        } else if (existLineX) {
            System.out.println("X wins");
        } else if (existLineO) {
            System.out.println("O wins");
        } else {
            gameFinished = false;
        }

        /*if ((conO - conX >= 2 || conX - conO >= 2) ||
                existLineX && existLineO) {
            System.out.println("Impossible");
        } else if (!existLineO && !existLineX && conX + conO == 9) {
            System.out.println("Draw");
        } else if (existLineX){
            System.out.println("X wins");
        } else if (existLineO){
            System.out.println("O wins");
        } else {
            gameFinished = false;
            System.out.println("Game not finished");
        }*/

        return gameFinished;
    }
}