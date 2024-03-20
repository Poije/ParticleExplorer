import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SimulationPanel extends JPanel{
    private List<Particle> particles = Collections.synchronizedList(new ArrayList<Particle>());
    private final int SIMULATION_WIDTH = 1280;
    private final int SIMULATION_HEIGHT = 720;
    private final int THREAD_COUNT = 8;

    //private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    private final ExecutorService executorService = Executors.newWorkStealingPool();

    public static int frameCount = 0;
    public static int previousFPS = 0;
    public long lastFPSCheck = 0;

    private int zoom = 1;
    private double xOffset = 0;
    private double yOffset = 0;
    public Explorer explorer;
    private boolean isDevMode = true;

    public SimulationPanel(){
        setBounds(50, 50, SIMULATION_WIDTH, SIMULATION_HEIGHT);
        setBackground(Color.WHITE);
        setFocusable(true);
        initializeListeners();
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
        //updateSimulation();
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

    public void addExplorer(double x, double y){
        if (explorer != null){
            this.remove(explorer);
        }
        explorer = new Explorer(x, y);
        explorer.setBounds(0,0, 1280,720);
        this.add(explorer);
        zoom = 5;
        xOffset = x * zoom - getWidth() / 5;
        yOffset = y * zoom - getHeight() / 5;
        SwingUtilities.invokeLater(this::repaint);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFPSCheck >= 500){
            frameCount = (int) (frameCount / ((currentTime - lastFPSCheck) / 1000.0));
            g.drawString("FPS: " + frameCount, 10, 20);
            previousFPS = frameCount;
            frameCount = 0;
            lastFPSCheck = currentTime;
        }
        else{
            g.drawString("FPS: " + previousFPS, 10, 20);
        }    
    }

    public void updateSimulation(){
        synchronized (this.particles){
            for (Particle particle : this.particles) {
                particle.updatePosition(0.1, zoom);
            }
        }
        if (explorer != null) {
            // Adjust offset to follow explorer's movement
            xOffset = explorer.x_coord * zoom - getWidth() / zoom;
            yOffset = explorer.y_coord * zoom - getHeight() / zoom;
        }
        SwingUtilities.invokeLater(this::repaint);
        frameCount++;
    }

    public void changeDevMode(boolean isDev){
        this.isDevMode = isDev;
    }

    private void initializeListeners() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (!isDevMode) { 
                    double x = e.getX();
                    double y = e.getY();
                    requestFocusInWindow();
                    addExplorer(x, y);
                }
            }
        });

        // Key listener
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!isDevMode && explorer != null) { 
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W:
                            explorer.y_coord -= 5;
                            break;
                        case KeyEvent.VK_A:
                            explorer.x_coord -= 5;
                            break;
                        case KeyEvent.VK_S:
                            explorer.y_coord += 5;
                            break;
                        case KeyEvent.VK_D:
                            explorer.x_coord += 5;
                            break;
                    }
                }
            }
        });
    }

    private boolean isParticleInPeriphery(Particle particle) {
        // Implement logic to determine if the particle is within the periphery
        // This could involve checking the particle's coordinates against the explorer's location
        return true; // Placeholder return value
    }
}
