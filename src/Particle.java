import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Particle extends JComponent{
    private double x_coord;
    private double y_coord;
    private double velocity;
    private double angle;

    public Particle(double x, double y, double velocity, double angle){
        this.x_coord = x;
        this.y_coord = y;
        this.angle = angle;
        this.velocity = velocity;
        repaint();
    }

    public void updatePosition(double timeStep) {
        double deltaX = getVelocityX() * timeStep;
        double deltaY = getVelocityY() * timeStep;
        x_coord += deltaX;
        y_coord += deltaY;
        repaint();
    }

    public double getXCoord(){
        return x_coord;
    }

    public double getYCoord() {
        return y_coord;
    }

    public double getAngle() {
        return angle;
    }

    public double getVelocity() {
        return velocity;
    }
    public double getVelocityX() {
        return velocity * Math.cos(Math.toRadians(angle));
    }
    public double getVelocityY() {
        return velocity * Math.sin(Math.toRadians(angle));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillOval((int) x_coord - 5, 720 - (int)y_coord - 5, 10, 10);
        g.setColor(Color.RED);
    }
}
