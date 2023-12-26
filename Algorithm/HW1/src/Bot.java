import java.lang.Math;

/**
 * The class that calculate and generate the bot moves
 */
public class Bot {
    /**
     * int[] move(): the general move of the bot, including random and winning strategy
     * @param markers the current markers board
     * @return the move that is made by the bot
     */
    public int[] move(int markers[]){
        int moveset[] = new int[2];
        //Check to see if can win with removing Green
        int move = winningMoveCalc(markers[0], markers[1], markers[2]);
        //If not, check to see if can win with removing Yellow
        if(move == -1){
            move = winningMoveCalc(markers[1], markers[0], markers[2]);
        }else{
            moveset[0] = 0;
            moveset[1] = move;
            return moveset;
        }
        //If not, check to see if can win with removing Orange
        if(move == -1){
            move = winningMoveCalc(markers[2], markers[0], markers[1]);
        }else{
            moveset[0] = 1;
            moveset[1] = move;
            return moveset;
        }
        //If not all three, do a random move
        if(move == -1) {
            return randomMove(markers);
        }

        moveset[0] = 2;
        moveset[1] = move;
        return moveset;
    }

    /**
     * int[] randomMove(): randomized move that can be made
     * @param markers the current markers board
     * @return the move that is made by the bot
     */
    public int[] randomMove(int markers[]){
        int color;
        //Randomize colors
        do {
            color = (int) (Math.random()*3);
        } while (markers[color] == 0 );
        //Randomize the pieces to be removed
        int move = 1 + (int)(Math.random() * markers[color]);

        int moveset[] = {color, move};
        return moveset;
    }

    /**
     * int winningMoveCalc(): calculate the winning strategy using XOR sum
     * @param c the number to check if any markers to be removed
     * @param b the first marker set that the bot will not remove from
     * @param a the second marker set that the bot will not remove from
     * @return the number of pieces to remove from the set to make a winning move, otherwise, return -1
     */
    public int winningMoveCalc(int c, int b, int a){
        if(c > (a^b)){
            for(int i=1; i<c+1; i++){
                if(((c-i)^a^b) == 0){
                    return i;
                }
            }
        }
        return -1;
    }
}
