import java.util.Arrays;

public class MostPreciousPath {
    public static int[][] vault ={
            {96,33,44,98,75,68,99,84},
            {10,41,1,86,46,24,53,93},
            {83,97,94,27,65,51,30,7},
            {56,70,47,64,22,88,67,12},
            {91,11,77,48,13,71,92,15},
            {32,59,17,25,31,4,16,63},
            {79,5,14,23,78,37,40,74},
            {35,89,52,66,82,20,95,21},
    };
    //This value is for printing, help negate the referencing between two multi-dimensional arrays when cloning
    public static final int[][] vaultP = {
            {96,33,44,98,75,68,99,84},
            {10,41,1,86,46,24,53,93},
            {83,97,94,27,65,51,30,7},
            {56,70,47,64,22,88,67,12},
            {91,11,77,48,13,71,92,15},
            {32,59,17,25,31,4,16,63},
            {79,5,14,23,78,37,40,74},
            {35,89,52,66,82,20,95,21},
    };
    public static String[][] path = new String[8][8];
    public static int n = 8; //number of rows/columns
    public static int mostGems;
    public static String mostGemsPath;

    public static final String Gcolor = "\u001B[42m";
    public static final String RESET = "\u001B[0m";


    public static void main(String[] args){

        mostPreciousPath();
        initializeResult();
        printPath();

    }

    /**
     * print the results
     */
    public static void printPath(){
        System.out.println("The result:");
        System.out.print("      |");
        for(int i=0; i<n; i++){
            System.out.printf(" Vault %d |", i+1);
        }
        System.out.println();

        for(int i=n-1; i>=0; i--){
            System.out.printf("Row %d |", i+1);
            for(int j=0; j<n; j++){
                int cPath = Character.getNumericValue(mostGemsPath.charAt(i));
                if(cPath == j){
                    System.out.printf("%s   %3d   %s|", Gcolor, vaultP[i][j], RESET);
                }else{
                    System.out.printf("   %3d   |", vaultP[i][j]);
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.printf("Bilbo starts at square %c (Row 1, Vault %c)\n", mostGemsPath.charAt(0)+1, mostGemsPath.charAt(0)+1);
        System.out.printf("Bilbo collected %d gems in total\n", mostGems);
        System.out.printf("The secret Arkenstone is at Vault %c \n", mostGemsPath.charAt(7)+1);

    }

    /**
     * get the results from the modified vault values
     */
    public static void initializeResult(){
        mostGems = 0;
        for(int i=0; i<n; i++){
            if(mostGems < vault[7][i]){
                mostGems = vault[7][i];
                mostGemsPath = path[7][i];
            }
        }
    }

    /**
     * calculate the most precious path
     */
    public static void mostPreciousPath(){
        for(int i=1; i<n; i++){
            for(int j=0; j<n; j++){
                int max = 0;
                int maxIndex = -1;
                if(j-1>=0){
                    if(max < vault[i-1][j-1]){
                        max = vault[i-1][j-1];
                        maxIndex = j-1;
                    }
                }
                if(j+1<n){
                    if(max < vault[i-1][j+1]){
                        max = vault[i-1][j+1];
                        maxIndex = j+1;
                    }
                }
                if(max < vault[i-1][j]){
                    max = vault[i-1][j];
                    maxIndex = j;
                }
                vault[i][j] += max;
                if(i==1){
                    path[0][maxIndex] = Integer.toString(maxIndex);
                }
                path[i][j] = path[i-1][maxIndex] + j;
            }

        }
    }
}
