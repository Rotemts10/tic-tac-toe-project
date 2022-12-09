//ברוכים הבאים למשחק איקס עיגול שנוצר בג'אווה על ידי רותם שרון
// ניתן לשחק משתמש נגד משתמש
//או משתמש נגד מחשב
//או מחשב נגד מחשב
// מקווה שתהנו מהמשחק :)

import java.util.Random;
import java.util.Scanner;

public class Tic_Tac_Toe_full {

    //אובייקטים שאני משתמש בהם הרבה ולכן שמתי אותם בתור "סטטים"
    static Scanner scanner = new Scanner(System.in);
    static int matrixSize = 0;

    public static void main(String[] args) {

        startTheGame();

    }

    //מתודה שנותנת לבחור אם לשחק נגד שחקן אחר, נגד המחשב, או מחשב נגד מחשב
    static void startTheGame() {
        System.out.println("hello user! welcome to tic-tac-toe game by Rotem Sharon");
        System.out.println("to play USER VS USER enter the number 1");
        System.out.println("to play USER VS PC enter the number 2");
        System.out.println("to play PC VS PC enter the number 3");
        System.out.println("to exit program enter the number 0");

        int whoIsPlaying;
        boolean playerChooseToStartIsOk = false;

        while (playerChooseToStartIsOk == false) {
            whoIsPlaying = scanner.nextInt();

            switch (whoIsPlaying) {
                case 1:

                    System.out.println("you choose to play USER VS USER!");
                    System.out.println("O.K let's start the game!");
                    System.out.println();
                    playerChooseToStartIsOk = true;

                    openingScreenUserVsUser();

                    break;

                case 2:

                    System.out.println("you choose to play USER VS PC!");
                    System.out.println("O.K let's start the game!");
                    System.out.println();
                    playerChooseToStartIsOk = true;

                    openingScreenUserVsPc();

                    break;

                case 3:

                    System.out.println("you choose to play PC VS PC!");
                    System.out.println("O.K let's start the game!");
                    System.out.println();
                    playerChooseToStartIsOk = true;

                    openingScreenPcVsPc();

                    break;

                case 0:

                    System.out.println("you choose to end the game");
                    System.out.println("goodbye");
                    System.exit(0);
                    playerChooseToStartIsOk = true;
                    break;

                default:
                    System.out.println();
                    System.out.println("you enter a wrong number");
                    System.out.println("to play USER VS USER enter the number 1");
                    System.out.println("to play USER VS PC enter the number 2");
                    System.out.println("to play PC VS PC enter the number 3");
                    System.out.println("to exit program enter the number 0");
                    playerChooseToStartIsOk = false;
            }
        }

    }

    //מתודה שיוצרת את המטריצה הראשונית /המטריצה הריקה
    static String[][] createNewMatrix() {
        //בחירת גודל המטריצה
        System.out.println("please enter the size of the game board (the matrix size) that you want");
        System.out.println("for normal size - 3X3, please enter 3");
        System.out.println("for 4X4, please enter 4");
        System.out.println("for 5X5, please enter 5");
        System.out.println("and so on");

        matrixSize = scanner.nextInt();

        //בדיקה שהמטריצה בגודל של לפחות 3 על 3
        while (matrixSize < 3) {
            System.out.println("you need a matrix size at least 3X3");
            matrixSize = scanner.nextInt();
        }

        // יצירת המטריצה
        String[][] matrix = new String[matrixSize][matrixSize];

        System.out.println("you choose game bord in size " + matrixSize + "X" + matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = "[_]";
            }
        }
        return matrix;
    }

    //פונקציה שמדפיסה את המטריצה כל פעם שקוראים לה
    static void printTheMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }

        }
        System.out.println();
    }

    // מסך פתיחה של משחק "משתמש נגד משתמש"
    static void openingScreenUserVsUser() {

        String[][] matrix = createNewMatrix();
        int turnCount = 0;

        System.out.println();
        System.out.println("----- Game Instructions -----");
        System.out.println("you can insert a-X or a-O in any place you wants by writing the Row number and then writing the column number");
        System.out.println("an empty place look like this: [_]");
        System.out.println("you can't insert X or O in a full place");
        System.out.println("----------------------------------------");
        printTheMatrix(matrix);
        System.out.println();

        gameUserVsUser(matrix, turnCount);

    }

    //מהלך המשחק של "משתמש נגד משתמש"
    // המתודה הזאת חוזרת על עצמה ברקורסיה עד שמישהו מנצח או שיש תיקו
    static void gameUserVsUser(String[][] matrix, int turnCount) {
        //שולח למתודה שבודקת אם זה תור X או תור O לפי ספירת תורות
        String XorO = playerXorOTurn(turnCount);

        int row;
        int column;

        System.out.println("it's " + XorO + " turn");

        winInNextMove(matrix, XorO);

        //מבקשת להכניס מספר שורה ומספר טור ובודקת אם המקום ריק או מלא
        do {
            //בדיקה שהמספר של השורה הוא בין 1 לגודל המטריצה
            do {
                System.out.println("please enter a empty row between 1-" + matrixSize);
                row = scanner.nextInt() - 1;

                if (row < 0 || row >= matrixSize) {
                    System.out.println("you enter a WRONG! row number");
                    System.out.println();
                }
            } while (row < 0 || row >= matrixSize);

            //בדיקה שהמספר של הטור הוא בין 1 לגודל המטריצה
            do {
                System.out.println("please enter a empty column between 1-" + matrixSize);
                column = scanner.nextInt() - 1;
                if (column < 0 || column >= matrixSize) {
                    System.out.println("you enter a WRONG! column number");
                    System.out.println();
                }
            } while (column < 0 || column >= matrixSize);

            //בודק אם המקום ריק
            if (matrix[row][column] == "[_]") {
                matrix[row][column] = XorO;
            } else {
                System.out.println();
                System.out.println("this place is NOT empty, please choose other place");
                turnCount--;
            }

        } while (matrix[row][column] == "[_]");

        printTheMatrix(matrix);
        System.out.println();

        //אם התור היה בסדר והכניסו למקום ריק איקס או עיגול אז הספירה עולה ב-1
        turnCount++;

        //בדיקה אם איקס או עיגול ניצחו
        winnerCheck(matrix, XorO);

        //בדיקה אם הלוח מלא ויש תיקו
        boolean drew = draw(matrix);
        if (drew) {
            endGame();
        }

        //רקורסיה של המדותה שחוזרת על עצמה עד שמישהו מנצח או שיש תיקו
        gameUserVsUser(matrix, turnCount);

    }

    //פונקציה של התור של שחקן איקס או עיגול
    static String playerXorOTurn(int turnCount) {
        String XorO;
        if (turnCount % 2 == 0) {
            XorO = "[X]";
        } else {
            XorO = "[O]";
        }

        return XorO;

    }

    // מסך פתיחה של משחק "משתמש נגד מחשב"
    static void openingScreenUserVsPc() {
        String[][] matrix = createNewMatrix();

        System.out.println();
        System.out.println("----- Game Instructions -----");
        System.out.println("you play as a X, and the computer play's as a O");
        System.out.println("you can insert a-X in any place you wants by writing the Row number and then writing the column number");
        System.out.println("an empty place look like this: [_]");
        System.out.println("you can't insert X in a full place");
        System.out.println("----------------------------------------");
        printTheMatrix(matrix);
        System.out.println();

        gameUserVsPc(matrix);

    }

    //מהלך המשחק של "משתמש נגד מחשב"
    static void gameUserVsPc(String[][] matrix) {

        int row;
        int column;
        //המשתמש מקבל את X
        String XorO = "[X]";

        System.out.println("it's " + XorO + " turn");

        // מתודה שבודקת אם יש אפשרות לניצחון בתור הזה ואם כן היא מודיעה על זה לשחקן
        winInNextMove(matrix, XorO);

        //מבקשת להכניס מספר שורה ומספר טור ובודקת אם המקום ריק או מלא
        do {
            //בדיקה שהמספר של השורה הוא בין 1 לגודל המטריצה
            do {
                System.out.println("please enter a empty row between 1-" + matrixSize);
                row = scanner.nextInt() - 1;

                if (row < 0 || row >= matrixSize) {
                    System.out.println("you enter a WRONG! row number");
                    System.out.println();
                }
            } while (row < 0 || row >= matrixSize);

            //בדיקה שהמספר של הטור הוא בין 1 לגודל המטריצה
            do {
                System.out.println("please enter a empty column between 1-" + matrixSize);
                column = scanner.nextInt() - 1;

                if (column < 0 || column >= matrixSize) {
                    System.out.println("you enter a WRONG! column number");
                    System.out.println();
                }
            } while (column < 0 || column >= matrixSize);

            //בודק אם המקום ריק
            if (matrix[row][column] != "[_]") {
                System.out.println();
                System.out.println("this place is NOT empty, please choose other place");
                System.out.println("it's " + XorO + " turn");
                printTheMatrix(matrix);
            }

        } while (matrix[row][column] != "[_]");
        matrix[row][column] = XorO;

        printTheMatrix(matrix);
        System.out.println();

        //בדיקה אם איקס או עיגול ניצחו
        winnerCheck(matrix, XorO);

        //בדיקה אם הלוח מלא ויש תיקו
        boolean drew = draw(matrix);
        if (drew) {
            endGame();
        }

        //שולח למתודה "התור של המחשב" את ה-O
        XorO = "[O]";
        //שולח למתודה "התור של המחשב" מחרוזת שאומרת לו לחזור למשתמש כשהמתודה מסיימת
        String backToPcOrUser = "user";
        int turnCount = 0;
        pcTurn(matrix, XorO, backToPcOrUser, turnCount);

    }

    // המתודה ששולטת בתור של המחשב
    static void pcTurn(String[][] matrix, String XorO, String backToPcOrUser, int turnCount) {
        int row;
        int column;
        //מייצר מספר אקראי
        Random random = new Random();

        System.out.println("it's " + XorO + " turn");

        // שולח למתודה שבודקת אם המחשב יכול לנצח בתור הבא
        //אם כן אז המתודה מחזירה שורה וטור והמחשב מציב שם את ה-X או ה-O (לפי התור הנוכחי)
        // ואם לא אז נבחרים מספרים רנדומליים לשורה ולטור
        int[] canWin = new int[2];
        canWin = winInNextMove(matrix, XorO);

        if (canWin[0] != -1 && canWin[1] != -1){
            row = canWin[0];
            column = canWin[1];

        }else {

            //נותן למחשב לבחור מספר אקראי ושם אותו בשורה והטור
            do {
                row = random.nextInt(matrixSize);
                column = random.nextInt(matrixSize);

                //בודק אם המקום ריק
            } while (matrix[row][column] != "[_]");
        }

        // מכניס את הסמל (איקס או עיגל) למקום שנבחר בלוח
        matrix[row][column] = XorO;

        // אומר למשתמש מה המחשב בחר
        System.out.println("the computer choose: row " + (row + 1) + " colum " + (column + 1));

        printTheMatrix(matrix);
        System.out.println();

        //בדיקה אם איקס או עיגול ניצחו
        winnerCheck(matrix, XorO);

        // שולח למתודה שבודקת אם יש תיקו, ואם יש אז היא מסיימת את המשחק
        boolean drew = draw(matrix);
        if (drew) {
            endGame();
        }

        //בדיקה לאן צריך לחזור בסיום הפונקציה, לתור של המשתמש או לתור של המחשב
        if (backToPcOrUser == "user") {
            gameUserVsPc(matrix);
        }

        if (backToPcOrUser == "pc") {
            turnCount++;
            gamePcVsPc(matrix, turnCount);
        }
    }

    // מסך פתיחה של משחק "מחשב נגד מחשב"
    static void openingScreenPcVsPc() {
        String[][] matrix = createNewMatrix();
        int turnCount = 0;

        System.out.println();
        System.out.println("----- Game Instructions -----");
        System.out.println("the computer play's against himself");
        System.out.println("----------------------------------------");
        printTheMatrix(matrix);
        System.out.println();

        gamePcVsPc(matrix, turnCount);

    }

    //מהלך המשחק של "מחשב נגד מחשב"
    static void gamePcVsPc(String[][] matrix, int turnCount) {
        //שולח לפונקציה שבוחרת אם זה תור X או תור O
        String XorO = playerXorOTurn(turnCount);
        //שולח למתודה "התור של המחשב" מחרוזת שאומרת לו לחזור למחשב כשהמתודה מסיימת
        String backToPcOrUser = "pc";
        pcTurn(matrix, XorO, backToPcOrUser, turnCount);

    }

    // מתודה שבודקת אם יש אפשרות לנצח בתור הקרוב
    // אם יש אפשרות היא מודיעה על זה לשחקן
    // אם השחקן הוא מחשב אז היא מחזירה את מספר השורה והטור ואז המחשב מנצח בעזרתם
    static int[] winInNextMove(String[][] matrix, String XorO) {
        int winCounter = 0;
        int[] returnRowAndColum = {-1, -1};

        //בדיקה של הטורים
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrix[j][i] == XorO) {
                    winCounter++;
                }
            }
            if (winCounter == matrixSize - 1) {
                for (int j = 0; j < matrixSize; j++) {
                    if (matrix[j][i] == "[_]") {
                        System.out.println("you can win in the next move if you enter " + XorO +
                                " in row " + (j + 1) + " and colum " + (i + 1));
                        winCounter = 0;
                        returnRowAndColum[0] = j;
                        returnRowAndColum[1] = i;
                    }
                }
            }
            winCounter = 0;
        }

        //בדיקה של השורות
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrix[i][j] == XorO) {
                    winCounter++;
                }
            }
            if (winCounter == matrixSize - 1) {
                for (int j = 0; j < matrixSize; j++) {
                    if (matrix[i][j] == "[_]") {
                        System.out.println("you can win in the next move if you enter " + XorO +
                                " in row " + (i + 1) + " and colum " + (j + 1));
                        winCounter = 0;
                        returnRowAndColum[0] = i;
                        returnRowAndColum[1] = j;
                    }
                }
            }
            winCounter = 0;
        }

        // בדיקת אלכסון
        for (int i = 0, j = 0; i < matrixSize; i++, j++) {
            if (matrix[i][j] == XorO) {
                winCounter++;
            }
        }
        if (winCounter == matrixSize - 1) {
            for (int i = 0, j = 0; i < matrixSize; i++, j++) {
                if (matrix[i][j] == "[_]") {
                    System.out.println("you can win in the next move if you enter " + XorO +
                            " in row " + (i + 1) + " and colum " + (j + 1));
                    winCounter = 0;
                    returnRowAndColum[0] = i;
                    returnRowAndColum[1] = j;
                }
            }
        }
        winCounter = 0;

        // בדיקת אלכסון נגדי
        for (int i = 0, j = matrixSize - 1; i < matrixSize; i++, j--) {
            if (matrix[i][j] == XorO) {
                winCounter++;
            }
        }
        if (winCounter == matrixSize - 1) {
            for (int i = 0, j = matrixSize - 1; i < matrixSize; i++, j--) {
                if (matrix[i][j] == "[_]") {
                    System.out.println("you can win in the next move if you enter " + XorO +
                            " in row " + (i + 1) + " and colum " + (j + 1));
                    winCounter = 0;
                    returnRowAndColum[0] = i;
                    returnRowAndColum[1] = j;
                }
            }
        }
        winCounter = 0;

        // במקרה שאין אפשרות לנצח בתור הבא הפונקציה מחזירה -1,-1 ואם יש אפשרות היא מחזירה את מספר השורה והטור
        return returnRowAndColum;
    }

    //פונקציה שבודקת אם יש תיקו
    static boolean draw(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == "[_]") {
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

    //פונקציה שבודקת אם יש מנצח
    static void winnerCheck(String[][] matrix, String XorO) {
        int winCounter = 0;

        //בדיקה של הטורים
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrix[j][i] == XorO) {
                    winCounter++;
                }
            }
            if (winCounter == matrixSize) {
                theWinnerIs(matrix, XorO);
            } else {
                winCounter = 0;
            }
        }

        //בדיקה של השורות
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrix[i][j] == XorO) {
                    winCounter++;
                }
            }
            if (winCounter == matrixSize) {
                theWinnerIs(matrix, XorO);
            } else {
                winCounter = 0;
            }
        }

        // בדיקת אלכסון
        for (int i = 0, j = 0; i < matrixSize; i++, j++) {
            if (matrix[i][j] == XorO) {
                winCounter++;
            }
        }
        if (winCounter == matrixSize) {
            theWinnerIs(matrix, XorO);
        } else {
            winCounter = 0;
        }

        // בדיקת אלכסון נגדי
        for (int i = 0, j = matrixSize - 1; i < matrixSize; i++, j--) {
            if (matrix[i][j] == XorO) {
                winCounter++;
            }
        }
        if (winCounter == matrixSize) {
            theWinnerIs(matrix, XorO);
        } else {
            winCounter = 0;
        }
    }

    //פונקציה שמכריזה על המנצח
    static void theWinnerIs(String[][] matrix, String XorO) {
        System.out.println();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("the winner is " + XorO + "!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        printTheMatrix(matrix);
        System.out.println();
        endGame();
    }

    //פונקציה שמסיימת את המשחק ושואלת אם רוצים משחק חוזר
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