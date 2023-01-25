package Lib;

import javax.swing.*;
import java.awt.*;

public class Gegener implements GameObject{
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
        height=iIcon.getIconHeight();
        image = iIcon.getImage();
        this.damage = damage;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.speed = speed;
    }
    public void move(Vertex target) {
        Vertex Richtung = new Vertex(target.getX()- (pos.getX() + width/2),target.getY()- (pos().getY() +height/2));
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
    public void paintTo(Graphics g){
        Color color = g.getColor();
        g.drawRect((int)pos.x, (int)pos.y-10, (int)(width), (int)5);
        g.setColor(Color.red);
        g.fillRect((int)pos.x, (int)pos.y-10, (int)((width/maxHealth)*health), (int)5);
        g.setColor(color);
        g.drawImage(image,(int)pos.x, (int)pos.y, null);
    }

    public Vertex getPos() {
        return pos;
    }

    public void setPos(Vertex pos) {
        this.pos = pos;
    }

    public Vertex getVelocity() {
        return velocity;
    }

    public void setVelocity(Vertex velocity) {
        this.velocity = velocity;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getHealth() {
        return health;
    }
    public void setHealth(double shealth) {
        health = shealth;
    }
}
