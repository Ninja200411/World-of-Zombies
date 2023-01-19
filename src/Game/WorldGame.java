package Game;

import Lib.*;

import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import static java.awt.event.KeyEvent.*;


record WorldGame(GameObject player, List<List<? extends GameObject>> goss, int width, int height)
        implements Game {

    WorldGame() {
        this(new ImageObject(new Vertex(200, 200), new Vertex(0, 0), ""), new ArrayList<>(), 1200, 700);
    }


    public void init() {
        goss().clear();
        goss.add(List.of(new TextObject(new Vertex(10, 20), "X: " + player.pos().getX() + " Y: " +player.pos().getY())));

    }
    public static void main(String[] args) {
        new WorldGame().play();
    }


    public void keyPressedReaction(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case VK_LEFT -> {if (player().velocity().getX() == 0 ) {player().velocity().setX(-1);} else if (player().velocity().getX() == 1 ){player().velocity().setX(0);}}
            case VK_RIGHT -> {if (player().velocity().getX() == 0 ) {player().velocity().setX(1);} else if (player().velocity().getX() == -1 ){player().velocity().setX(0);}}
            case VK_UP -> {if (player().velocity().getY() == 0 ) {player().velocity().setY(-1);} else if (player().velocity().getY() == 1 ){player().velocity().setY(0);}}
            case VK_DOWN -> {if (player().velocity().getY() == 0 ) {player().velocity().setY(1);} else if (player().velocity().getY() == -1 ){player().velocity().setY(0);}}
        }

    }
    public void keyReleasedReaction(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case VK_LEFT -> {if (player().velocity().getX() == -1) player().velocity().setX(0);}
            case VK_RIGHT -> {if (player().velocity().getX() == 1) player().velocity().setX(0);}
            case VK_UP -> {if (player().velocity().getY() == -1) player().velocity().setY(0);}
            case VK_DOWN -> {if (player().velocity().getY() == 1) player().velocity().setY(0);}
        }
    }

    public void doChecks() {

    }

    // public void move(){if (status!=Status.infiziert) super.move();}


    @Override
    public boolean won() {
        return false;
    }

    @Override
    public boolean lost() {
        return false;
    }
}

