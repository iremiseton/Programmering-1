package OU4;

import java.util.*;

public class OU4 {
    public static void main(String[] args) {

        // 0 - 9

        // 5 + 5 => 10

        /*
            1
            123
            192
            ---
             1   
            sum = 1
            carry = null;

            "315"

         *  8 a
         *  7 b
         * 
         *  
        */

        //57 = 9

        //48 = 0
        //49 = 1 

        //left = b - (58 - a)

        /**
         *  8 + 7
         *  > 10
         * 
         *  (10 - 8) => 2
         * 
         *  7 - 2 => 5
         * 
         *  15
         * 
         *  
         */
        //8 + 7 => 15'

        String tal1, 
               tal2, 
               summa;

        Scanner in = new Scanner(System.in);
        System.out.println("Två naturliga heltal:");
        
        tal1 = in.next();
        tal2 = in.next();

        System.out.println();

        summa = addera(tal1, tal2);
        System.out.print(summa);
        //visa(tal1, tal2, summa, "+");

    }

    /*

    string namn = "Harry"; => characters
    string tal1 = "123";
    string tal2 = "123";

    tal1 + tal2 => "123123";



    string summa => tal1 + tal2;
    int namn = "harry"; => integers

    */

    public static String addera(String tal1, String tal2) {

        /**
         *  9
         *  8
         *  ----
         *  8 - (9 - 8) = 7
         * 
         * 
         *  8
         *  7
         * 
         *  left = 7 - (10 - maxtalet)
         * 
         * 
         *  8
         *  5
         *  -----
         * 
         *  5 - (8 - 5) = 2
         * 
         *  17
         */

        int maxLength;
        String max;
        String min;
        StringBuilder sum = new StringBuilder("");

        if (tal1.length() > tal2.length()) {
            maxLength = tal1.length();

            String zeros = "";

            System.out.println(tal1.length() - tal2.length());
            for (int i = 0; i < (tal1.length() - tal2.length()); i++) {
                zeros = "0" + zeros;
            }

            tal2 = zeros + tal2;
            max = tal1;
            min = tal2;
        } else { 
            maxLength = tal2.length(); 

            String zeros = "";

            System.out.println(tal2.length() - tal1.length());
            for (int i = 0; i < (tal2.length() - tal1.length()); i++) {
                zeros = "0" + zeros;
            }

            tal1 = zeros + tal1;
            max = tal2;
            min = tal1;
        }

        int carry = 0;
        String s;
        for (int i = maxLength - 1; i >= 0; i--) {

            s = (tal1.charAt(i) + tal2.charAt(i) - '0');

            sum.insert(0, s);
        }

        return sum.toString();
    }
}
