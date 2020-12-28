package pong;

import java.awt.*;

public class Enemy {

    // # Positions:
    // Our enemy has different type value for coordination
    // to make it with more organic movement.

    public double x, y;
    public int width, height;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 10;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        // # Casting:
        // Our fillRect this time has a cast (int), to make possible
        // to fill those parameter with double values (extracting only the int value from the double input).

        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 40, 10);
    }
}
