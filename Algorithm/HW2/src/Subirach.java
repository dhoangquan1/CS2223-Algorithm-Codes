//Name: Quan Hoang Dinh - B23HW2
import java.util.*;
import java.lang.Math;
import java.io.FileWriter;
import java.io.IOException;

public class Subirach {
    //The variables that will be used all throughout the class
    //I make them global for easy access across methods
    private static int square[] = {1,14,14,4,
                                    11,7,6,9,
                                    8,10,10,5,
                                    13,2,3,15};
    private static ArrayList<ArrayList<Integer>> allSubSets = new ArrayList<>();
    private static HashMap<Integer,Integer> sumCount = new HashMap<>();
    private static int mostSum = 0;
    private static int mostCount = 0;

    public static void main(String[] args){
        int count4Combos = find4ComboOf33();
        System.out.println("There are " + count4Combos + " combinations of 4-elements that have a sum of 33.");
        System.out.println("The combinations are printed to 4-combos-of-sum-33.txt");
        System.out.println();

        allSubSets = makeAllSubsets();
        int countAllCombo33 = findAllComboOf33();
        System.out.println("There are " + countAllCombo33 + " combinations total that have a sum of 33.");
        System.out.println("The combinations are printed to all-combos-of-sum-33.txt");
        System.out.println();

        findAllComboOfAllSum();
        printAllSumsResults();
        System.out.println();

        findGreatestCombination();
        System.out.println("The sum " + mostSum + " has the greatest combination counts of " + mostCount );
    }

    /**
     * find the counts of combinations of 4-elements that sum up to 33.
     * the method also record the combinations in a text file
     * @return the total counts
     */
    public static int find4ComboOf33(){
        int count = 0;
        try{
            FileWriter combos = new FileWriter("4-combos-of-sum-33.txt");
            for(int a=0; a<16; a++){
                for(int b=a+1; b<16; b++){
                    for(int c=b+1; c<16; c++){
                        for(int d=c+1; d<16; d++){
                            if(square[a] + square[b] + square[c] + square[d] == 33){
                                count++;
                                combos.append(square[a] +" "+ square[b] +" "+ square[c] +" "+ square[d] +"\n");
                            }
                        }
                    }
                }
            }
            combos.close();
        }catch(IOException e){
            System.out.println("An error occur with find4GeneralCombo method.");
        }
        return count;
    }

    /**
     * make the Subirach Square into all subsets
     * @return the ArrayList contains all the subsets as Arraylist of Integers.
     */
    public static ArrayList<ArrayList<Integer>> makeAllSubsets(){
        ArrayList<ArrayList<Integer>> subsets = new ArrayList<>();
        ArrayList<Integer> emt = new ArrayList<>();
        emt.add(0);
        subsets.add(emt);
        for(int i: square){
            int size = subsets.size();
            for(int a=0; a<size; a++){
                ArrayList<Integer> temp = (ArrayList<Integer>) subsets.get(a).clone();
                temp.add(i);
                subsets.add(temp);
            }
        }
        return subsets;
    }

    /**
     * find all the combinations that sum up to 33
     * @return the total combination counts
     */
    public static int findAllComboOf33(){
        int count = 0;
        try{
            FileWriter output = new FileWriter("all-combos-of-sum-33.txt");
            for(ArrayList<Integer> subset: allSubSets){
                int sum = 0;
                for(Integer i: subset){
                    sum += i;
                }
                if(sum==33){
                    count++;
                    output.append(subset.toString() + "\n");
                }
            }
            output.close();
        }catch (IOException e){
            System.out.println("An error occur with findAllComboOf33 method.");
        }
        return count;
    }

    /**
     * find all the sum of all combinations
     * the result is added to a Hashmap
     * the key is the sum
     * the value is the total counts
     */
    public static void findAllComboOfAllSum(){
        for(ArrayList<Integer> subset: allSubSets){
            int sum = 0;
            for(Integer i: subset){
                sum += i;
            }
            sumCount.merge(sum, 1, Integer::sum);
        }
    }

    /**
     * format print all the combination counts for all the sums
     */
    public static void printAllSumsResults(){
        System.out.println("All Sum and All Combination Results");
        for(Integer k: sumCount.keySet()){
            System.out.printf("%-5d occurs %d times\n", k, sumCount.get(k));
        }
    }

    /**
     * find the greatest number of combinations through the Hashmap
     */
    public static void findGreatestCombination(){
        for(Integer k: sumCount.keySet()){
            int count = sumCount.get(k);
            if(count > mostCount){
                mostCount = count;
                mostSum = k;
            }
        }
    }
}
