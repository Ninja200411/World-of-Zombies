package Lib;

import java.awt.*;

public class Stats implements GameObject{
    private String text;
    private Vertex pos;
    private Vertex velocity;
    private double width;
    private double heigth;
    private int fontSize;
    private String fontName;


    public Stats(Vertex pos, Vertex velocity, double width, double heigth, int fontSize, String fontName, String text) {
        this.pos = pos;
        this.velocity = velocity;
        this.width = width;
        this.heigth = heigth;
        this.fontSize = fontSize;
        this.fontName = fontName;
        this.text = text;
    }
    public Stats(Vertex pos, String text){
        this(pos,new Vertex(0,0),0,0,20,"Helvetica",text);
    }

    @Override
    public Vertex pos() {
        return pos;
    }

    @Override
    public Vertex velocity() {
        return velocity();
    }

    @Override
    public double width() {
        return width;
    }

    @Override
    public double height() {
        return heigth;
    }

    @Override
    public void paintTo(Graphics g) {
        String[] teiltext = text.split("\n");
        g.setFont(new Font(fontName, Font.PLAIN, fontSize));
        for (int i = 0; i < teiltext.length; i++) {
            g.drawString(teiltext[i], (int)pos().x, (int)(pos().y+fontSize*i));
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
