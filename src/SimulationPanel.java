import java.awt.Color;
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
}
