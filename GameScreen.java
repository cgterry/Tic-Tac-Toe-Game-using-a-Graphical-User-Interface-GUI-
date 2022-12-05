package cpsc2150.extendedTicTacToe;

import java.util.*;
import cpsc2150.extendedTicTacToe.models.*;

import static java.lang.System.exit;

public class GameScreen {

    public static void main(String[] args) {

        // variable to help user play more than one game
        int userPlayAgain2 = 0;

        // until user ends the game and quits the program
        while(userPlayAgain2 != 1) {

            // Helper variables and input scanner
            int numPlayers;
            int numRows;
            int numCols;
            int numToWin;
            int trueWinner = 0;
            int trueTie = 0;
            String typeGame1;
            char typeGame2;
            char[] players;
            boolean check = false;
            boolean winner = false;
            boolean tie = false;
            Scanner input = new Scanner(System.in);

            System.out.println("How many players?");
            numPlayers = input.nextInt();

            // User validation
            if (numPlayers > 10) {

                while (numPlayers > 10) {
                    System.out.println("Must be 10 players or fewer");
                    System.out.println("How many players?");
                    numPlayers = input.nextInt();
                }
            }
            if (numPlayers < 2) {

                while (numPlayers < 2) {
                    System.out.println("Must be at least 2 players");
                    System.out.println("How many players?");
                    numPlayers = input.nextInt();
                }
            }

            players = new char[numPlayers];

            // Collects player's characters and stores them in a char array(players)
            for (int i = 0; i < numPlayers; i++) {

                System.out.print("Enter the character to represent player ");
                System.out.print(i + 1);
                System.out.print("\n");
                String player = input.next();
                String uCasePlayer = player.toUpperCase();
                players[i] = uCasePlayer.charAt(0);

                // User validation
                if (i >= 1) {
                    for (int j = 0; j < i; j++) {
                        if (players[i] == players[j]) {
                            while (players[i] == players[j]) {
                                System.out.print(players[i]);
                                System.out.print(" is already taken as a player token!");
                                System.out.print("\n");

                                System.out.print("Enter the character to represent player ");
                                System.out.print(i + 1);
                                System.out.print("\n");
                                player = input.next();
                                uCasePlayer = player.toUpperCase();
                                players[i] = uCasePlayer.charAt(0);
                            }
                        }
                    }
                }

            }

            System.out.println("How many rows?");
            numRows = input.nextInt();

            // User validation
            if (numRows < 3 || numRows > 100) {

                while (numRows < 3 || numRows > 100) {
                    System.out.println("Rows must be between 3 and 100");
                    System.out.println("How many rows?");
                    numRows = input.nextInt();
                }
            }

            System.out.println("How many columns?");
            numCols = input.nextInt();

            // User validation
            if (numCols < 3 || numCols > 100) {

                while (numCols < 3 || numCols > 100) {
                    System.out.println("Columns must be between 3 and 100");
                    System.out.println("How many columns?");
                    numCols = input.nextInt();
                }
            }

            System.out.println("How many in a row to win? ");
            numToWin = input.nextInt();

            // User validation
            if (numToWin > numRows || numToWin > numCols) {
                while (numToWin > numRows || numToWin > numCols) {
                    System.out.println("How many in a row to win? ");
                    numToWin = input.nextInt();
                }
            }


            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            typeGame1 = input.next();
            typeGame2 = typeGame1.charAt(0);

            // User validation
            if (typeGame2 == 'f' || typeGame2 == 'F' || typeGame2 == 'M' || typeGame2 == 'm') {
            }
            else {

                System.out.println("Please enter F or M");
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                typeGame1 = input.next();
                typeGame2 = typeGame1.charAt(0);
            }


            // GameBoard Variable and its constructors/
            // implementations based on the user choice
            IGameBoard game = null;

            if (typeGame2 == 'f' || typeGame2 == 'F') {
                game = new GameBoard(numRows, numCols, numToWin);
            }
            else {
                game = new GameBoardMem(numRows, numCols, numToWin);
            }

            String output = game.toString();

            System.out.println(output);


            // Runs until there is a winner on the board
            if ((trueWinner < 1) || (trueTie < 1)) {
                while ((trueWinner < 1)) {
                    for (int i = 0; i < numPlayers; i++) {

                        check = false;
                        char currPlayer = players[i];

                        // while curr player's turn
                        while (check == false) {
                            System.out.println("Player " + players[i] + " Please enter your ROW");
                            int rowReader;
                            rowReader = input.nextInt();

                            System.out.println("Player " + players[i] + " Please enter your COLUMN");
                            int colReader;
                            colReader = input.nextInt();

                            BoardPosition temp;
                            temp = new BoardPosition(rowReader, colReader);

                            check = game.checkSpace(temp);

                            // if space is available
                            if (check == true) {

                                game.placeMarker(temp, currPlayer);

                                winner = game.checkForWinner(temp);
                                tie = game.checkForDraw();
                                if (winner == true) {
                                    System.out.println("Player " + players[i] + " wins!");
                                }
                                if (tie == true) {
                                    System.out.println("Draw!");
                                }
                            } else {
                                System.out.println("That space is unavailable, please pick again");
                            }
                            output = game.toString();
                            System.out.println(output);
                        }

                        if (winner == true) {
                            trueWinner = 1;
                            break;
                        }
                        if (tie == true) {
                            trueTie = 1;
                            trueWinner = 1;
                            break;
                        }
                    }
                }
            }
            // if the user wants to play again or end program
            System.out.println("Would you like to play again? Y/N");
            String playAgain = input.next();
            String uPlayAgain = playAgain.toUpperCase();
            char userPlayAgain = uPlayAgain.charAt(0);
            if (userPlayAgain == 'N' || userPlayAgain == 'n') {
                userPlayAgain2 = 1;
            }
        }
    }
}