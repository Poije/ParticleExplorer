import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SimulationPanel extends JPanel{
    private List<Particle> particles = Collections.synchronizedList(new ArrayList<Particle>());
    private List<Wall> walls = Collections.synchronizedList(new ArrayList<Wall>());
    private final int SIMULATION_WIDTH = 1280;
    private final int SIMULATION_HEIGHT = 720;
    private final int THREAD_COUNT = 8;
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public SimulationPanel(){
        setBounds(50, 50, SIMULATION_WIDTH, SIMULATION_HEIGHT);
        setBackground(Color.WHITE);
        executorService.execute(() -> {
            while (true) {
                updateSimulation();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void opt1Add(double x, double y, double angle, double velocity){
        Particle particle = new Particle(x, y, velocity, angle);
        this.particles.add(particle);
        particle.setBounds(0,0, 1280,720);
        this.add(particle);
    }

    public void opt2Add(int n, double x1, double y1, double x2, double y2, double angle, double velocity){
        double xDifference = (x2 - x1) / (n - 1);
        double yDifference = (y2 - y1) / (n - 1);
        for (int i = 0; i < n; i++){
            opt1Add((x1 + (i * xDifference)), (y1 + (i * yDifference)), angle, velocity);
        }
    }

    public void opt3Add(int n, double x, double y, double angle1, double angle2, double velocity){
        double angleDifference = (angle2 - angle1) / (n - 1);
        for (int i = 0; i < n; i++){
            opt1Add(x, y, (angle1 + (i * angleDifference)), velocity);
        }
    }

    public void opt4Add(int n, double x, double y, double angle, double velocity1, double velocity2){
        double velocityDifference = (velocity2 - velocity1) / (n - 1);
        for (int i = 0; i < n; i++){
            opt1Add(x, y, angle, (velocity1 + (i * velocityDifference)));
        }
    }

    public void addWall(double x1, double y1, double x2, double y2){
        Wall wall = new Wall(x1, y1, x2, y2);
        walls.add(wall);
        wall.setBounds(0,0, 1280,720);
        this.add(wall);
        repaint();
    }

    public void updateSimulation(){
        for (Particle particle : particles) {
            particle.updatePosition(0.1, new ArrayList<Wall>(walls));
        }

        SwingUtilities.invokeLater(() -> {
            repaint();
        });
    }
}
