package Lib;

import javax.swing.*;
import java.awt.*;

public class Player implements GameObject {
    private int points = 0;
    private Vertex pos;
    private Vertex velocity;
    private double width;
    private double height;
    private String fileName;
    private Image image;
    private int kills = 0;
    private int wave = 0;
    private long endtime = 0;
    private static double damage = 1;
    private static double speed = 1;
    private static double health = 100;
    private static double bulletspeed = 3;
    private static double bulletdamage = 10;
    private static long reloadTime = 1500;
    private static long lastdamgedealt;
    private static long lastshot;
    private static double maxHealth = health;


    public String toString() {
        return "Health: " + (int)health + "\n1) Max Health: "+ maxHealth +"\n2) Max Speed: " + speed + "\n3) Damage: " + damage + "\n4) Bullet Speed: " + bulletspeed + "\n5) Reloadtime: " + reloadTime + "\n6) Bullet Damage: " + bulletdamage + "\n   Points: " + points;
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

    public static long getLastdamgedealt() {
        return lastdamgedealt;
    }

    public static void setLastdamgedealt(long lastdamgedealt) {
        Player.lastdamgedealt = lastdamgedealt;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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

    public void setWidth(double width) {
        this.width = width;
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

    public static void setDamage(double damage) {
        Player.damage = damage;
    }

    public static double getSpeed() {
        return speed;
    }

    public static void setSpeed(double speed) {
        Player.speed = speed;
    }

    public static void setBulletspeed(double bulletspeed) {
        Player.bulletspeed = bulletspeed;
    }

    public static void setBulletdamage(double bulletdamage) {
        Player.bulletdamage = bulletdamage;
    }

    public static void setReloadTime(long reloadTime) {
        Player.reloadTime = reloadTime;
    }

    public static double getMaxHealth() {
        return maxHealth;
    }

    public static void setMaxHealth(double maxHealth) {
        Player.maxHealth = maxHealth;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }
}
