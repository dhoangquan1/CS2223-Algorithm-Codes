public class nQueens {

    public static int N = 0;
    public static int currentIndex = 0;
    public static int total = 0;
    public static int done = 0;
    public static int[] firstBoard;

    public static void main(String[] args){
        System.out.println("# Queens    |      Total Solutions      | First Solution");

       for(int i=4; i<36; i++){
           N = i;
           total = 0;
           currentIndex = 0;
           int[] boardT = new int[N];
           firstBoard = new int[N];

           if(i<=17){
               allLegalBoard(boardT);
           }else{
               firstLegalBoard(boardT);
           }
           printResult(boardT, N);
       }
    }
    public static void printResult(int[] board, int n){
        System.out.printf("n = %3d     |", n);
        System.out.printf("     %10d            |", total);
        for(int i: firstBoard){
            System.out.printf("%3d ", i);
        }
        System.out.println();
    }

    public static boolean isLegalPosition(int[] board, int n){
        for(int i=0; i<n; i++){
            if(board[i] != 0){
                for(int j=0; j<i; j++){
                    int distance = i-j;
                    if(board[i] == board[j]
                            || board[j] + distance == board[i]
                            || board[j] - distance == board[i]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void firstLegalBoard(int[] board){
        while(nextLegalPosition(board)){
            if(currentIndex==N){
                if(total==0){
                    firstBoard = board.clone();
                }
                total++;
                break;
            }
        }
    }

    public static void allLegalBoard(int[] board){
        while (nextLegalPosition(board)){
            if(currentIndex==N){
                if(total==0){
                    firstBoard = board.clone();
                }
                currentIndex=N-1;
                total++;
            }
        }
    }

    public static boolean nextLegalPosition(int[] board) {
        while(!successor(board,currentIndex)){
            currentIndex--;
            if(currentIndex==-1){
                return false;
            }
        }
        currentIndex++;

        return true;
    }

    public static boolean successor(int[] board, int n){
        int currentP = board[n];
        for(int i = 0; i < N-currentP; i++) {
            board[n]++;
            if(isLegalPosition(board, N)){
                return true;
            }
        }
        board[n] = 0;
        return false;
    }
}
