import java.awt.Color;
import java.awt.Graphics;

public class Player {

    public boolean right, left;
    public int x, y, widht, height, speed;
    
    public Player(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.widht = 40;
        this.height = 5;
    }

    // Cuida da logica do jogo
    public void tick() {
        if (right) {
            x+=speed;
        } else if (left) {
            x-=speed;
        }

        // Colisão com a parede
        if (x + widht > Game.WIDHT) {
            x = Game.WIDHT - widht;
        } else if (x < 0) {
            x = 0;
        }
    }

    // Cuida da parte gráfica
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(x, y, widht, height);
    }
}