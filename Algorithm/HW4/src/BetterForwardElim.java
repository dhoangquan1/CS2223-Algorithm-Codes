//This code is used to check for question 2
public class BetterForwardElim {
    public static double[][] A = {
            {1,1,1,0},
            {1,1,2,0},
            {2,2,3,0}};
    public static double[] B = {6,9,15};
    public static int n = B.length;

    public static void main(String[] args) {

        BFE();

        for (double[] C : A) {
            for (double d : C) {
                System.out.printf("%4f ", d);
            }
            System.out.println();
        }
    }

    /**
     * Calculate the Better Forward Elimination
     */
    public static void BFE() {
        for (int i = 0; i < n; i++) {
            A[i][n] = B[i];
        }
        for (int i = 0; i < n-1; i++) {
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

            for (int j = i+1; j < n; j++) {
                double temp = A[j][i] / A[i][i];
                for (int k = i; k < n+1; k++) {
                    A[j][k] -= A[i][k] * temp;
                }
            }
        }
    }
}