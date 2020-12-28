package pong;

import java.awt.*;
import java.util.Random;

public class Ball {

    public double x, y;
    public int width, height;
    public double dx, dy;
    public double speed = 1;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 4;
        this.height = 4;
        this.dx = new Random().nextGaussian();
        this.dy = new Random().nextGaussian();
    }

    public void tick() {

        // # Winning Check:
        if(y >= Game.HEIGHT) {
            System.out.println("Ponto do Inimigo.");
            new Game();
            return;

        } else if(y < 0) {
            System.out.println("Ponto do Player.");
            new Game();
            return;
        }

        // # Wall Collision:
        if((x + (dx * speed) + width >= Game.WIDTH) ||
            (x + (dx * speed) < 0)) {
            dx *= -1;
        }

        // # Player and Enemy collision:
        Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
        Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
        Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);

        if(bounds.intersects(boundsPlayer)) {
            dy *= -1;
            speed += 0.1;
        } else if(bounds.intersects(boundsEnemy)) {
            dy *= -1;
            speed += 0.1;
        }

        // # Ball Movement:
        x += dx * speed;
        y += dy * speed;
    }

    public void render(Graphics g) {

        // # Casting:
        // Our fillRect this time has a cast (int), to make possible
        // to fill those parameter with double values (extracting only the int value from the double input).

        g.setColor(Color.yellow);
        g.fillRect((int)x, (int)y, width, height);
    }
}
