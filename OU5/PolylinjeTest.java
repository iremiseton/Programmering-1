package OU5;

import java.util.Arrays;

public class PolylinjeTest {
	public static void main(String[] args) {

		Punkt[] punktVektor = {};
        Polylinje p = new Polylinje(punktVektor);
        
        PolylinjeIterator iterator = new PolylinjeIterator(p);

        while (iterator.finnsHorn()) {
            System.out.println(iterator.horn());
            iterator.gaFram();
        }

        //System.out.println(p);
        //System.out.println("1 Langd: " + p.langd());


        //p.laggTillFramfor(new Punkt("B", 3, 2), "C");
        //System.out.println(p);
        //System.out.println("2 Langd: " + p.langd());

        //p.laggTill(new Punkt("D", 1, 2));
        //System.out.println(p);
        //System.out.println("3 Langd: " + p.langd());  
        
        //p.taBort("D");

        System.out.println(p);
		// Uppgift D - gå igenom en Polylinje
		// Gå fram med PolylinjeIterator
	}
}



// 