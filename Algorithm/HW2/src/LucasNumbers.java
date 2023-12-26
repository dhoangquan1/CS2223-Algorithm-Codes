//Name: Quan Hoang Dinh - B23HW2
import java.util.*;
import java.lang.Math;

public class LucasNumbers {
    private static int seq[];
    private static long time[];
    private static float ratioT[];

    //The code will print the student's conditions first before asking for input
    //This will happened once, but the user's input can be asked many times.
    public static void main(String[] args){
        System.out.println("My own initial conditions for Lucas Number (n=40). Wait while it runs!");
        seq = new int[40];
        time = new long[40];
        ratioT = new float[40];
        seq[0] = 28;
        seq[1] = 2;
        fibonaci(39);
        printAllResults(40);

        Scanner in = new Scanner(System.in);
        System.out.println("The system will continuously asking for value n. Enter 0 to stop");

        //asking for continuous n so we don't have to reset to test
        while(true){
            System.out.print("Enter the value of n: ");
            int n = in.nextInt();
            if(n == 0){
                break;
            }
            seq = new int[n];
            time = new long[n];
            ratioT = new float[n];
            seq[0] = 2;
            seq[1] = 1;

            fibonaci(n-1);

            printAllResults(n);

        }
    }

    /**
     * creates the Lucas numbers sequence through recursive
     * @param n the total counts of the numbers
     * @return the sequence number at n
     */
    public static int fibonaci(int n){
        if(n>1){
            long start = System.nanoTime(); //I use nanoTime to avoid the 0.0 time of the other method
            seq[n] = fibonaci(n-1) + fibonaci(n-2);
            long end = System.nanoTime();
            time[n] = end - start;
        }
        return seq[n];
    }

    /**
     * print the successive Lucas Numbers ratios.
     * @param n the total counts of the numbers
     */
    public static void printNumberRatio(int n){
        System.out.print("[");
        for(int i=1; i<n-1; i++){
            System.out.print((float)seq[i]/(float)seq[i-1] + ", ");
        }
        System.out.print("]\n");
    }

    /**
     * print the time needed between successive number ratios.
     * @param n the total counts of the numbers
     */
    public static void printTimeRatio(int n){
        for(int i=1; i<n-1; i++){
            if(time[i-1] != 0){
                ratioT[i] = (float)time[i]/(float)time[i-1];
            }
        }
        System.out.println(Arrays.toString(ratioT));
    }

    /**
     * print all the results
     * @param n the total counts of Lucas Numbers
     */
    public static void printAllResults(int n){
        System.out.println("The Lucas Numbers Sequence:");
        System.out.println(Arrays.toString(seq));
        System.out.println();

        System.out.println("The Time (in nano-second) of Compute each Lucas Number: ");
        System.out.println(Arrays.toString(time));
        System.out.println();

        System.out.println("The Ratios of (L(n+1))/(L(n)): ");
        printNumberRatio(n);
        System.out.println();

        System.out.println("The Ratios of Time(L(n+1))/Time(L(n)) (in nano-second): ");
        printTimeRatio(n);
        System.out.println();
    }

}


