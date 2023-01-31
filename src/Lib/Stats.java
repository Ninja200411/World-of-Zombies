package Lib;

import java.awt.*;

public class Stats implements GameObject {
    private String text;
    private Vertex pos;
    private final Vertex velocity;
    private final double width;
    private final double heigth;
    private final int fontSize;
    private final String fontName;


    public Stats(Vertex pos, Vertex velocity, double width, double heigth, int fontSize, String fontName, String text) {
        this.pos = pos;
        this.velocity = velocity;
        this.width = width;
        this.heigth = heigth;
        this.fontSize = fontSize;
        this.fontName = fontName;
        this.text = text;
    }

    public Stats(Vertex pos, String text) {
        this(pos, new Vertex(0, 0), 0, 0, 20, "Helvetica", text);
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
    public void paintTo(Graphics g, Vertex translate) {
        String[] teiltext = text.split("\n");
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.setFont(new Font(fontName, Font.PLAIN, fontSize));
        for (int i = 0; i < teiltext.length; i++) {
            g.drawString(teiltext[i], (int) pos().x, (int) (pos().y + fontSize * i));
        }
        g.setColor(c);
    }


    public void setText(String text) {
        this.text = text;
    }


    public void setPos(Vertex pos) {
        this.pos = pos;
    }
}
