package OU5;

public class Polylinje {
    private Punkt[] horn;
    private String farg = "";
    private int bredd = 1;

    public Polylinje() { this.horn = new Punkt[0]; }

    public Polylinje(Punkt[] horn) {
        this.horn = new Punkt[horn.length];
        for (int i = 0; i < horn.length; i++) 
            this.horn[i] = new Punkt(horn[i]);
    }

    public String toString() {
        String res = "{";
        for (int i = 0; i < horn.length; i++) {
            res += ( "{" + horn[i].getNamn() + " " + horn[i].getX() + " " + horn[i].getY() + "}");
        }
        return res + "}, " + this.getFarg();
    }

    public Punkt[] getHorn() {
        Punkt[] h = new Punkt[this.horn.length];
        for (int i = 0; i < this.horn.length; i++)
            h[i] = this.horn[i];
        return h; 
    }
    public String getFarg() { return this.farg; }
    public int getBredd() { return this.bredd; }
    public void setFarg(String farg) { this.farg = farg; }
    public void setBredd(int bredd) { this.bredd = bredd; }

    public double langd() {
        double res = 0;
        for (int i = 0; i < this.horn.length - 1; i++) {
            res += this.horn[i].avstand(this.horn[i + 1]);
        }
        return res;
    }

    public void laggTill(Punkt horn) {
        Punkt[] h = new Punkt[this.horn.length + 1];
        int i = 0;
        for (i = 0; i < this.horn.length; i++)
            h[i] = this.horn[i];
        h[i] = new Punkt(horn);

        this.horn = h;
    }
    //  0  1  2  3
    // [1, 2, 3, 5]
    // (0, 1)

    // [0, 1, 3, 5,

    public void laggTillFramfor(Punkt horn, String hornNamn) {
        Punkt[] h = new Punkt[this.horn.length + 1];
        int d = 0;
        while (d < this.horn.length) {
            if (this.horn[d].getNamn() == hornNamn) {
                d++;
                break;
            }
            d++;
        }

        for (int i = 0; i < this.horn.length + 1; i++) { 
            if (i < d - 1) 
                h[i] = this.horn[i]; 
            else if (i == d - 1) 
                h[i] = horn; 
            else
                h[i] = this.horn[i - 1]; 
        } 

        this.horn = h;
    }

    public void taBort(String hornNamn) {
		Punkt[] h = new Punkt[this.horn.length - 1];
		int i = 0;
		int i2 = 0;
		for(i = 0; i < this.horn.length; i++) {
            if(this.horn[i].getNamn() == hornNamn) { continue; }
            else {
				h[i2] = this.horn[i];
				i2++;
			}
        }
        this.horn = h;
	}
}
 