package Game;

import Lib.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import static java.awt.event.KeyEvent.*;


record WorldGame(Player player, List<List<? extends GameObject>> goss, int width, int height)
        implements Game {

    private static final double Schrittweite = 1.2;

    private static boolean shooting = false;
    private static final long damgetakendelay = 500;

    WorldGame() {
        this(new Player(new Vertex(1200, 1200), new Vertex(0, 0), "Images/player.png"), new ArrayList<>(), 1000, 1000);
    }

    static int mausX;
    static int mausY;
    static Point mousePos = new Point(0, 0);

    public void init() {
        random.setSeed(System.currentTimeMillis());
        backgroundgenerator(random.nextInt(3));
        goss().clear();
        mausX = 0;
        mausY = 0;
        for (int i = 0; i < 30; i++) {
            gegeners.add(new Gegener(new Vertex(random.nextDouble(gameWidth) + 1000, random.nextDouble(gameHeight) + 1000), new Vertex(1), "Images/gegner.png", random.nextDouble(2.0) + 1, random.nextDouble(1.2) + 0.5, random.nextInt(50) * 2));
        }
    }

    public static void main(String[] args) {
        new WorldGame().play();
    }


    public void keyPressedReaction(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case VK_LEFT, VK_A -> {
                if (player().velocity().getX() == 0) {
                    player().velocity().setX(-Schrittweite);
                } else if (player().velocity().getX() == Schrittweite) {
                    player().velocity().setX(0);
                }
            }
            case VK_RIGHT, VK_D -> {
                if (player().velocity().getX() == 0) {
                    player().velocity().setX(Schrittweite);
                } else if (player().velocity().getX() == -Schrittweite) {
                    player().velocity().setX(0);
                }
            }
            case VK_UP, VK_W -> {
                if (player().velocity().getY() == 0) {
                    player().velocity().setY(-Schrittweite);
                } else if (player().velocity().getY() == Schrittweite) {
                    player().velocity().setY(0);
                }
            }
            case VK_DOWN, VK_S -> {
                if (player().velocity().getY() == 0) {
                    player().velocity().setY(Schrittweite);
                } else if (player().velocity().getY() == -Schrittweite) {
                    player().velocity().setY(0);
                }
            }
        }

    }


    public void keyReleasedReaction(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case VK_LEFT, VK_A -> {
                if (player().velocity().getX() == -Schrittweite) player().velocity().setX(0);
            }
            case VK_RIGHT, VK_D -> {
                if (player().velocity().getX() == Schrittweite) player().velocity().setX(0);
            }
            case VK_UP, VK_W -> {
                if (player().velocity().getY() == -Schrittweite) player().velocity().setY(0);
            }
            case VK_DOWN, VK_S -> {
                if (player().velocity().getY() == Schrittweite) player().velocity().setY(0);
            }
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
        if (shooting && System.currentTimeMillis() - Player.getLastshot() >= Player.getReloadTime()) {
            Vertex richtung = new Vertex(mausX + tranlate.getX() - (player.pos().getX() + player.getWidth() / 2), mausY + tranlate.getY() - (player.pos().getY() + player().height() / 2));
            richtung.normal();
            playerbullets.add(new PlayerBullet(new Vertex(player.pos().getX() + player.getWidth() / 2, player.pos().getY() + player.getHeight() / 2), new Vertex(richtung.getX(), richtung.getY()), "Images/playerbullet.png", Player.getBulletdamage(), Player.getBulletspeed()));
            Player.setLastshot(System.currentTimeMillis());
        }
    }


    public void doChecks() {
        if (player.pos().getX() < 1000) {
            player.pos().setX(1000);
        }
        if (player.pos().getX() > gameWidth - player.width() + 1000) {
            player.pos().setX(gameWidth - player.width() + 1000);
        }
        if (player.pos().getY() < 1000) {
            player.pos().setY(1000);
        }
        if (player.pos().getY() > gameHeight - player.height() + 1000) {
            player.pos().setY(gameHeight - player.height() + 1000);
        }
        ArrayList<PlayerBullet> bulletsToRemove = new ArrayList<PlayerBullet>();
        for (var bullet : playerbullets) {
            if (bullet.pos().getX() < 800) {
                bulletsToRemove.add(bullet);
            }
            if (bullet.pos().getX() > gameWidth - bullet.width() + 1200) {
                bulletsToRemove.add(bullet);
            }
            if (bullet.pos().getY() < 800) {
                bulletsToRemove.add(bullet);
            }
            if (bullet.pos().getY() > gameHeight - bullet.height() + 1200) {
                bulletsToRemove.add(bullet);
            }
        }

        ArrayList<Gegener> gegenersToRemove = new ArrayList<Gegener>();

        for (int j = 0; j < gegeners.size(); j++) {

            if (System.currentTimeMillis() - Player.getLastdamgetaken() >= damgetakendelay) {
                if (gegeners.get(j).touches(player)) {
                    Player.setHealth(Player.getHealth() - gegeners.get(j).getDamage());
                    gegeners.get(j).setHealth(gegeners.get(j).getHealth() - Player.getDamage());
                    Player.setLastdamgetaken(System.currentTimeMillis());
                }
            }
            for (int i = 0; i < playerbullets.size(); i++) {
                if (gegeners.get(j).touches(playerbullets.get(i))) {
                    gegeners.get(j).setHealth(gegeners.get(j).getHealth() - playerbullets.get(i).getDamage());
                    bulletsToRemove.add(playerbullets.get(i));
                }
            }
        }
        for (int i = gegeners.size() - 1; i >= 0; i--) {
            if (gegeners.get(i).getHealth() <= 0) {
                gegenersToRemove.add(gegeners.get(i));
                player.setKills(player.getKills() + 1);
            }
        }
        for (var bullet : bulletsToRemove) {
            playerbullets.remove(bullet);
        }
        for (var gegener : gegenersToRemove) {
            gegeners.remove(gegener);
        }
        if (gegeners.size() == 0) {
            player.setWave(player.getWave() + 1);
            for (int i = 0; i < player().getWave() + 100; i++) {
                gegeners.add(new Gegener(new Vertex(random.nextDouble(gameWidth) + 1000, random.nextDouble(gameHeight) + 1000), new Vertex(1), "Images/gegner.png", random.nextDouble(2.0) + 1, random.nextDouble(1.2) + 0.5, random.nextInt(50) * 2));
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

