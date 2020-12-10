package EU4;

import java.util.Random;

import OU5.Punkt;

public class Test {
    public static void main(String[] args) {

        Polylinje polylinje = null;
        Polylinje[] polylinjer = new Polylinje[5];
        String[] farger = {"röd", "gul"};
        String farg;

        for (int i = 0; i < 5; i ++) {
            polylinje = new NPolylinje();
            polylinje.setFarg(farger[new Random().nextInt(2)]);
    
            polylinje.laggTill(new Punkt("C", new Random().nextInt(10) + 1, 3));
            polylinje.laggTill(new Punkt("D", new Random().nextInt(10) + 1, 3));
            polylinje.laggTill(new Punkt("E", new Random().nextInt(10) + 2, 1));

            polylinjer[i] = polylinje;
            System.out.println(polylinje + " Langd: " + polylinje.langd());
        }

        System.out.println("\n\n" + yellow(polylinjer));

    }

    public static Polylinje yellow(Polylinje[] polylinjer) {
        Polylinje kortaste = null;
        double k = Double.MAX_VALUE;

        for (int i = 0; i < polylinjer.length - 1; i++) {
            //
            if(polylinjer[i].getFarg() == "gul"){
                if(k > polylinjer[i].langd()){
                    kortaste = polylinjer[i];
                    k = polylinjer[i].langd();
                }
            }
        }

        return kortaste;
    }
}
