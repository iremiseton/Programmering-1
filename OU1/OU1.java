package OU1;

import java.util.*;

class OU1 {

    static private double findMax(double[] array) {
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
        }

        return max;
    }

    static private double findMin(double[] array) {
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min)
                min = array[i];
        }

        return min;
    }

    static private double findSum(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++)
            sum += array[i];

        return sum;
    }

    static private double findAverage(double[] array) {
        return findSum(array) / array.length;
    }

    public static void main(String[] args) {
        int antalVeckor,
            antalMatningarPerVecka;

        double[][] t;
        double[] minT,
                 maxT,
                 sumT,
                 medelT;

        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        System.out.print("Mata in antalet veckor: ");
        antalVeckor = in.nextInt();

        System.out.print("Mata in antalet mätningar per vecka: ");
        antalMatningarPerVecka = in.nextInt();

        t = new double[antalVeckor][antalMatningarPerVecka];

        for (int vecka = 0; vecka < antalVeckor; vecka++) {
            System.out.println("Tempratur för vecka " + (vecka + 1) + ": ");

            for (int matning = 0; matning < antalMatningarPerVecka; matning++){
                t[vecka][matning] = in.nextDouble();
            }

            System.out.println();
        }

        in.close();
        System.out.println();

        for (int vecka = 0; vecka < antalVeckor; vecka++) {
            System.out.println("Vecka: " + (vecka + 1));
            for (int matning = 0; matning < antalMatningarPerVecka; matning++) {
                System.out.println(t[vecka][matning]);
            }
        }

        minT = new double[antalVeckor];
        maxT = new double[antalVeckor];
        sumT = new double[antalVeckor];
        medelT = new double[antalVeckor];

        for (int vecka = 0; vecka < antalVeckor; vecka++) {
            minT[vecka] = findMin(t[vecka]);
            maxT[vecka] = findMax(t[vecka]);
            sumT[vecka] = findSum(t[vecka]);
            medelT[vecka] = Math.round(findAverage(t[vecka]) * 100) / 100;  
        }
        
        System.out.println();

        for (int vecka = 0; vecka < antalVeckor; vecka++) {
            System.out.println("Minsta värdet för vecka " + (vecka + 1) + ": " + minT[vecka]);
            System.out.println("Största värdet för vecka " + (vecka + 1) + ": " + maxT[vecka]);
            System.out.println("Summa av alla värden för vecka " + (vecka + 1) + ": " + sumT[vecka]);
            System.out.println("Medel värdet för vecka " + (vecka + 1) + ": " + medelT[vecka]);
            
            System.out.println();
        }

        double minTemp,
                maxTemp,
                sumTemp,
                medelTemp;

        minTemp = findMin(minT);
        maxTemp = findMax(maxT);
        sumTemp = findSum(sumT);
        medelTemp = findSum(medelT) / (antalMatningarPerVecka * antalVeckor);

        System.out.println("Minsta världet av alla mätningar: " + minTemp);
        System.out.println("Största världet av alla mätningar: " + maxTemp);
        System.out.println("Summan av alla världen i veckorna: " + sumTemp);
        System.out.println("Medeltemperaturen för alla veckor: " + medelTemp);
    }
}