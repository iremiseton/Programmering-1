package OU1;

import java.util.Locale;
import java.util.Scanner;

public class Temperaturer {    
    public static void main (String[] args) {

        // inmatningsverktyg
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        // mata in uppgifter om antalet veckor och antalet mätningar
        System.out.print("antalet veckor: ");
        int antalVeckor = in.nextInt();
        System.out.print("antalet mätnigar per vecka");
        int antalMatningarPerVecka = in.nextInt();

        //plats att lagra temperaturer
        double[][] t = new double[antalVeckor + 1][antalMatningarPerVecka + 1];

        //Mata in temperaturerna
        for (int vecka = 1; vecka <= antalVeckor; vecka++) {
            System.out.println("temperaturer - vecka " + vecka + ":");
            for (int matning = 1; matning <= antalMatningarPerVecka; matning++) 
                t[vecka][matning] = in.nextDouble();
        }
        System.out.println();

        //visa temperaturerna
        System.out.println("temperaturerna:");
        for (int vecka = 1; vecka <= antalVeckor; vecka++) {
            for (int matning = 1; matning <= antalMatningarPerVecka; matning++) 
                System.out.print(t[vecka][matning] + " ");
            System.out.println();
        }
        System.out.println(); 

        //den minsta, den största och medeltemperaturen - veckovis
        double[] minT = new double[antalVeckor + 1];
        double[] maxT = new double[antalVeckor + 1];
        double[] sumT = new double[antalVeckor + 1];
        double[] medelT = new double[antalVeckor + 1];
        
    }
}
