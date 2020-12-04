package OU5;

import java.util.Arrays;

public class PolylinjeTest {
	public static void main(String[] args) {

		Punkt[] punktVektor = {new Punkt("A", 1, 1), new Punkt("C", 3, 3)};
        Polylinje p = new Polylinje(punktVektor);

        System.out.println(p.getHorn()[0].avstand(p.getHorn()[1]));
        
        System.out.println(p);
        System.out.println("Langd: " + p.langd());
        p.laggTillFramfor(new Punkt("B", 2, 2), "C");
        System.out.println(p);
        System.out.println("Langd: " + p.langd());
        p.laggTill(new Punkt("D", 1, 2));
        System.out.println(p);
        System.out.println("Langd: " + p.langd());  
        
        p.taBort("B");

        System.out.println(p);
		// Uppgift D - gå igenom en Polylinje
		// Gå fram med PolylinjeIterator
	}
}