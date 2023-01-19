package Lib;

public class Vertex {
    double x;
    double y;

    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vertex that) {
        x += that.x;
        y += that.y;
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
}
