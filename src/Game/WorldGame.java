package Game;

import Lib.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import static java.awt.event.KeyEvent.*;


record WorldGame(Player player, List<List<? extends GameObject>> goss,int width,int height)
        implements Game {

    private static double Schrittweite = 1.2;

    private static int kills = 0;
    private static int wave = 0;
    WorldGame() {
        this(new Player(new Vertex(200, 200), new Vertex(0, 0), "Images/player.png"), new ArrayList<>(), 500, 500);
    }


    public void init() {
        goss().clear();
        for (int i = 0; i < 2; i++) {
            gegeners.add(new Gegener(new Vertex(i, i), new Vertex(1), "Images/gegner.png", 2, i+1, 50));
        }
    }
    public static void main(String[] args) {
        new WorldGame().play();
    }


    public void keyPressedReaction(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == VK_SPACE) {
            Vertex richtung = null;
            for (int i = 0; i < gegeners.size(); i++) {
                Vertex test = new Vertex(gegeners.get(i).getPos().getX() + gegeners.get(i).width()- (player.pos().getX() + width/2),gegeners.get(i).getPos().getY() + gegeners.get(i).height()- (player.pos().getY() +height/2));
                if (richtung == null || test.betrag() <= richtung.betrag()){
                    richtung = test;
                }
            }
            richtung.normal();
            playerbullets.add(new PlayerBullet(new Vertex(player.pos().getX(),player.pos().getY()),new Vertex(richtung.getX(), richtung.getY()), "Images/playerbullet.png", player.getDamage(), player.getBulletspeed()));
        }
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

    public void doChecks() {
        if(player.pos().getX() < 0){
            player.pos().setX(0);
        }
        if(player.pos().getX() > width-player.width()){
            player.pos().setX(width-player.width());
        }
        if(player.pos().getY() < 0){
            player.pos().setY(0);
        }
        if(player.pos().getY() > height-player.height()){
            player.pos().setY(height-player.height());
        }
        for (var bullet : playerbullets) {
            if(bullet.pos().getX() < 0){
                bullet.pos().setX(0);
            }
            if(bullet.pos().getX() > width-bullet.width()){
                bullet.pos().setX(width-bullet.width());
            }
            if(bullet.pos().getY() < 0){
                bullet.pos().setY(0);
            }
            if(bullet.pos().getY() > height-bullet.height()){
                bullet.pos().setY(height-bullet.height());
            }
        }
        for (int j = 0; j < gegeners.size(); j++) {
            if (gegeners.get(j).touches(player)) {
                player.setHealth(player.getHealth()-gegeners.get(j).getDamage());
                gegeners.get(j).setHealth(gegeners.get(j).getHealth()-player.getDamage());
            }
            for (int i = 0; i < playerbullets.size(); i++) {
                if (gegeners.get(j).touches(playerbullets.get(i))) {
                    gegeners.get(j).setHealth(gegeners.get(j).getHealth()-playerbullets.get(i).getDamage());
                    playerbullets.remove(playerbullets.get(i));
                }
            }
        }
        for (int i = gegeners.size() -1; i >= 0; i--) {
            if(gegeners.get(i).getHealth() <= 0){
                gegeners.remove(gegeners.get(i));
                kills++;
            }
        }
        if(gegeners.size() == 0){
            wave++;
            for (int i = 0; i < wave+1; i++) {
                gegeners.add(new Gegener(new Vertex(i, i), new Vertex(1), "Images/gegner.png", 1, i+1, 50));
            }
        }

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

