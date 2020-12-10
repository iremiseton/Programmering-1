package EU4;

import OU5.Punkt;

public class Test {
    public static void main(String[] args) {

        Polylinje polylinje = null;
        Polylinje[] polylinjer = new Polylinje[2];

        for (int i = 0; i < 2; i ++) {
            polylinje = new NPolylinje();
            //polylinje = new VPolylinje();
    
            polylinje.laggTill(new Punkt("C", i + 1, 3));
            polylinje.laggTill(new Punkt("D", i + 1, 3));
            polylinje.laggTill(new Punkt("E", i + 2, 1));

            System.out.println(polylinje);

            polylinjer[i] = polylinje;
        }


        System.out.println(yellow(polylinjer));
    }

    public static Polylinje yellow(Polylinje[] polylinjer) {
        Polylinje kortaste = null;
        double k = Double.MAX_VALUE;

        for (int i = 0; i < polylinjer.length; i++) {
            if (polylinjer[i].langd() < k) {
                kortaste = polylinjer[i];
            }
        }

        return kortaste;
    }
}
