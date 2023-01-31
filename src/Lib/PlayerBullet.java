package Lib;

import javax.swing.*;
import java.awt.*;

public class PlayerBullet implements GameObject {
    private Vertex pos;
    private Vertex velocity;
    private double width;
    private double height;
    private String fileName;
    private Image image;
    private double damage;
    private double speed;


    public PlayerBullet(Vertex pos, Vertex velocity, String filename, double damage, double speed) {
        this.pos = pos;
        this.velocity = velocity;
        var iIcon = new ImageIcon(getClass().getClassLoader().getResource(filename));
        width = iIcon.getIconWidth();
        height = iIcon.getIconHeight();
        image = iIcon.getImage();
        this.damage = damage;
        this.speed = speed;
    }

    public void move() {
        Vertex Richtung = velocity;
        Richtung.normal();
        pos.add(Richtung.mult(speed));

    }

    @Override
    public Vertex pos() {
        return pos;
    }

    @Override
    public Vertex velocity() {
        return velocity;
    }

    @Override
    public double width() {
        return width;
    }

    @Override
    public double height() {
        return height;
    }

    @Override
    public void paintTo(Graphics g, Vertex translate) {
        g.drawImage(image, (int) (pos.x - translate.getX()), (int) (pos.y - translate.getY()), null);
    }

    public double getDamage() {
        return damage;
    }

}

