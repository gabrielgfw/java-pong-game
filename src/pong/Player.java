package pong;

import java.awt.*;

public class Player {

    public boolean right, left;
    public int x, y;
    public int width, height;

    public Player (int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 10;
    }

    public void tick() {

        // # Moving System:
        // KeyListener is always changing those variables
        // in order to make possible the player's movement inside the game.

        if(right) {
            x++;
        } else if (left) {
            x--;
        }

        // # Collision System:
        // Always comparing the player position
        // against the game's window size.
        // That prevents player model going outside the window's limits
        // and being invisible.

        if(x + width > Game.WIDTH) {
            x = Game.WIDTH - width;
        } else if(x < 0) {
            x = 0;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, 40, 10);
    }
}
