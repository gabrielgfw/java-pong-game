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
        this.width = 30;
        this.height = 5;
    }

    public void tick() {
        // # Enemy's IA:
        x += (Game.ball.x - x - 20) * 0.0958;

        if(x + width > Game.WIDTH) {
            x = Game.WIDTH - width;
        } else if(x < 0) {
            x = 0;
        }
    }

    public void render(Graphics g) {

        // # Casting:
        // Our fillRect this time has a cast (int), to make possible
        // to fill those parameter with double values (extracting only the int value from the double input).

        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, width, height);
    }
}
