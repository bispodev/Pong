import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

    public double x, y;
    public int widht, height;
    public Points points = new Points();
    private boolean switchSide = false;

    public Enemy(final double x, final double y) {
        this.x = x;
        this.y = y;
        this.widht = 40;
        this.height = 5;
    }

    // Cuida da logica do jogo
    public void tick() {
        if (switchSide) {
            x += 4;
        }else if(!switchSide){
            x -= 4;
        }
        if (x + widht > Game.WIDHT) {
            x = Game.WIDHT - widht;
            switchSide = false;
        } else if (x < 0) {
            x = 0;
            switchSide = true;
        }
    }

    // Cuida da parte gráfica
    public void render(final Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect((int) x, (int) y, widht, height);
    }
}