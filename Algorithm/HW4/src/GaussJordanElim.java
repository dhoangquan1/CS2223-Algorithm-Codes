public class GaussJordanElim {

     public static double[][] A = {
     {1,1,1,1,1,1,1,1,1,0},
     {1,1,1,1,1,-1,-1,-1,-1,0},
     {1,-1,1,-1,1,-1,1,-1,1,0},
     {1,1,0,0,0,0,0,0,0,0},
     {0,0,1,1,0,0,0,0,0,0},
     {0,0,0,0,1,1,0,0,0,0},
     {0,0,0,0,0,0,0,1,1,0},
     {9,-8,7,-6,5,-4,3,-2,1,0},
     {1,1,-1,1,1,-1,1,1,-1,0}};
     public static double[] B = {122,-88,32,3,7,18,76,41,0};
     public static int n = B.length;

     //Example of an inconsistent system if you want to check
     /**
    public static double[][] A = {
            {1,1,1,0},
            {1,2,1,0},
            {1,1,1,0}
    };
    public static double[] B = {1,4,2};
    public static int n = B.length;
      */

     //Example of a redundant system if you want to check
    /**
     public static double[][] A = {
             {1,2,2,0},
             {1,-1,3,0},
             {4,-1,11,0}
     };
    public static double[] B = {11,8,35};
    public static int n = B.length;
     */

    public static void main(String[] args) {

        GJE();

        for (double[] C : A) {
            for (double d : C) {
                System.out.printf("%3d ", (int)Math.round(d));
            }
            System.out.println();
        }
    }

    /**
     * Calculate the Gauss-Jordan Elimination
     */
    public static void GJE() {
        for (int i = 0; i < n; i++) {
            A[i][n] = B[i];
        }
        for (int i = 0; i < n; i++) {
            int pivotrow = i;

            for (int j = i + 1; j < n; j++) {
                if (Math.abs(A[j][i]) > Math.abs(A[pivotrow][i])) {
                    pivotrow = j;
                }
            }
            for (int k = i; k < n + 1; k++) {
                double temp = A[i][k];
                A[i][k] = A[pivotrow][k];
                A[pivotrow][k] = temp;
            }

            double pivotNum = A[i][i];
            for (int p=0; p<=n; p++){
                A[i][p] /= pivotNum;
            }

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double temp = A[j][i] / A[i][i];
                    for (int k = 0; k < n+1; k++) {
                        A[j][k] -= A[i][k] * temp;
                    }
                }
            }
            if(RICheck()){
                break;
            }
        }
    }

    /**
     * check for inconsistency and redundant, and print it into terminal
     */
    public static boolean RICheck(){
        boolean error = true;
        for(int i=0; i<n; i++){
            error = true;
            for(int j=0; j<n; j++){
                if(Math.round(A[i][j]) != 0){
                    error = false;
                }
            }
            if(error){
                if(Math.round(A[i][n]) != 0){
                    System.out.println("The system is inconsistent.");
                }else{
                    System.out.println("The system is redundant.");
                }
                break;
            }
        }
        return error;
    }
}