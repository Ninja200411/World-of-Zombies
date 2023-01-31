package Lib;

import javax.swing.*;
import java.awt.*;

public class Gegener implements GameObject {
    private Vertex pos;
    private Vertex velocity;
    private double width;
    private double height;
    private Image image;
    private double damage;
    private double health;
    private double maxHealth;
    private double speed;


    public Gegener(Vertex pos, Vertex velocity, String filename, double damage, double speed, double maxHealth) {
        this.pos = pos;
        this.velocity = velocity;
        var iIcon = new ImageIcon(getClass().getClassLoader().getResource(filename));
        width = iIcon.getIconWidth();
        height = iIcon.getIconHeight();
        image = iIcon.getImage();
        this.damage = damage;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.speed = speed;
    }

    public void move(Vertex target) {
        Vertex Richtung = new Vertex(target.getX() - (pos.getX() + width / 2), target.getY() - (pos().getY() + height / 2));
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
        Color color = g.getColor();
        g.drawRect((int) (pos.x - translate.getX()), (int) (pos.y - translate.getY() - 10), (int) (width), 5);
        g.setColor(Color.red);
        g.fillRect((int) (pos.x - translate.getX()), (int) (pos.y - translate.getY() - 10), (int) ((width / maxHealth) * health), 5);
        g.setColor(color);
        g.drawImage(image, (int) (pos.x - translate.getX()), (int) (pos.y - translate.getY()), null);
    }

    public double getDamage() {
        return damage;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double shealth) {
        health = shealth;
    }
}
