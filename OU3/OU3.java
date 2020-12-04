package OU3;

import java.util.*;

public class OU3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        int m, n;
        double[] a, c;
        double[][] b;

        System.out.println("m n: ");
        m = in.nextInt();
        n = in.nextInt();

        a = new double[m];
        c = new double[n];
        b = new double[m][n];
        
        System.out.println("Längd till alla från Z1 till Z2");
        for (int i = 0; i < m; i++) {
            System.out.print("a" + (i + 1) + ": ");
            a[i] = in.nextDouble();
        }

        System.out.println("Längd till alla från Z2 till Z3");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("b" + (i + 1) + "" + (j + 1)+ ": ");
                b[i][j] = in.nextDouble();
            }
        }

        System.out.println("Längd till alla från Z3 till Z4");
        for (int j = 0; j < n; j++) {
            System.out.print("c" + (j+ 1) + ": " );
            c[j] = in.nextDouble();
        }
        
        try {
            double langd = DenKortasteVagen.langd(a, b, c);
            int[] result = DenKortasteVagen.mellanstationer(a, b, c);
            System.out.println(result[0] + " " + result[1] + ": " + langd);
        } catch(Exception e) {
            System.out.println(e);
        }
        in.close();
    } 
}

class DenKortasteVagen {

    public static int[] mellanstationer(double[] a, double[][] b, double[] c) throws Exception {
        double len = a[0] + b[0][0] + c[0];
        int[] result = {0, 0};
        
        if (a.length < 1 || b.length < 1 || c.length < 1)
            throw new Exception("Alla listor måste ha värden i sig.");

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (a[i] + b[i][j] + c[j] < len) {
                    len = a[i] + b[i][j] + c[j];
                    result[0] = i;
                    result[1] = j; 
                }
            }
        }
        
        return result;
    }

    public static double langd(double[] a, double[][] b, double[] c) throws Exception {
        try {
            int[] result = mellanstationer(a, b, c);
            return a[result[0]] + b[result[0]][result[1]] + c[result[1]];
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
