package OU4;

import java.util.*;

public class OU4 {
    public static void main(String[] args) {

        String tal1, 
               tal2, 
               summa,
               diff;

        Scanner in = new Scanner(System.in);
        System.out.println("Två naturliga heltal:");
        

        tal1 = in.next();
        tal2 = in.next();

        in.close();

        System.out.println();

        diff = subtrahera(tal1, tal2);
        visa(tal1, tal2, diff, '-');

        System.out.println();

        summa = addera(tal1, tal2);
        visa(tal1, tal2, summa, '+');

    }
    public static void visa (String tal1, String tal2,String resultat, char operator) {

        // sätt en lämplig längd på heltalen och resultatet   
        int len1 = tal1.length ();
        int    len2 = tal2.length ();
        int    len  = resultat.length ();
        int    maxLen = Math.max (Math.max (len1, len2), len);
        tal1 = sattLen (tal1, maxLen -len1);
        tal2 = sattLen (tal2, maxLen -len2);
        resultat = sattLen (resultat, maxLen -len);

        // visa heltalen och resultatet
        System.out.println ("  " + tal1);
        System.out.println ("" + operator + " " + tal2);
        for (int i = 0; i < maxLen + 2; i++)
            System.out.print ("-");
        System.out.println ();
        System.out.println ("  " + resultat + "\n");
    }

    public static String sattLen(String s, int antal) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < antal; i++) {
            sb.insert(0, " ");
        }

        return sb.toString();
    }


    public static String subtrahera(String tal1, String tal2) {

        int maxLength;
        String zeros = "";
        int taker = 0;
        int s, t1, t2;
        StringBuilder dif = new StringBuilder("");
        
        if (tal1.length() > tal2.length()) {
            maxLength = tal1.length();

            for (int i = 0; i < (tal1.length() - tal2.length()); i++) {
                zeros = '0' + zeros;
            }

            tal2 = zeros + tal2;
        } else { 
            maxLength = tal2.length(); 

            for (int i = 0; i < (tal2.length() - tal1.length()); i++) {
                zeros = '0' + zeros;
            }

            tal1 = zeros + tal1;
        }

        for (int i = maxLength - 1; i >= 0; i--) {
            t1 = tal1.charAt(i);
            t2 = tal2.charAt(i);
            
            // 101
            //  99

            if (taker == -1) {
                t1 += taker;
            }

            if (t2 > t1) {
                taker = -1;

                s = (t1 + 10 - t2 + 48);
            } else {
                taker = 0;

                s = (t1 - t2 + 48);
            }

            dif.insert(0, (char)s);
        }

        while (dif.charAt(0) == '0') {
            dif.deleteCharAt(0);
        }

        return dif.toString();
    }


    public static String addera(String tal1, String tal2) {

        int maxLength;
        StringBuilder sum = new StringBuilder("");
        int t1, t2, s;
        int carry = 0;
        int left;
        int max, min;
        String zeros = "";
        
        if (tal1.length() > tal2.length()) {
            maxLength = tal1.length();

            for (int i = 0; i < (tal1.length() - tal2.length()); i++) {
                zeros = '0' + zeros;
            }

            tal2 = zeros + tal2;
        } else { 
            maxLength = tal2.length(); 

            for (int i = 0; i < (tal2.length() - tal1.length()); i++) {
                zeros = '0' + zeros;
            }

            tal1 = zeros + tal1;
        }


        for (int i = maxLength - 1; i >= 0; i--) {

            t1 = tal1.charAt(i);
            t2 = tal2.charAt(i);


            if (carry == 1) {
                s = (t1 + t2 - 48 + 1);
            } else {
                s = (t1 + t2 - 48);
            }

            if (s > 57) {
                if (t1 > t2) { max = t1; min = t2; }
                else { max = t2; min = t1; }


                // 9 + 3 => 1 2
                // 3 - (10 - 9) => 2

                if (carry == 1) {
                    left = (min - (58 - max)) + 1;
                } else {
                    left = min - (58 - max);
                }
                sum.insert(0, (char)left);
                carry = 1;
            } else {
                carry = 0;

                sum.insert(0, (char)s); 
            }
        }

        if (carry == 1) {
            sum.insert(0, carry);
        }

        return sum.toString();

    }
}
