/* Welcome to tic-tac-toe game created in Java by Rotem Sharon
You can play user vs user
or user vs computer
or computer against computer
Hope you enjoy the game :) */

import java.util.Random;
import java.util.Scanner;

public class Tic_Tac_Toe_full {

    static Scanner scanner = new Scanner(System.in);

    //static variable to keep the correct turn counting
    static int turnCount;

    //static variable to keep if there is a winner or not
    static boolean winner;

    public static void main(String[] args) {

        //A method that starts the game and you can return to it at the end of the game and start a new game
        startTheGame();

    }

    //A method that lets you choose whether to play against another player, against the computer, or computer against computer
    static void startTheGame() {
        System.out.println
                ("hello user! welcome to tic-tac-toe game by Rotem Sharon\n" +
                        "to play USER VS USER enter the number 1\n" +
                        "to play USER VS PC enter the number 2\n" +
                        "to play PC VS PC enter the number 3\n" +
                        "to exit program enter the number 0");

        int playingOptions = scanner.nextInt();

        switch (playingOptions) {
            case 1 -> {
                System.out.println("you choose to play USER VS USER!");
                System.out.println("O.K let's start the game!");
                System.out.println();
                gameUserVsUser();
            }
            case 2 -> {
                System.out.println("you choose to play USER VS PC!");
                System.out.println("O.K let's start the game!");
                System.out.println();
                gameUserVsPc();
            }
            case 3 -> {
                System.out.println("you choose to play PC VS PC!");
                System.out.println("O.K let's start the game!");
                System.out.println();
                gamePcVsPc();
            }
            case 0 -> {
                System.out.println("you choose to end the game");
                System.out.println("goodbye");
                System.exit(0);
            }
            default -> {
                System.out.println("please choose one of the options below");
                System.out.println();
                startTheGame();
            }
        }

    }

    //A method that creates the matrix
    static String[][] createNewMatrix() {
        //choosing the size of the matrix
        System.out.println("""
                please enter the size of the game board (the matrix size) that you want
                for normal size - 3X3, please enter 3
                for 4X4, please enter 4
                for 5X5, please enter 5
                and so on""");

        int matrixSize = scanner.nextInt();

        //Check that the matrix size is at least 3 by 3
        while (matrixSize < 3) {
            System.out.println("you need a matrix size at least 3X3");
            matrixSize = scanner.nextInt();
        }

        //Creating the matrix
        String[][] matrix = new String[matrixSize][matrixSize];

        System.out.println("you choose game bord in size " + matrixSize + "X" + matrixSize);

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = "[_]";
            }
        }
        return matrix;
    }

    //A method that prints the matrix every time it is called
    static void printTheMatrix(String[][] matrix) {
        for (String[] rows : matrix) {
            System.out.println();
            for (String placeInRow : rows) {
                System.out.print(placeInRow);
            }
        }
        System.out.println();
    }

    //A method of X or O turn
    static String XorOTurn() {
        String XorO;

        //If turnCount is divisible by 2 without a remainder, then it's X turn, and if there is a remainder, then it's O turn
        if (turnCount % 2 == 0) {
            XorO = "[X]";
        } else {
            XorO = "[O]";
        }

        return XorO;
    }

    //method of the user's turn
    static void userTurn(String[][] matrix) {

        //An array where the first variable is the row number and the second variable is the column number
        int[] rowAndColumnNumber = new int[2];

        do {

            System.out.println("it's " + XorOTurn() + " turn");
            printTheMatrix(matrix);
            System.out.println();
            //Checking if there is a possibility to win in the current turn, If so then it prints the row and column that needed be entered to win
            canWinInNextMove(matrix);

            //Changes the string to row to ask the user which row to select
            String rowOrColumn = "row";

            //Runs the command twice, once for the rows and once for the columns
            for (int i = 0; i < 2; i++) {
                do {
                    System.out.println("please enter a empty " + rowOrColumn + " between 1-" + matrix.length);
                    rowAndColumnNumber[i] = scanner.nextInt() - 1;

                    if (rowAndColumnNumber[i] < 0 || rowAndColumnNumber[i] >= matrix.length) {
                        System.out.println("you enter a WRONG! " + rowOrColumn + " number");
                        System.out.println();
                    }
                } while (rowAndColumnNumber[i] < 0 || rowAndColumnNumber[i] >= matrix.length);

                //Changes the string to column to ask the user which column to select
                rowOrColumn = "column";
            }

            //Checks if the place is empty
            if (!(matrix[rowAndColumnNumber[0]][rowAndColumnNumber[1]].equals("[_]"))) {
                System.out.println();
                System.out.println("this place is NOT empty, please choose other place");
            }

        } while (!(matrix[rowAndColumnNumber[0]][rowAndColumnNumber[1]].equals("[_]")));
        //If everything is correct and the place is empty, then the method puts X or O (according to the current turn) in the chosen place
        matrix[rowAndColumnNumber[0]][rowAndColumnNumber[1]] = XorOTurn();
    }

    //Method of the computer turn
    static void pcTurn(String[][] matrix) {

        Random random = new Random();

        System.out.println("it's " + XorOTurn() + " turn");

        //Sends to a method that checks if the computer can win the current turn
        int[] rowAndColumn =canWinInNextMove(matrix);

        //If the method returns -1 and -1 then there is no possibility of winning and the computer chooses random numbers
        if (rowAndColumn[0] == -1 && rowAndColumn[1] == -1) {
            do {
                //Generates a random numbers
                rowAndColumn[0] = random.nextInt(matrix.length);
                rowAndColumn[1] = random.nextInt(matrix.length);

                //Checks if the place is empty
            } while (!matrix[rowAndColumn[0]][rowAndColumn[1]].equals("[_]"));
        }

        //puts X or O (according to the current turn) in the chosen place
        matrix[rowAndColumn[0]][rowAndColumn[1]] = XorOTurn();

        //Prints to the user what the computer has selected
        System.out.println("the computer choose: row " + (rowAndColumn[0] + 1) + " colum " + (rowAndColumn[1] + 1));

        printTheMatrix(matrix);
        System.out.println();

    }

    //"User vs. User" playing method
    static void gameUserVsUser() {

        String[][] matrix = createNewMatrix();
        turnCount = 0;

        System.out.println();
        System.out.println("----- Game Instructions -----");
        System.out.println("you can insert a-X or a-O in any place you wants by writing the Row number and then writing the column number");
        System.out.println("an empty place look like this: [_]");
        System.out.println("you can't insert X or O in a full place");
        System.out.println("----------------------------------------");
        System.out.println();

        while (true) {
            userTurn(matrix);

            if (winnerCheck(matrix))
                break;
            else if (draw(matrix))
                break;

            turnCount++;
        }

        endGame();
    }

    //"User vs. pc" playing method
    static void gameUserVsPc() {

        String[][] matrix = createNewMatrix();
        turnCount = 0;

        System.out.println();
        System.out.println("----- Game Instructions -----");
        System.out.println("you play as a X, and the computer play's as a O");
        System.out.println("you can insert a-X in any place you wants by writing the Row number and then writing the column number");
        System.out.println("an empty place look like this: [_]");
        System.out.println("you can't insert X in a full place");
        System.out.println("----------------------------------------");
        System.out.println();

        while (true) {
            userTurn(matrix);

            if (winnerCheck(matrix))
                break;
            else if (draw(matrix))
                break;

            turnCount++;

            pcTurn(matrix);

            if (winnerCheck(matrix))
                break;
            else if (draw(matrix))
                break;

            turnCount++;
        }

        endGame();
    }

    //"pc vs. pc" playing method
    static void gamePcVsPc() {

        String[][] matrix = createNewMatrix();
        turnCount = 0;

        System.out.println();
        System.out.println("----- Game Instructions -----");
        System.out.println("the computer play's against himself");
        System.out.println("----------------------------------------");
        printTheMatrix(matrix);
        System.out.println();


        while (true) {
            pcTurn(matrix);

            if (winnerCheck(matrix))
                break;
            else if (draw(matrix))
                break;

            turnCount++;
        }

        endGame();
    }

    //A method that checks the matrix and returns if someone can win or if there is a winner
    static int[] matrixCheck(String[][] matrix) {

        int winCounter = 0;
        int[] canWinRowAndColum = {-1, -1};
        //If there is a winner then the value in "winner" changes to true
        winner = false;

        //בדיקה של הטורים
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i].equals(XorOTurn())) {
                    winCounter++;
                }
            }
            if (winCounter == matrix.length - 1) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[j][i].equals("[_]")) {
                        canWinRowAndColum[0] = j;
                        canWinRowAndColum[1] = i;
                    }
                }
            }

            if (winCounter == matrix.length) {
                winner = true;
            }

            winCounter = 0;
        }

        //בדיקה של השורות
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j].equals(XorOTurn())) {
                    winCounter++;
                }
            }
            if (winCounter == matrix.length - 1) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j].equals("[_]")) {
                        canWinRowAndColum[0] = i;
                        canWinRowAndColum[1] = j;
                    }
                }
            }

            if (winCounter == matrix.length) {
                winner = true;
            }

            winCounter = 0;
        }

        // בדיקת אלכסון
        for (int i = 0, j = 0; i < matrix.length; i++, j++) {
            if (matrix[i][j].equals(XorOTurn())) {
                winCounter++;
            }
        }
        if (winCounter == matrix.length - 1) {
            for (int i = 0, j = 0; i < matrix.length; i++, j++) {
                if (matrix[i][j].equals("[_]")) {
                    canWinRowAndColum[0] = i;
                    canWinRowAndColum[1] = j;
                }
            }
        }

        if (winCounter == matrix.length) {
            winner = true;
        }

        winCounter = 0;

        // בדיקת אלכסון נגדי
        for (int i = 0, j = matrix.length - 1; i < matrix.length; i++, j--) {
            if (matrix[i][j].equals(XorOTurn())) {
                winCounter++;
            }
        }
        if (winCounter == matrix.length - 1) {
            for (int i = 0, j = matrix.length - 1; i < matrix.length; i++, j--) {
                if (matrix[i][j].equals("[_]")) {
                    canWinRowAndColum[0] = i;
                    canWinRowAndColum[1] = j;
                }
            }
        }

        if (winCounter == matrix.length) {
            winner = true;
        }

        winCounter = 0;

        //In case it is not possible to win, the method returns -1, -1 and if there is a possibility, it returns the row and column number
        return canWinRowAndColum;
    }

    //A method that declare if there is a possibility of winning
    static int[] canWinInNextMove(String[][] matrix) {

        int[] canWinRowAndColum = matrixCheck(matrix);

        if (canWinRowAndColum[0] != -1 && canWinRowAndColum[1] != -1) {

            System.out.println("you can win in the next move if you enter " + XorOTurn() +
                    " in row " + (canWinRowAndColum[0] + 1) + " and colum " + (canWinRowAndColum[1] + 1));
        }

        return canWinRowAndColum;
    }

    //A method that declare if there is a winner
    static boolean winnerCheck(String[][] matrix) {

        matrixCheck(matrix);

        if (winner) {
            System.out.println();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("the winner is " + XorOTurn() + "!!!");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            printTheMatrix(matrix);
            System.out.println();
            return true;

        } else {
            return false;
        }
    }

    //A function that checks if there is a draw
    static boolean draw(String[][] matrix) {
        for (String[] rows : matrix) {
            for (String placeInRow : rows) {
                if (placeInRow.equals("[_]")) {
                    return false;
                }
            }
        }
        System.out.println();
        System.out.println("GAME OVER!");
        System.out.println("it's a draw!");
        printTheMatrix(matrix);
        System.out.println();
        return true;
    }

    //A method that ends the game and asks if the user wants to play again
    static void endGame() {
        int leaveOrStay;

        System.out.println("to start new game enter 1");
        System.out.println("to exit enter Anything else");
        leaveOrStay = scanner.nextInt();

        if (leaveOrStay == 1) {
            startTheGame();
        } else {
            System.out.println("goodbye");
            System.exit(0);
        }
    }
}

/*
by Rotem Sharon
 */
