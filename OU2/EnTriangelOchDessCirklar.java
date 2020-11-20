package OU2;

import java.util.*;
import OU2.Triangel.*;

public class EnTriangelOchDessCirklar {
    public static void main(String[] args) {
        double a, b, c, s, inR, outR;

        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        System.out.print("Längd för A: ");
        a = in.nextDouble();
        System.out.println();

        System.out.print("Längd för B: ");
        b = in.nextDouble();
        System.out.println();

        System.out.print("Längd för C: ");
        c = in.nextDouble();
        System.out.println();

        inR = Triangel.calculateInnerRadius(a, b, c);
        outR = Triangel.calculateOuterRadius(a, b, c);

        System.out.println("");
        System.out.println("Inner Radius: " +  inR);
        System.out.println("Outer Radius: " + outR);

        in.close();
    }
}
