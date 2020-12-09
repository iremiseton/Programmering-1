package EU4;

import OU5.Punkt;

public class NPolylinje implements Polylinje {
    
    private static class Nod { 

        public Punkt horn;    
        public Nod nastaNod;

        public Nod(Punkt horn) {
            this.horn = horn;
            this.nastaNod = null;
        }

    }

    private Nod horn;
    private String farg = "svart";
    private int bredd = 1;  // pixlar

    public NPolylinje() {
        this.horn = null;
    }

    // [{a, 1, 2}, {b, 3, 1}, {c, 2, 1}, {d, 1, 2}]
    /**
     * NOD({a, 1, 2}, NOD({b, 3, 1}, NOD({c, 2, 1}, NOD({d, 1, 2}, null))))
     */

    public NPolylinje (Punkt[] horn) {
        if (horn.length > 0) {
            Nod  nod = new Nod (new Punkt (horn[0]));
            this.horn = nod;
            int    pos = 1;
            while (pos < horn.length) {
                nod.nastaNod = new Nod (new Punkt (horn[pos++]));
                nod = nod.nastaNod;
            }
        }
    }
    
    public Nod getNod() { return this.horn; }

    public Punkt[] getHorn() {
        //Om vi bara har en nod eller färre.
        if (this.horn.nastaNod == null) { 
            Punkt[] p = {new Punkt(this.horn.horn)}; 
            return p; 
        }

        //Räknar antal noder.
        int c = 1;
        Nod nod = this.horn;
        //Räknar upp tills kedjan e tom för att kunna skapa en tillräckligt stor array.
        while(nod.nastaNod != null) {
            nod = nod.nastaNod;
            c++;
        }

        Punkt[] h = new Punkt[c];
        nod = this.horn;
        for (int i = 0; i < c; i++) {
            h[i] = nod.horn;
            nod = nod.nastaNod;
        }

        return h;
    }

    public String toString() {

        Punkt[] horn = this.getHorn();

        String res = "{";
        for (int i = 0; i < horn.length; i++) {
            res += ( "{" + horn[i].getNamn() + " " + horn[i].getX() + " " + horn[i].getY() + "}");
        }
        return res + "}";
    }

    public String getFarg() { return this.farg; }
    public int getBredd() { return this.bredd; }
    public double langd() {

        Punkt[] h = getHorn();

        double res = 0;
        for (int i = 0; i < h.length - 1; i++) {
            res += h[i].avstand(h[i + 1]);
        }
        return res;
    }
    public void setFarg(String farg) { this.farg = farg; }
    public void setBredd(int bredd) { this.bredd = bredd; }
    public void laggTill(Punkt horn) {
        Nod nod = this.horn;

        //Kopierar över 
        while (nod.nastaNod != null) {
            nod = nod.nastaNod;
        }
        //Lägger in den nya noden längst bak. 
        nod.nastaNod = new Nod(horn);
    }
    
    public void laggTillFramfor(Punkt horn, String hornNamn) {

        Punkt[] gammalHorn = this.getHorn();

        Punkt[] h = new Punkt[gammalHorn.length + 1];
        int d = 0;
        while (d < gammalHorn.length) {
            if (gammalHorn[d].getNamn() == hornNamn) {
                d++;
                break;
            }
            d++;
        }

        for (int i = 0; i < gammalHorn.length + 1; i++) { 
            if (i < d - 1) 
                h[i] = gammalHorn[i]; 
            else if (i == d - 1) 
                h[i] = horn; 
            else
                h[i] = gammalHorn[i - 1]; 
        } 

        if (h.length > 0) {
            Nod  nod = new Nod (new Punkt (h[0]));
            this.horn = nod;
            int    pos = 1;
            while (pos < h.length) {
                nod.nastaNod = new Nod (new Punkt (h[pos++]));
                nod = nod.nastaNod;
            }
        }
    }

    public void taBort(String hornNamn) {

        Punkt[] gammalHorn = this.getHorn();

        Punkt[] h = new Punkt[gammalHorn.length - 1];
		int i = 0;
		int i2 = 0;
		for(i = 0; i < gammalHorn.length; i++) {
            if(gammalHorn[i].getNamn() == hornNamn) { continue; }
            else {
				h[i2] = gammalHorn[i];
				i2++;
			}
        }

        if (h.length > 0) {
            Nod  nod = new Nod (new Punkt (h[0]));
            this.horn = nod;
            int    pos = 1;
            while (pos < h.length) {
                nod.nastaNod = new Nod (new Punkt (h[pos++]));
                nod = nod.nastaNod;
            }
        }
    }

    public java.util.Iterator<Punkt> iterator() {
		return this.iterator();
	}
}

