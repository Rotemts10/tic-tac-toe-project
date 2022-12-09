import java.util.Scanner;

public class Tic_Tac_Toe {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        startNewGame();
    }

    //תחילת משחק חדש
    static void startNewGame() {
        //הדפסת המטריצה
        String[][] matrix = {{"[_]", "[_]", "[_]"}, {"[_]", "[_]", "[_]"}, {"[_]", "[_]", "[_]"}};

        //הוראות למשחק
        System.out.println("hello user! welcome to tic-tac-toe game");
        System.out.println("you can insert a-X or a-O in any place you wants by writing the Row number and then writing the column number");
        System.out.println("there are 3 rows and 3 columns");
        System.out.println("an empty place look like this: [_]");
        System.out.println("you can't insert a X or O in a full place");
        System.out.println("to start the game enter the number 1");
        System.out.println("to exit program enter the number 0");

        int playerChooseToStart;
        int turnCount;

        //בחירה האם להתחיל את המשחק
        //אם המשחק התחיל אז להתחיל לספור תורות, אם התור זוגי אז תור עיגול ואם לא אז תור איקס
        do {

            playerChooseToStart = scanner.nextInt();
            switch (playerChooseToStart) {
                case 1:

                    System.out.println("O.K let's start the game!");
                    System.out.println();
                    turnCount = 1;
                    playerXTurn(matrix, turnCount);

                    break;
                case 0:

                    System.out.println("goodbye");
                    System.exit(0);
            }
        } while (playerChooseToStart != 0 && playerChooseToStart != 1);

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

    //פונקציה שנותנת לבחור מקום על המטריצה ולשים שם איקס או עיגול
    //הפונקציה גם בודקת בעזרת פונקציות אחרות אם מישהו ניצח או אם יש תיקו
    static void placeChoose(String[][] matrix, String XorO, int turnCount) {

        int row;
        int column;

        //מבקשת להכניס מספר שורה ומספר טור ובודקת אם המקום ריק או מלא
        do {
            //בדיקה שהמספר של השורה הוא בין 1 ל-3
            do {
                System.out.println("please enter a empty row between 1-3:");
                row = scanner.nextInt() - 1;

                if (row < 0 || row > 2){
                    System.out.println("you enter a WRONG! row number");
                    System.out.println();
                }
            }while (row < 0 || row > 2);

            //בדיקה שהמספר של הטור הוא בין 1 ל-3
            do {
                System.out.println("please enter a empty column between 1-3:");
                column = scanner.nextInt() - 1;
                if (column < 0 || column > 2){
                    System.out.println("you enter a WRONG! column number");
                    System.out.println();
                }
            }while (column < 0 || column > 2);

            //בודק אם המקום ריק
            if (matrix[row][column] == "[_]") {
                matrix[row][column] = XorO;
            } else {
                System.out.println();
                System.out.println("this place is NOT empty, please choose other place");
                turnCount--;
            }

        } while (matrix[row][column] == "[_]");

        //אם התור היה בסדר והכניסו למקום ריק איקס או עיגול אז הספירה עולה ב-1
        turnCount++;

        //בדיקה אם איקס או עיגול ניצחו
        boolean isSomeoneWin = false;
        isSomeoneWin = winCheck(matrix, XorO);
        if (isSomeoneWin) {
            endGame();
        }

        //בדיקה אם הלוח מלא ויש תיקו
        boolean drew = draw(matrix);
        if (drew) {
            endGame();
        }

        //אם עוד לא ניצחו וגם אין תיקו והתור הקודם היה תקין, אז העברה לתור של השחקן השני
        if (turnCount % 2 == 0) {
            playerOTurn(matrix, turnCount);
        } else {
            playerXTurn(matrix, turnCount);
        }

    }

    //פונקציה של התור של שחקן איקס
    static void playerXTurn(String[][] matrix, int turnCount) {
        String X = "[X]";
        System.out.println("it's player X turn");
        printTheMatrix(matrix);
        placeChoose(matrix, X, turnCount);

    }

    //פונקציה של התור של שחקן עיגול
    static void playerOTurn(String[][] matrix, int turnCount) {
        String O = "[O]";
        System.out.println("it's player O turn");
        printTheMatrix(matrix);
        placeChoose(matrix, O, turnCount);

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
    static boolean winCheck(String[][] matrix, String XorO) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == XorO && matrix[i][1] == XorO && matrix[i][2] == XorO) {
                theWinnerIs(matrix, XorO);
                return true;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[0][i] == XorO && matrix[1][i] == XorO && matrix[2][i] == XorO) {
                theWinnerIs(matrix, XorO);
                return true;
            }
        }
        if (matrix[0][0] == XorO && matrix[1][1] == XorO && matrix[2][2] == XorO) {
            theWinnerIs(matrix, XorO);
            return true;
        }
        if (matrix[0][2] == XorO && matrix[1][1] == XorO && matrix[2][0] == XorO) {
            theWinnerIs(matrix, XorO);
            return true;
        }


        return false;
    }

    //פונקציה שמכריזה על המנצח
    static void theWinnerIs(String[][] matrix, String XorO) {
        System.out.println();
        if (XorO == "[X]"){
            System.out.println("the X is the winner!!!");
        }
        if (XorO == "[O]"){
            System.out.println("the O is the winner!!!");
        }
        printTheMatrix(matrix);
        System.out.println();
    }

    //פונקציה שמסיימת את המשחק ושואלת אם רוצים משחק חוזר
    static void endGame() {
        int leaveOrStay;

        System.out.println("to start new game enter 1");
        System.out.println("to exit enter Anything else");
        leaveOrStay = scanner.nextInt();

        if (leaveOrStay == 1) {
            startNewGame();
        } else {
            System.out.println("goodbye");
            System.exit(0);
        }

    }

}
