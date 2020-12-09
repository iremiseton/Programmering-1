package OU5;

public class Polylinje1 {
    private Punkt[] horn;
    private String farg = "";
    private int bredd = 1;

    public Polylinje1() { this.horn = new Punkt[0]; }

    public Polylinje1(Punkt[] horn, String farg, int bredd) {
        this.farg = farg;
        this.bredd = bredd;
        this.horn = new Punkt[horn.length];
        for (int i = 0; i < horn.length; i++) 
            this.horn[i] = new Punkt(horn[i]);
    }

    public String toString() {
        String res = "{";
        for (int i = 0; i < horn.length; i++) {
            res += ( "{" + horn[i].getNamn() + " " + horn[i].getX() + " " + horn[i].getY() + "}");
        }
        return res + "}";
    }

    public Punkt[] getHorn() {
        return this.horn;
    }

}
