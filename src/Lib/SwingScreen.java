package Lib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SwingScreen extends JPanel {
    private static final long serialVersionUID = 1403492898373497054L;
    Game logic;
    Timer t;

    public SwingScreen(Game gl) {
        this.logic = gl;


        t = new Timer(13, (ev)->{
            logic.move();
            logic.doChecks();
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
