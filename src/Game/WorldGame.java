package Game;

import Lib.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.Random;

import static java.awt.event.KeyEvent.*;


record WorldGame(Player player, List<List<? extends GameObject>> goss,int width,int height)
        implements Game {

    private static double Schrittweite = 1.2;

    private static int kills = 0;

    private static int wave = 0;
    private static boolean shooting = false;
    private static long damgetakendelay = 500;
    WorldGame() {
        this(new Player(new Vertex(1200, 1200), new Vertex(0, 0), "Images/player.png"), new ArrayList<>(), 1000, 1000);
    }
    static int mausX;
    static int mausY;
    static Point mousePos = new Point(0,0);

    public void init() {
        backgroundgenerator(random.nextInt(3));
        goss().clear();
        mausX = 0;
        mausY = 0;

        for (int i = 0; i < 1; i++) {
            gegeners.add(new Gegener(new Vertex(i, i), new Vertex(1), "Images/gegner.png", 2, i+1, 50));
        }
    }
    public static void main(String[] args) {
        new WorldGame().play();
    }


    public void keyPressedReaction(KeyEvent keyEvent) {
        /*if (keyEvent.getKeyCode() == VK_SPACE && System.currentTimeMillis() - player().getLastshot() >= player().getReloadTime()) {
            Vertex richtung = null;
            for (int i = 0; i < gegeners.size(); i++) {
                Vertex test = new Vertex(gegeners.get(i).getPos().getX() + gegeners.get(i).width()- (player.pos().getX() + Windowsize.x/2),gegeners.get(i).getPos().getY() + gegeners.get(i).height()- (player.pos().getY() +Windowsize.y/2));
                if (richtung == null || test.betrag() <= richtung.betrag()){
                    richtung = test;
                }
            }
            richtung.normal();
            playerbullets.add(new PlayerBullet(new Vertex(player.pos().getX(),player.pos().getY()),new Vertex(richtung.getX(), richtung.getY()), "Images/playerbullet.png", player.getBulletdamage(), player.getBulletspeed()));
            player().setLastshot(System.currentTimeMillis());
        } */
        switch (keyEvent.getKeyCode()) {
            case VK_LEFT, VK_A-> {if (player().velocity().getX() == 0 ) {player().velocity().setX(-Schrittweite);} else if (player().velocity().getX() == Schrittweite ){player().velocity().setX(0);}}
            case VK_RIGHT, VK_D -> {if (player().velocity().getX() == 0 ) {player().velocity().setX(Schrittweite);} else if (player().velocity().getX() == -Schrittweite ){player().velocity().setX(0);}}
            case VK_UP, VK_W -> {if (player().velocity().getY() == 0 ) {player().velocity().setY(-Schrittweite);} else if (player().velocity().getY() == Schrittweite ){player().velocity().setY(0);}}
            case VK_DOWN, VK_S -> {if (player().velocity().getY() == 0 ) {player().velocity().setY(Schrittweite);} else if (player().velocity().getY() == -Schrittweite ){player().velocity().setY(0);}}
        }

    }


    public void keyReleasedReaction(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case VK_LEFT, VK_A-> {if (player().velocity().getX() == -Schrittweite) player().velocity().setX(0);}
            case VK_RIGHT, VK_D -> {if (player().velocity().getX() == Schrittweite) player().velocity().setX(0);}
            case VK_UP, VK_W -> {if (player().velocity().getY() == -Schrittweite) player().velocity().setY(0);}
            case VK_DOWN, VK_S -> {if (player().velocity().getY() == Schrittweite) player().velocity().setY(0);}
        }
    }



    public void mouseDraggedReaction(MouseEvent mouseEvent) {
        mausX = mouseEvent.getX();
        mausY = mouseEvent.getY();
    }

    public void mousePressedReaction(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            shooting = true;
        }
    }


    public void mouseReleasedReaction(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            shooting = false;
        }
    }
    public void doShoot() {
        if (shooting && System.currentTimeMillis() - player().getLastshot() >= player().getReloadTime()) {
            Vertex richtung = new Vertex(mausX +tranlate.getX() - (player.pos().getX() + player.getWidth()/2),mausY +tranlate.getY() - (player.pos().getY() + player().height()/2));
            richtung.normal();
            playerbullets.add(new PlayerBullet(new Vertex(player.pos().getX() + player.getWidth()/2,player.pos().getY() + player.getHeight()/2) ,new Vertex(richtung.getX(), richtung.getY()), "Images/playerbullet.png", player.getBulletdamage(), player.getBulletspeed()));
            player().setLastshot(System.currentTimeMillis());
        }
    }




    public void doChecks() {
        if(player.pos().getX() < 1000){
            player.pos().setX(1000);
        }
        if(player.pos().getX() > 9000-player.width()){
            player.pos().setX(9000-player.width());
        }
        if(player.pos().getY() < 1000){
            player.pos().setY(1000);
        }
        if(player.pos().getY() > 9000-player.height()){
            player.pos().setY(9000-player.height());
        }
        ArrayList<PlayerBullet> bulletsToRemove = new ArrayList<PlayerBullet>();
        for (var bullet : playerbullets) {
            if(bullet.pos().getX() < 800){
                bulletsToRemove.add(bullet);
            }
            if(bullet.pos().getX() > 9200-bullet.width()){
                bulletsToRemove.add(bullet);
            }
            if(bullet.pos().getY() < 800){
                bulletsToRemove.add(bullet);
            }
            if(bullet.pos().getY() > 9200-bullet.height()){
                bulletsToRemove.add(bullet);
            }
        }

        ArrayList<Gegener> gegenersToRemove = new ArrayList<Gegener>();

        for (int j = 0; j < gegeners.size(); j++) {

            if (System.currentTimeMillis() - player.getLastdamgetaken() >= damgetakendelay){
                if (gegeners.get(j).touches(player)) {
                    player.setHealth(player.getHealth()-gegeners.get(j).getDamage());
                    gegeners.get(j).setHealth(gegeners.get(j).getHealth()-player.getDamage());
                    player.setLastdamgetaken(System.currentTimeMillis());
                }
            }
            for (int i = 0; i < playerbullets.size(); i++) {
                if (gegeners.get(j).touches(playerbullets.get(i))) {
                    gegeners.get(j).setHealth(gegeners.get(j).getHealth()-playerbullets.get(i).getDamage());
                    bulletsToRemove.add(playerbullets.get(i));
                }
            }
        }
        for (int i = gegeners.size() -1; i >= 0; i--) {
            if(gegeners.get(i).getHealth() <= 0){
                gegenersToRemove.add(gegeners.get(i));
                kills++;
            }
        }
        for (var bullet : bulletsToRemove) {
            playerbullets.remove(bullet);
        }
        for (var gegener : gegenersToRemove) {
            gegeners.remove(gegener);
        }
        /*if(gegeners.size() == 0){
            wave++;
            for (int i = 0; i < wave+1; i++) {
                gegeners.add(new Gegener(new Vertex(i, i), new Vertex(1), "Images/gegner.png", 1, i+1, 50));
            }
        }*/

    }



    @Override
    public boolean won() {
        return false;
    }

    @Override
    public boolean lost() {
        return false;
    }
}

