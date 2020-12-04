package OU5;

import java.io.*;

public class PunktTest {
    public static void main(String[] args) {
        PrintWriter out =  new PrintWriter(System.out, true);

        Punkt p1 = new Punkt("A", 3, 4);
        Punkt p2 = new Punkt("B", 5, 6);
        System.out.println(p1 + " " + p2);
        
        String n = p1.getNamn();
        int x = p1.getX();
        int y = p1.getY();
        System.out.println(n + " " + x + " " + y);
        
        double d = p1.avstand(p2);
        System.out.println(d);
        boolean b = p1.equals(p2);
        System.out.println(b);

        p2.setX(1);
        p2.setY(2);
        System.out.println(p2);
        
        Punkt p = new Punkt(p1);
        System.out.println(p);
    }
}
