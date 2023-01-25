package Lib;

import javax.swing.*;
import java.awt.*;

public class PlayerBullet implements GameObject{
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
        height=iIcon.getIconHeight();
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
    public void paintTo(Graphics g){
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}

