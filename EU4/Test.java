package EU4;

import OU5.Punkt;

public class Test {
    public static void main(String[] args) {

        Punkt[] horn = {new Punkt("A", 10, 10)};

        NPolylinje polylinje = new NPolylinje(horn);

        System.out.println(polylinje);

        polylinje.laggTillFramfor(new Punkt("B", 5, 5), "A");

        polylinje.laggTill(new Punkt("C", 2, 3));
    
        /**
         *  node = polylinke.getNode();
         *  
         *  node.nastaNode.nastaNode.horn; => {C, 2, 3}
         * 
         */

        // NOD({b, 5, 5}, NOD({A, 10, 10}, NOD({C, 2, 3,}, NULL})))
    }
}
