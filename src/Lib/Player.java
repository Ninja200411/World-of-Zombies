package Lib;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
public class Player implements GameObject {
    private Vertex pos;
    private Vertex velocity;
    private double width;
    private double height;
    private String fileName;
    private Image image;
    private static double damage = 1;
    private static double speed = 1;
    private static double health = 100;
    private static double bulletspeed = 1;
    private static long reloadTime = 300;
    private static LocalTime lasshot = LocalTime.now().minusNanos(reloadTime);
    private static double maxHealth = health;

    public String toString(){
        return "Health: " + health + "\nMaxspeed: " + speed + "\nDamage: " +damage;
    }

    public Player(Vertex pos,Vertex velocity, String fileName, double speed, double damage, double maxHealth){
        this.pos = pos;
        this.velocity = velocity;
        var iIcon = new ImageIcon(getClass().getClassLoader().getResource(fileName));
        width = iIcon.getIconWidth();
        height=iIcon.getIconHeight();
        image = iIcon.getImage();
        this.damage = damage;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.speed = speed;

    }
    public Player(Vertex pos, Vertex velocity, String fileName){
        this.pos = pos;
        this.velocity = velocity;
        var iIcon = new ImageIcon(getClass().getClassLoader().getResource(fileName));
        width = iIcon.getIconWidth();
        height=iIcon.getIconHeight();
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

    public static double getDamage() {
        return damage;
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

    public static double getHealth() {
        return health;
    }

    public static void setHealth(double health) {
        Player.health = health;
    }

    public static double getBulletspeed() {
        return bulletspeed;
    }

    public static void setBulletspeed(double bulletspeed) {
        Player.bulletspeed = bulletspeed;
    }

    public static long getReloadTime() {
        return reloadTime;
    }

    public static void setReloadTime(long reloadTime) {
        Player.reloadTime = reloadTime;
    }

    public static LocalTime getLasshot() {
        return lasshot;
    }

    public static void setLasshot(LocalTime lasshot) {
        Player.lasshot = lasshot;
    }

    public static double getMaxHealth() {
        return maxHealth;
    }

    public static void setMaxHealth(double maxHealth) {
        Player.maxHealth = maxHealth;
    }
}
