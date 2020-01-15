import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {

    public double x, y;
    public int widht, height;

    public double transformX, transformY, speed = 1;

    public Ball(final double x, final double y) {
        this.x = x;
        this.y = y;
        this.widht = 5;
        this.height = 5;
        this.transformX = Math.random() * 0.1 + 1.3;
        this.transformY = Math.random() * 0.1 + 1.3;
    }

    // Cuida da logica do jogo
    public void tick() {

        // colisão com as laterias
        if (x + (transformX * speed) + widht >= Game.WIDHT) {
            transformX *= -1;
            speed += (speed * 0.10);
        } else if (x + (transformX * speed) < 0) {
            transformX *= -1;
            speed += (speed * 0.10);
        }
        if (speed == 2) {
            speed = 2;
        }

        // Logica para pontução
        if (y >= Game.HEIGHT) {
            Debug("ponto Inimigo");
            speed = 0;
            new Game();
            return;
        } else if (y < 0) {
            Debug("ponto do Jogador");
            speed = 0;
            new Game();
            return;
        }

        // Colisor da bola
        Rectangle bounds = new Rectangle((int) (x + (transformX * speed)), (int) (y + (transformY * speed)), widht,
                height);

        // Colisor da bola
        Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.widht, Game.player.height);

        // colisor do enemy
        Rectangle boundsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.widht,
                Game.enemy.height);

        // Verificando colisão
        if (bounds.intersects(boundsPlayer)) {
            transformY *= -1;
        } else if (bounds.intersects(boundsEnemy)) {
            transformY *= -1;
        }

        // Momentação da bola
        x += transformX * speed;
        y += transformY * speed;

    }

    // Cuida da parte gráfica
    public void render(final Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect((int) x, (int) y, widht, height);
    }

    // Debug em tela
    private void Debug(final String text) {
        System.out.println(text);
    }
}