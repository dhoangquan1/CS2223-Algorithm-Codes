import java.util.*;
public class BRGC {
    public static String[] members = {"Gomer", "Fleek", "Elmer", "Dietz", "Crizz", "Berty", "Alfie"};

    public static void main(String[] args){
        ArrayList<String> bitsBRGC = new ArrayList<>();

        makeBRGC(7, bitsBRGC);
        printResult(bitsBRGC);

    }

    /**
     * make the BRGC
     * @param n the length of bits
     * @param subsets the set for recursion
     * @return the ArrayList contains the BRGC
     */
    public static ArrayList<String> makeBRGC(int n, ArrayList<String> subsets) {
        if (n == 1) {
            subsets.add("0");
            subsets.add("1");
        } else {
            makeBRGC(n - 1, subsets);
            ArrayList<String> rSubsets = (ArrayList)subsets.clone();
            Collections.reverse(rSubsets);
            appendBit("0", subsets);
            appendBit("1", rSubsets);
            subsets.addAll(rSubsets);
        }
        return subsets;
    }

    /**
     * append 0 or 1 to each bit of the set
     * @param bit the 0 or 1 string
     * @param s the set to modify
     */
    public static void appendBit(String bit, ArrayList<String> s){
        for(int i=0; i<s.size(); i++){
            String temp = bit + s.get(i);
            s.set(i,temp);
        }
    }

    /**
     * print out the table
     * @param bits the BRGC
     */
    public static void printResult(ArrayList<String> bits){
        System.out.println("|   Index   |    Gray Code   |                    Players Playing                  |       Action      |");
        System.out.println("|          0|         0000000|                                         SILENT STAGE|                   |");
        for(int i=1; i<bits.size(); i++){
            System.out.printf("|%11d|",i);

            String pbit = bits.get(i-1);
            String nbit = bits.get(i);
            String players = "";
            String action = "";

            System.out.printf("%16s|", nbit);
            for(int j=0; j<nbit.length(); j++){
                if(nbit.charAt(j) == '1'){
                    if(!players.equals("")){
                        players += " & ";
                    }
                    players += members[j];
                }
                if(pbit.charAt(j) > nbit.charAt(j)){
                    action = members[j] + " Fades";
                }
                if(pbit.charAt(j) < nbit.charAt(j)){
                    action = members[j] + " Joins";
                }
            }

            System.out.printf("%53s|", players);
            System.out.printf("%19s|\n", action);
        }
    }

}
