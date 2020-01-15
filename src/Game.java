import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

    private static final long serialVersionUID = -2535699999487469575L;
    public static int WIDHT = 200;
    public static int HEIGHT = 120;
    public static int SCALE = 3; // Ajuda na otimização do game
    // Layer para renderizar os graficos
    public BufferedImage layer = new BufferedImage(WIDHT, HEIGHT, BufferedImage.TYPE_INT_RGB);

    // Istanciando o player e Enemy
    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    // Metodo construtor
    public Game() {
        this.setPreferredSize(new Dimension(WIDHT * SCALE, HEIGHT * SCALE));
        this.addKeyListener(this);
        player = new Player(100, HEIGHT - 5, 5);
        enemy = new Enemy(100, 0);
        ball = new Ball(100, HEIGHT/2);
    }

    // Incializa a aplicação
    public static void main(final String[] args) {
        final Game game = new Game();
        final JFrame frame = new JFrame("Pong");

        frame.requestFocus();
        frame.setResizable(false); // Faz com que o usuario não redimenciona a janale
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Evita que a aplicação fique rodando mesmo com o frame
                                                              // fechado
        frame.add(game); // Adiciona um novo componente ao frame
        frame.pack(); // Adiciona os componentes de acordo com os devidos tamanhos
        frame.setLocationRelativeTo(null); // Iniciando a janela no centro da tela, deve ser colocado sempre uma linha
                                           // antes do setVisible
        frame.setVisible(true); // Exibi o frame (Deve ficar ser a ultima linha a ser carregada)

        new Thread(game).start();
    }

    // Logica do game em geral
    public void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
    }

    // Parte gráfica geral
    public void render() {

        // BufferStrategy serve o funcionamendo de video e permitindo o bakcbuffer
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            /*
             * Usase "2", uma frente visivel (aparece para o usuario) outra não(o
             * backbuffer, aonde tudo é desenhado antes de ir para o frontbuffer). Quanto
             * mais buffers, mais memoria será utilizada
             */
            this.createBufferStrategy(2);
            return;
        }
        Graphics graphics = layer.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDHT, HEIGHT);

    //Enemy
        enemy.render(graphics);
        ball.render(graphics);

    // Player
        player.render(graphics);
        // Desenhando o player
        graphics = bs.getDrawGraphics();
        graphics.drawImage(layer, 0, 0, WIDHT * SCALE, HEIGHT * SCALE, null);
        // Renderizando em tela
        bs.show();
    }



    @Override
    public void run() {
        while (true) {
            tick();
            render();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Capturando comandos do teclado
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("Direita");
            player.left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("Esquerda");
            player.right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}