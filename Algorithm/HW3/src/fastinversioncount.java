import java.util.Scanner;

public class fastinversioncount {
    private static int invM = 0;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("The program will continuously asking for input for easy testing.");
        while(true){
            int n = 8;
            System.out.println("Enter the length of the list (enter 0 will result in default length 8, enter -1 to exit): ");
            int input = in.nextInt();

            if(input == -1){
                break;
            }else if(input != 0){
                n = input;
            }

            int[] arr = new int[n];
            System.out.println("Enter the series of number: ");
            for(int i=0; i<n; i++){
                arr[i] = in.nextInt();
            }

            int mergeArr[] = arr.clone();
            mergeSort(mergeArr, 0, mergeArr.length-1);
            System.out.println("Inversion value of Merge Sort: " + invM);

            invM = 0;
            System.out.println();

        }
    }

    /**
     * merging part of the MergeSort (micro)
     * @param arr the array to merge sort
     * @param l the left index
     * @param m the middle index
     * @param r the right index
     */
    public static void merge(int arr[], int l, int m, int r){
        int n1 = m-l+1;
        int n2 = r-m;

        int left[] = new int[n1];
        int right[] = new int[n2];

        for(int i=0; i<n1; i++){
            left[i] = arr[l+i];
        }
        for(int i=0; i<n2; i++){
            right[i] = arr[m+i+1];
        }

        int i=0, j=0, k=l;
        while(i<n1 && j<n2){
            if(left[i] < right[j]){
                arr[k] = left[i];
                i++;
            }else{
                arr[k] = right[j];
                //For every shift, we calculate how much the index is shifted
                //We only count if the right is smaller than left (avoid repeats)
                invM += (m+j+1)-k;
                j++;
            }
            k++;
        }

        while(i<n1){
            arr[k] = left[i];
            i++;
            k++;
        }
        while(j<n2){
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    /**
     * Merge Sort (macro) / for recursion and main call
     * @param arr the array contains values to merge
     * @param l the left index
     * @param r the right index
     */
    public static void mergeSort(int arr[], int l, int r){
        if(l<r){
            int m = l + (r-l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);
        }
    }
}
