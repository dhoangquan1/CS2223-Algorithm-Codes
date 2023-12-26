import java.util.Locale;
import java.util.Scanner;

public class palindromecheck {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("The program will continuously asking for input for easy testing.");
        System.out.println("The program is case insensitive, ignore white spaces and punctuations.");
        while(true){
            System.out.println("Enter a String (Enter -1 to end the program)");
            String input = in.nextLine();
            if(input.equals("-1")){
                break;
            }

            String cleanedS = clean(input);
            System.out.println("The input after formatting: " + cleanedS);
            String reversedS = reverse(cleanedS);
            System.out.println("The reversed string: " + reversedS);
            if(cleanedS.equals(reversedS)){
                System.out.println("This is a palindrome!");
            }else{
                System.out.println("This is not a palindrome!");
            }
            System.out.println();
        }
    }

    /**
     * Clean up the string by removing uppercase, punctuations, and spaces.
     * @param org the original string
     * @return the cleaned string
     */
    public static String clean(String org){
        String comp = org.toLowerCase();
        String temp = "";
        for(int i=0; i<comp.length(); i++){
            char c = comp.charAt(i);
            if((c>=48 && c<=57) || (c>=97 && c<=122)){
                temp += c;
            }
        }
        return temp;
    }

    /**
     * reverse the string
     * @param org the original string
     * @return the string in reversed order
     */
    public static String reverse(String org){
        String temp = "";
        for(int i=org.length()-1; i>=0; i--){
            temp += org.charAt(i);
        }
        return temp;
    }
}
