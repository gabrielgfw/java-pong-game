package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    public static int WIDTH = 240;
    public static int HEIGHT = 120;
    public static int SCALE = 3;

    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public Player player;
    public Enemy enemy;
    public Ball ball;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        this.addKeyListener(this);
        player = new Player(100, HEIGHT-10);
        enemy = new Enemy(100, 0);
        ball = new Ball(100, HEIGHT/2 - 1);
    }

    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = layer.getGraphics();

        // Resets player render, avoiding render overflow;
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Players Render
        player.render(g);
        enemy.render(g);
        ball.render(g);

        g = bs.getDrawGraphics();

        // Sets a default background layer, so we can render objects over it.
        g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);

        // Makes everything above visible inside game's window.
        bs.show();
    }

    @Override
    public void run() {
        requestFocus();
        while(true) {

            // # Game Loop (while):
            // Turns the game inside a loop.
            // By using threads, we are able to make the game to flow seamless.

            // # Thread:
            // Turns possible to do more than one thing at time.

            // # Tick:
            // Takes the game's logic and apply in every loop's section.

            // # Render:
            // Responsible for all graphics components in the game.

            // # IMPORTANT:
            // Always tick before render,
            // so we can apply logical changes before it can be seemed.

            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // # IMPORTANT:
        // Treats the event when certain key is pressed.
        // Keep in mind that this doesn't prevent key released events.
        // So if there's not a keyReleased event tied with keyPressed events,
        // you going to have an endless render towards the direction once pressed.

        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if (e.getKeyCode() ==  KeyEvent.VK_LEFT) {
            player.left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if (e.getKeyCode() ==  KeyEvent.VK_LEFT) {
            player.left = false;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        // # JFrame:
        // Responsible for creating the game's window;
        // We need to setup some configurations (below) to prevent undesirables behavior.

        JFrame frame = new JFrame("Pong Game");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);

        new Thread(game).start();

    }


}
