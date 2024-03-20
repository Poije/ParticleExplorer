import javax.swing.JComponent;
import java.awt.*;

public class Explorer extends JComponent {
    public double x_coord;
    public double y_coord;

    public Explorer(double x, double y){
        this.x_coord = x;
        this.y_coord = y;
        this.setSize(20, 20); 
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        int radius = 10; 
        g.fillOval((int) x_coord - radius, (int) y_coord - radius, 20, 20);
    }
}
