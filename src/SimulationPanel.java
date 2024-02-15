import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class SimulationPanel extends JPanel{
    private List<Particle> particles;
    private List<Wall> walls;
    private final int SIMULATION_WIDTH = 1280;
    private final int SIMULATION_HEIGHT = 720;
    private final int BALL_RADIUS = 10;

    public SimulationPanel(){
        setBounds(50, 50, SIMULATION_WIDTH, SIMULATION_HEIGHT);
        setBackground(Color.WHITE);
    }

    public void opt1Add(double x, double y, double angle, double velocity){
        particles.add(new Particle(x, y, velocity, angle));
    }

    public void opt2Add(int n, double x1, double y1, double x2, double y2, double angle, double velocity){

    }

    public void opt3Add(int n, double x, double y, double angle1, double angle2, double velocity){

    }

    public void opt4Add(int n, double x, double y, double angle, double velocity1, double velocity2){

    }
}
