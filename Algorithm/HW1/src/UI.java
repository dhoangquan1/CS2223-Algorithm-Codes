import java.util.*;
import java.lang.Math;

/**
 * the class that deals with general printing to the terminal
 */
public class UI {
    //The string colors predefined
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[38;2;189;183;107m";
    public static final String ANSI_ORANGE = "\u001B[38;2;255;165;0m";
    public static final String ANSI_GREEN = "\u001B[38;2;0;255;0m";
    public static final String ANSI_RED = "\u001B[38;2;255;0;0m";

    private static String color_pallete[] = {ANSI_GREEN, ANSI_YELLOW, ANSI_ORANGE};

    /**
     * printing the intro to the game
     */
    public void printIntro(){
        System.out.println("=====================================================");
        System.out.println("                 Double Trouble Game                 ");
        System.out.println("=====================================================");
        System.out.println("");
        System.out.println(ANSI_BLUE + "To enter a move, input the color initial, follow by a number you wished to remove." + ANSI_RESET);
        System.out.println(ANSI_BLUE + "There should be no space between the letter and the number. Example: Y2" + ANSI_RESET);
        System.out.println("The colors are " + ANSI_GREEN + "green, " + ANSI_RESET +
                                                ANSI_YELLOW + "yellow, " + ANSI_RESET +
                                                ANSI_ORANGE + "orange, " + ANSI_RESET + " respectively on the board.");
        System.out.println("So you have three color options: G, Y, O");
        System.out.println("");
        System.out.println(ANSI_BLUE + "Who would you like to move first?" + ANSI_RESET);
        System.out.println("1) BOT ");
        System.out.println("2) PLAYER ");
    }

    /**
     * printing the result of the winner to the terminal
     * @param playerWon true if the player won the game
     */
    public void printResult(boolean playerWon){
        if(playerWon){
            System.out.println("=====================================================");
            System.out.println(ANSI_GREEN + "         Congratulation!!! You won the game!!!       " + ANSI_RESET);
            System.out.println("=====================================================");
        }else{
            System.out.println("=====================================================");
            System.out.println(ANSI_RED + "                Mwop Mwop!!! You lost!!!             " + ANSI_RESET);
            System.out.println("=====================================================");
        }
    }

    /**
     * print the current markers on the board
     * @param markers the markers' data
     */
    public void printBoard(int markers[]){
        System.out.println(ANSI_BLUE + "==================Current Markers=================="+ ANSI_RESET);
        System.out.println("");
        for(int i=0; i<3; i++){
            if(markers[i] != 0){
                printMarker(markers[i], color_pallete[i]);
            }else{
                System.out.println("");
            }
        }
        System.out.println(ANSI_BLUE +"===================================================="+ ANSI_RESET);
    }

    /**
     * print the row of markers based on their color
     * @param num number of markers to be printed
     * @param color the color of those markers
     */
    public static void printMarker(int num, String color){
        for(int j=0; j<num; j++){
            System.out.print(color + "  #  " + ANSI_RESET);
        }
        System.out.println("");
        for(int i=0; i<2; i++){
            for(int j=0; j<num; j++){
                System.out.print(color + " ||| " + ANSI_RESET);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * print what the bot did on its turn
     * @param moveset the move that the bot did
     */
    public void printBotMove(int moveset[]){
        if(moveset[0] == 0){
            System.out.println("The bot removed " + ANSI_GREEN + moveset[1] + " green" + ANSI_RESET + " markers from the board.");
        }
        if(moveset[0] == 1){
            System.out.println("The bot removed " + ANSI_YELLOW + moveset[1] + " yellow" + ANSI_RESET + " markers from the board.");
        }
        if(moveset[0] == 2){
            System.out.println("The bot removed " + ANSI_ORANGE + moveset[1] + " orange" + ANSI_RESET + " markers from the board.");
        }
    }
}
