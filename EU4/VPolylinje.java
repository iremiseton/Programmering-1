package EU4;

import java.util.Iterator;
import OU5.Punkt;

public class VPolylinje implements Polylinje {
    
    private Punkt[] horn;
    private String farg;
    private int bredd;

    public VPolylinje() {
        this.horn = null; 
    }

    public VPolylinje(Punkt[] p) {
            this.horn = p;
    }

    public Punkt[] getHorn() {  return this.horn; }
    
    public String getFarg() { return this.farg; }

    public int getBredd() { return this.bredd; }

    public double langd() {
        double res = 0;
        for (int i = 0; i < this.horn.length - 1; i++) {
            res += this.horn[i].avstand(this.horn[i + 1]);
        }
        return res;
    }

    public void setFarg(String farg) { this.farg = farg; }

    public void setBredd(int bredd) { this.bredd = bredd; }

    public void laggTill(Punkt horn) {
        Punkt[] h = new Punkt[this.horn.length + 1];
        int i = 0;
        for (i = 0; i < this.horn.length; i++)
            h[i] = this.horn[i];
        h[i] = new Punkt(horn);

        this.horn = h;
    }

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

    public String toString() {
        String res = "{";
        for (int i = 0; i < horn.length; i++) {
            res += ( "{" + horn[i].getNamn() + " " + horn[i].getX() + " " + horn[i].getY() + "}");
        }
        return res + "}";
    }
    
	public Iterator<Punkt> iterator() {
		return this.iterator();
	}
}
