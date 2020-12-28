package pong;

import java.awt.*;
import java.util.Random;

public class Ball {

    public double x, y;
    public int width, height;
    public double dx, dy;
    public double speed = 1.6;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 4;
        this.height = 4;
        this.dx = new Random().nextGaussian();
        this.dy = new Random().nextGaussian();
    }

    public void tick() {
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
