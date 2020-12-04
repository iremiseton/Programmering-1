package OU5;

public class Punkt {
    private String namn;
    private int x;
    private int y;

    public Punkt(String namn, int x, int y) {
        this.namn = namn;
        this.x = x;
        this.y = y;
    }

    public Punkt(Punkt p) {
        this.x = p.x;
        this.y = p.y;
        this.namn = p.namn;
    }

    public int getX() { return this.x; }
    public void setX(int newX) { this.x = newX; }

    public int getY() { return this.y; }
    public void setY(int newY) { this.y = newY; }

    public String getNamn() { return this.namn; }

    public double avstand(Punkt p) { 
        return Math.sqrt((p.x - this.x) * (p.x - this.x) + (p.y-this.y) * (p.y-this.y));
    }

    public boolean equals(Punkt p) { return this.x == p.x && this.y == p.y; }

    public String toString() { return  "(" + x + ", " + y + ")"; }

}