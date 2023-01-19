package Lib;

public abstract class AbstractGameObject implements GameObject{
    protected Vertex pos;
    protected Vertex velocity;
    protected double width;
    protected double height;

    public Vertex pos(){return pos;}
    public Vertex velocity(){return velocity;}
    public double width(){return width;}
    public double height(){return height;}

    public AbstractGameObject(Vertex p, Vertex v, double w, double h){
        pos=p; velocity=v; width=w; height=h;
    }
}


