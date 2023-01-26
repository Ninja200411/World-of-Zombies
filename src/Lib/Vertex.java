package Lib;

public class Vertex {
    double x;
    double y;

    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vertex(double xy) {
        this.x = xy;
        this.y = xy;
    }
    public double betrag(){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

    public void normal(){
        double factor = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        x = x/factor;
        y = y/factor;
    }
    public void add(Vertex that) {
        x += that.x;
        y += that.y;
    }

    public Vertex radd(Vertex that) {
        return new Vertex(x += that.x,y += that.y);
    }
    public void set(Vertex that) {
        x = that.x;
        y = that.y;
    }

    public void moveTo(Vertex that) {
        x = that.x;
        y = that.y;
    }

    public Vertex mult(double d) {
        return new Vertex(d * x, d * y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
