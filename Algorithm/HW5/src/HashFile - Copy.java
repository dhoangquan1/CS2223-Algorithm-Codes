import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class HashFile {

    public static String cleanedW = "";

    public static int C = 123;
    public static int m = 997;

    public static int n = 8;
    public static String[] ht = new String[n];

    //trivial variables to answer question 3
    public static int emptyStart = 0;
    public static int emptyEnd = 0;
    public static int clusterStart = 0;
    public static int clusterEnd = 0;
    public static int distinctHash = -1;
    public static String furthestWord = "";


    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("Input the file name (EX: DeclarationOfIndependence.txt): ");
        String filename = in.nextLine();
        System.out.println("Input the hash table size (EX: 997): ");
        int size = in.nextInt();

        n = size;
        ht = new String[n];

        Arrays.fill(ht,"-1");
        readFile(filename);
        printHashTable();
        System.out.println();
        printAllCheck();
    }

    /**
     * Clean up word and hash it
     * @param word the word needed to be hashed
     * @return the hash value
     */
    public static int hashWord(String word){
        StringBuilder sb = new StringBuilder();
        int h = 0;
        int s = word.length();

        for(int i=0; i<s; i++){
            int c = word.charAt(i);
            if((c>=65 && c<=90) || (c>=97 && c<=122) || c==39 || c==45){
                h = (c-1) % 10;
                sb.append((char)c);
            }
        }

        cleanedW = sb.toString();
        return h;
    }

    /**
     * read file and pass all other arguments to make hash table
     * @param filename the name of file
     * @throws IOException the error of not finding file
     */
    public static void readFile(String filename) throws IOException {
        try {
            FileReader file = new FileReader(filename);
            BufferedReader input = new BufferedReader(file);
            String currentLine = input.readLine();
            while (currentLine != null) {
                String[] words = currentLine.split("[ —]");
                for (String w : words) {
                    int h = hashWord(w);
                    if (!cleanedW.equals("")) {
                        putHashTable(h);
                    }
                }
                currentLine = input.readLine();
            }
        } catch (IOException e) {
            System.out.println("Your file was not found");
            throw e;
        }
    }

    /**
     * put the hash value and hashed word into the hash table
     * @param h the hash value
     */
    public static void putHashTable(int h){
        int index = h;
        while(!(ht[index].equals(cleanedW))){
            if(ht[index].equals("-1")){
                ht[index]=cleanedW;
            }else{
                if(index==n-1){
                    index=0;
                }else{
                    index++;
                }
            }
        }
    }

    /**
     * print out the hash table
     */
    public static void printHashTable(){
        System.out.println("--------------------------------------------------------");
        System.out.println("Hash Address|           Hashed Word        |  Hash Value");
        System.out.println("--------------------------------------------------------");
        for(int i=0; i<ht.length; i++){
            String cW= ht[i];
            int cH;
            if(cW.equals("-1")){
                cH = i;
            }else{
                cH = hashWord(cW);
            }
            System.out.printf("    %3d     |       %15s        |       %3d\n", i, cW, cH);
        }
    }

    //*******************************************************
    //
    //  From here on is trivial code to answer question 3
    //
    //*******************************************************

    public static int checkNonEmpty(){
        int count = 0;
        for(String s: ht){
            if(!s.equals("-1")){
                count++;
            }
        }
        return count;
    }

    public static int checkLongestEmpty(){
        int count = 0;
        int tempCount = 0;

        for(int i=0; i<ht.length; i++){
            String s = ht[i];
            if(s.equals("-1")){
                tempCount++;
            }else{
                if(tempCount > count){
                    count = tempCount;
                    emptyEnd = i-1;
                    emptyStart = i-count;
                }
                tempCount = 0;
            }
        }

        return count;
    }

    public static int checkLongestCluster(){
        int i = emptyEnd;
        int count = 0;
        int tempCount = 0;

        int tempStart = 0;

        while(i != emptyStart){
            if(i==n-1){
                i=0;
            }else{
                i++;
            }

            String s = ht[i];
            if(!s.equals("-1")){
                if(tempCount==0){
                    tempStart = i;
                }
                tempCount++;
            }else{
                if(tempCount>count){
                    count = tempCount;
                    clusterEnd = i-1;
                    clusterStart = tempStart;
                }
                tempCount=0;
            }
        }

        return count;
    }

    public static int checkGreatestDistinct(){
        int count = 0;
        int[] total = new int[n];

        for(String s: ht){
            if(!s.equals("-1")){
                int h = hashWord(s);
                total[h]++;
                if(total[h] > count){
                    count = total[h];
                    distinctHash = h;
                }
            }
        }

        return count;
    }

    public static int checkWordPlacedFurthest(){
        int count = 0;

        for(int i=0; i<ht.length; i++){
            String s = ht[i];
            if(!s.equals("-1")){
                int h = hashWord(s);
                int distance = Math.abs(h - i - n) % n;

                if(distance > count){
                    count = distance;
                    furthestWord = s;
                }
            }
        }

        return count;
    }

    public static int checkWrapAroundLenght(){
        int count = 0;
        if(!ht[0].equals("-1") && !ht[n-1].equals("-1")){
            int i = 0;
            while(!ht[i].equals("-1")){
                i++;
                count++;
            }
            i = n-1;
            while(!ht[i].equals("-1")){
                i--;
                count++;
            }
        }
        return count;
    }

    public static void printAllCheck(){
        int nonEmpty = checkNonEmpty();
        int longestEmpty = checkLongestEmpty();
        int longestCluster = checkLongestCluster();
        int distinct = checkGreatestDistinct();
        int furthest = checkWordPlacedFurthest();
        int wraparound = checkWrapAroundLenght();
        double loadFactor = (double)nonEmpty / (double)n;

        System.out.printf("Non empty addresses: %d \n", nonEmpty);
        System.out.printf("Load factor: %.5f \n", loadFactor);
        System.out.printf("From %d to %d has the longest empty area of %d \n", emptyStart,emptyEnd,longestEmpty);
        System.out.printf("From %d to %d has the longest cluster area of %d \n", clusterStart, clusterEnd, longestCluster);
        System.out.printf("Hash value %d has the greatest number of distinct words of %d \n", distinctHash, distinct);
        System.out.printf("The word '%s' is placed farthest from its actual hash value, distance of %d \n", furthestWord, furthest);
        System.out.printf("Bonus: Wrap around length: %d \n", wraparound);
    }
}
