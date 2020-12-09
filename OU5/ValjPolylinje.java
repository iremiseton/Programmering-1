package OU5;

import java.util.Random;

public class ValjPolylinje {
    public static final Random rand = new Random();
    public static final int ANTAL_POLYLINJER = 10;

    public static void main(String[] args) {
        Polylinje[] polylinjer = new Polylinje[ANTAL_POLYLINJER];
        for (int i = 0; i < ANTAL_POLYLINJER; i++) {
            polylinjer[i] = slumpPolylinje();
        }
    
        //Visas polylinjerna
        for (int i = 0; i < ANTAL_POLYLINJER; i++) {
            System.out.println(polylinjer[i]);
        }

        double kortast = Double.MAX_VALUE;
        int i = 0;
        int v = 0;
        while (i < ANTAL_POLYLINJER) {
            if (polylinjer[i].getFarg() == "gul") {
                if (kortast > polylinjer[i].langd()) {
                    kortast = polylinjer[i].langd();
                    v = i;
                }
            }
            i++;
        }
        System.out.println(kortast);

        System.out.println(polylinjer[v]);
    }

    public static Punkt slumpPunkt() {
        String n = "" + (char) (65 + rand.nextInt(26)); // A till Z
        int x = rand.nextInt(11);
        int y = rand.nextInt(11);

        return new Punkt(n, x, y);
    }
    
    public static Polylinje slumpPolylinje() {
        // skapa en tom polylinje, och lägg till hörn till den
        Polylinje polylinje = new Polylinje();
        int antalHorn = 2 + rand.nextInt(7); // Mellan 2 och 8 hörn
        int antalValdaHorn = 0;
        boolean[] valdaNamn = new boolean[26];
        // ett och samma namn kan inte förekomma flera gånger
        Punkt valdPunkt = null;
        char valtChar = 0;
        
        while (antalValdaHorn < antalHorn){
            
            while (true) {
                valdPunkt = slumpPunkt();
                valtChar = valdPunkt.getNamn().charAt(0);
                
                if (!valdaNamn[(int) valtChar - 65]) {
                    valdaNamn[(int) valtChar - 65] = true;
                    break;
                }
                
            }

            polylinje.laggTill(valdPunkt);
            
            antalValdaHorn++;
        }
        
        String[] farg = {"röd", "blå"};
        polylinje.setFarg(farg[rand.nextInt(2)]);

        return polylinje;
    }


    
    
}
