import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    
    // My version
    //public static Boolean cross = false;    // X
    //public static Boolean oe = false;       // O

        // Adam's example
        //private class row {
        //    char pos1;
        //    char pos2;
        //    char pos3;
        //}
        //private ArrayList <row> = 3; // ? check code in vid

    // Dav's version
    private static ArrayList<Character> board = new ArrayList<Character>();    // playing board
    private static int playersTurn = 1;    // stores whose turn it is

    private static boolean threeInARowCheck() {
        // figure out if 3 in a row
        return false;
    }

    public static void showBoard() {
        int pos = 0;
        for(int col = 0; col < 3; col++) {
            for(int row = 0; row < 3; row++) {
                if(pos == ' ') {
                    System.out.print(board.get(pos) + " ");
                } else {
                    System.out.print(board.get(pos) + " "); 
                }
                pos++;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // board of blanks, use to tell if spot is open for move
        for(int i = 0; i < 9; i++) {
            board.add(' '); 
        }

        // ability to ask user for move
        Scanner sc = new Scanner( System. in );

        Character marker = 'X';
        if(playersTurn == 2) {
            marker = 'O';
        }

        boolean done = false;
        boolean validMove = false; 
        while( validMove ) {
            System.out.println("What's your move?" + playersTurn);
            int pos = sc.nextInt();
            // 0 is upper left pos, 8 is lower right
            if(pos >= 0 && pos <= 8 && board.get(pos) == ' ') {
                board.set( pos, marker);
                validMove = true; 
                //break;    // cheater way to get out of loop
            } else {
                // User tried to play in an occupied square
                System.out.println("Position already occupied, try again.");
            }

        }
        if(threeInARowCheck() ) {
            System.out.print("You Win!");
            done = true;
        }
        showBoard();
        playersTurn += 1;
        if( playersTurn == 3) {
            playersTurn = 1;
        }
    }

}
