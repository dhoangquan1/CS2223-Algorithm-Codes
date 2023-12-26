import java.util.Scanner;

public class easyinversioncount {
    private static int invB = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("The program will continuously asking for input for easy testing.");
        while (true) {
            int n = 8;
            System.out.println("Enter the length of the list (enter 0 will result in default length 8, enter -1 to exit): ");
            int input = in.nextInt();

            if (input == -1) {
                break;
            } else if (input != 0) {
                n = input;
            }

            int[] arr = new int[n];
            System.out.println("Enter the series of number: ");
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }

            int bubbleArr[] = arr.clone();
            bubbleSort(bubbleArr.clone());
            System.out.println("Inversion value of Bubble Sort: " + invB);

            invB = 0;
            System.out.println();

        }
    }

    /**
     * Bubble sorting values and count inversion
     * @param arr the array contains values to sort
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    //Increment everytime a value is swapped (inversion)
                    invB++;
                }
            }
        }
    }
}