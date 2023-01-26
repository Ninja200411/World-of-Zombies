package Lib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingScreen extends JPanel {
    private static final long serialVersionUID = 1403492898373497054L;
    Game logic;
    Timer t;

    public SwingScreen(Game gl) {
        this.logic = gl;


        t = new Timer(13, (ev)->{
            logic.move();
            logic.doChecks();
            logic.doShoot();
            logic.Windowsize.setX(getSize().width);
            logic.Windowsize.setY(getSize().height);
            repaint();
            getToolkit().sync();
            requestFocus();
        });
        t.start();


        addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                logic.keyPressedReaction(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                logic.keyReleasedReaction(e);
            }
        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                logic.mousePressedReaction(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                logic.mouseReleasedReaction(e);
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                logic.mouseDraggedReaction(e);
            }
        });
        setFocusable(true);
        requestFocus();
    }



    @Override public Dimension getPreferredSize() {
        return new Dimension((int)logic.width(),(int)logic.height());
    }


    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        logic.paintTo(g);
    }
}
