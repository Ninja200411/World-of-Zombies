package Lib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public interface Game{
    long startTime = System.currentTimeMillis();
    int width();
    int height();
    Vertex Windowsize = new Vertex(0);
    Stats stats = new Stats(new Vertex(10, 20), "");

    Player player();
    List<List<? extends GameObject>> goss();
    ArrayList<GameObject> sorted = new ArrayList<GameObject>();
    ArrayList<Gegener> gegeners = new ArrayList<Gegener>();
    ArrayList<PlayerBullet> playerbullets = new ArrayList<PlayerBullet>();
    ArrayList<ArrayList<Image>> background = new ArrayList<ArrayList<Image>>();
    Random random = new Random();
    Vertex tranlate = new Vertex(0);
    int backwith = 32;
    int backheith = 32;

    void init();


    void doChecks();

    void keyPressedReaction(KeyEvent keyEvent);
    void keyReleasedReaction(KeyEvent keyEvent);
    void mousePressedReaction(MouseEvent mouseEvent);
    void mouseDraggedReaction(MouseEvent mouseEvent);
    void mouseReleasedReaction(MouseEvent mouseEvent);



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

    void doShoot();

    default boolean ended() {
        return won()||lost();
    }
    
    default void backgroundgenerator(int teilset){
        int teilstart = 1;
        int teilende = 6;
        if(teilset == 1) {
            teilstart = 1;
            teilende = 6;
        } else if (teilset == 2) {
            teilstart = 7;
            teilende = 12;
        } else if (teilset == 3) {
            teilstart = 13;
            teilende = 18;
        }
        ArrayList<Image> backlist = new ArrayList<Image>();
        for (int i = 1; i <= 6 ; i++) {
            var iIcon = new ImageIcon(getClass().getClassLoader().getResource("Images/bild/teil" + i + ".png"));
            backlist.add(iIcon.getImage());
        }
        
        for (int i = 0; i < (int) (10000 / backheith); i++) {
            background.add(new ArrayList<Image>());
            for (int j = 0; j < (int) (10000 / backwith); j++) {
                background.get(i).add(backlist.get(random.nextInt(backlist.size()-1)));
            }
        }
    }

    default void paintTo(Graphics g){
        tranlate.setX(player().getPos().x - Windowsize.x / 2 + player().width()/2);
        tranlate.setY(player().getPos().y - Windowsize.y / 2 + player().height()/2);
        for (int i = 0; i < background.size(); i++) {
            for (int j = 0; j < background.get(i).size(); j++) {
                g.drawImage(background.get(i).get(j),(int)(backwith*j - tranlate.x), (int)(backheith*i - tranlate.y), null);
            }
        }
        sorted.clear();
        for (var gos:goss()) gos.forEach( go -> sorted.add(go));
        playerbullets.forEach(playerBullet -> sorted.add(playerBullet));
        sorted.add(player());
        gegeners.forEach(gegener -> sorted.add(gegener));
        sorted.sort(Comparator.comparingDouble(e -> e.pos().getY()));
        sorted.forEach(go -> go.paintTo(g, tranlate));
        stats.paintTo(g, new Vertex(0));
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


