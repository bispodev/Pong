import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

    public double x, y;
    public int widht, height;

    public Enemy(double x, double y) {
        this.x = x;
        this.y = y;
        this.widht = 40;
        this.height = 5;
    }

    // Cuida da logica do jogo
    public void tick() {
        x += (Game.ball.x - x - 6) * 0.6;
        // Colisão com a parede
        if (x + widht > Game.WIDHT) {
            x = Game.WIDHT - widht;
        } else if (x < 0) {
            x = 0;
        }
    }

    // Cuida da parte gráfica
    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect((int) x, (int) y, widht, height);
    }
}