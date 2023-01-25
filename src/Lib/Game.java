package Lib;

import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface Game{


    int width();
    int height();
    Stats stats = new Stats(new Vertex(10, 20), "");

    Player player();
    List<List<? extends GameObject>> goss();
    ArrayList<GameObject> sorted = new ArrayList<GameObject>();
    ArrayList<Gegener> gegeners = new ArrayList<Gegener>();
    ArrayList<PlayerBullet> playerbullets = new ArrayList<PlayerBullet>();

    void init();


    void doChecks();

    void keyPressedReaction(KeyEvent keyEvent);
    void keyReleasedReaction(KeyEvent keyEvent);


    default void move(){
        if (ended()) return;
        player().move();
        playerbullets.forEach(playerBullet -> playerBullet.move());
        for (var gos:goss()) gos.forEach(go -> go.move());
        gegeners.forEach(gegener -> gegener.move(new Vertex(player().pos().getX() + player().width()/2, player().pos().getY()+ player().height()/2)));
        stats.setText(player().toString());
    }


    boolean won();
    boolean lost();

    default boolean ended() {
        return won()||lost();
    }


    default void paintTo(Graphics g){
        sorted.clear();
        for (var gos:goss()) gos.forEach( go -> sorted.add(go));
        playerbullets.forEach(playerBullet -> sorted.add(playerBullet));
        sorted.add(player());
        gegeners.forEach(gegener -> sorted.add(gegener));
        sorted.sort(Comparator.comparingDouble(e -> e.pos().getY()));
        sorted.forEach(go -> go.paintTo(g));
        stats.paintTo(g);
    }


    default void play(){
        init();
        var f = new javax.swing.JFrame();
        f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        f.add(new SwingScreen(this));
        System.out.println(f.getSize());
        f.pack();
        f.setVisible(true);
    }
}


