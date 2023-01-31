package Lib;

import javax.swing.*;
import java.awt.*;

public class Player implements GameObject {
    private Vertex pos;
    private Vertex velocity;
    private double width;
    private double height;
    private String fileName;
    private Image image;
    private int kills = 0;
    private int wave = 0;
    private static double damage = 1;
    private static double speed = 1;
    private static double health = 100;
    private static double bulletspeed = 3;
    private static double bulletdamage = 10;
    private static long reloadTime = 300;
    private static long lastdamgetaken;
    private static long lastshot;
    private static double maxHealth = health;


    public String toString() {
        return "Health: " + health + "\nMaxspeed: " + speed + "\nDamage: " + damage + "\nBulletspeed: " + bulletspeed + "\nReloadtime: " + reloadTime + "\nBulletdamage: " + bulletdamage;
    }

    public Player(Vertex pos, Vertex velocity, String fileName, double speed, double damage, double maxHealth) {
        this.pos = pos;
        this.velocity = velocity;
        var iIcon = new ImageIcon(getClass().getClassLoader().getResource(fileName));
        width = iIcon.getIconWidth();
        height = iIcon.getIconHeight();
        image = iIcon.getImage();
        Player.damage = damage;
        health = maxHealth;
        Player.maxHealth = maxHealth;
        Player.speed = speed;
        lastshot = System.currentTimeMillis() - reloadTime;

    }

    public Player(Vertex pos, Vertex velocity, String fileName) {
        this.pos = pos;
        this.velocity = velocity;
        var iIcon = new ImageIcon(getClass().getClassLoader().getResource(fileName));
        width = iIcon.getIconWidth();
        height = iIcon.getIconHeight();
        image = iIcon.getImage();
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

    public void paintTo(Graphics g, Vertex translate) {
        Color color = g.getColor();
        g.drawRect((int) (pos.x - translate.getX()), (int) (pos.y - translate.getY() - 10), (int) (width), 5);
        g.setColor(Color.red);
        g.fillRect((int) (pos.x - translate.getX()), (int) (pos.y - translate.getY() - 10), (int) ((width / maxHealth) * health), 5);
        g.setColor(color);
        g.drawImage(image, (int) (pos.x - translate.getX()), (int) (pos.y - translate.getY()), null);
    }

    public Vertex getPos() {
        return pos;
    }


    public double getWidth() {
        return width;
    }


    public double getHeight() {
        return height;
    }


    public static double getDamage() {
        return damage;
    }


    public static double getHealth() {
        return health;
    }

    public static void setHealth(double health) {
        Player.health = health;
    }

    public static double getBulletspeed() {
        return bulletspeed;
    }


    public static long getReloadTime() {
        return reloadTime;
    }


    public static long getLastshot() {
        return lastshot;
    }

    public static double getBulletdamage() {
        return bulletdamage;
    }

    public static long getLastdamgetaken() {
        return lastdamgetaken;
    }

    public static void setLastdamgetaken(long lastdamgetaken) {
        Player.lastdamgetaken = lastdamgetaken;
    }

    public static void setLastshot(long lastshot) {
        Player.lastshot = lastshot;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }
}
