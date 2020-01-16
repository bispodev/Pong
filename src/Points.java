import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Points {

    public int pointsRed, pointsBlue;

    public Points() {
    }

    public Points(int red, int blue) {
        this.pointsBlue = blue;
        this.pointsRed = red;
    }

    public void setPointsRed() {
        pointsRed += 1;
        System.out.println(pointsRed);
        new Game(pointsRed, pointsBlue);
        return;
    }

    public void setPointsBlue() {
        pointsBlue += 1;
        System.out.println(pointsBlue);
        new Game(pointsRed, pointsBlue);
        return;
    }

    public void render(Graphics graphics) {
        graphics.setFont(new Font("Arial", Font.BOLD, 10));
        graphics.setColor(Color.white);
        graphics.drawString("" + pointsRed, 10, 50);
        graphics.drawString("" + pointsBlue, 10, 70);
    }

}