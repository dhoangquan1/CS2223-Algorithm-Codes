import java.util.*;
import java.lang.Thread;

/**
 * The main class to run the game
 */
public class Game{
    public static void main(String[] args) throws InterruptedException {
        //Setting initial conditions for the game
        int markers[] = {3, 7, 5};
        int left = 15;
        int botMoveSet[] = new int[2];
        int[] playerMoveSet = new int[2];
        boolean playerWon = true;

        //Declaring other classes to use their methods (to keep it clean)
        UI display = new UI();
        Bot bot = new Bot();
        Scanner in = new Scanner(System.in);

        //Displaying the start and pick who to move first
        display.printIntro();
        System.out.println("Enter a Number:");
        int first = in.nextInt();
        in.nextLine();

        //Pre-move the bot if the bot goes first
        if(first == 1){
            botMoveSet = bot.move(markers);
            updateMarkers(markers, botMoveSet);
            left -= botMoveSet[1];
            display.printBotMove(botMoveSet);
        }

        //The core of the game
        //It will loop between the player and the bot to play
        while(left > 0){
            display.printBoard(markers);
            System.out.println("Enter your move: ");

            //The player moves
            String move = in.nextLine();
            playerMoveSet = moveFormat(move);
            if(!validMoveCheck(markers, playerMoveSet)){
                System.out.println("Invalid move! Try again");
                continue;
            }

            //Update the board and the leftover markers
            updateMarkers(markers, playerMoveSet);
            left -= playerMoveSet[1];

            //Sleep to slow things down, making it easier for the eye
            //Instead of displaying everything right away
            display.printBoard(markers);
            Thread.sleep(1000);

            //If the player haven't won, the bot moves.
            if(left > 0){
                botMoveSet = bot.move(markers);
                updateMarkers(markers, botMoveSet);
                left -= botMoveSet[1];
                display.printBotMove(botMoveSet);
                //If the bot moved and immediately won
                if(left == 0){
                    playerWon = false;
                }
            }
        }

        display.printResult(playerWon);
    }

    /**
     * void updateMarkers(): update the markers array after each move set
     * @param markers the current marker board
     * @param moveset the move set that was made
     */
    public static void updateMarkers(int markers[], int moveset[]){
        markers[moveset[0]] -= moveset[1];
    }

    /**
     * boolean validMoveCheck(): check if the player have made a valid move
     * @param markers the current marker board
     * @param moveset the move set to validate
     * @return true if the move set is legal
     */
    public static boolean validMoveCheck(int markers[], int moveset[]){
        return markers[moveset[0]] >= moveset[1] && moveset[1] > 0;
    }

    /**
     * int[] moveFormat(): format the move inputted by the user into a move set
     * @param move the raw string inputted
     * @return the move set containing the color's position and numbers to remove
     */
    public static int[] moveFormat(String move){
        int moveset[] = new int[2];
        String input[] = move.split("");
        if(input[0].compareToIgnoreCase("G") == 0){
            moveset[0] = 0;
        }
        if(input[0].compareToIgnoreCase("Y") == 0){
            moveset[0] = 1;
        }
        if(input[0].compareToIgnoreCase("O") == 0){
            moveset[0] = 2;
        }
        moveset[1] = Integer.parseInt(input[1]);
        return moveset;
    }
}
