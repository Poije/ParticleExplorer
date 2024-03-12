import javax.swing.JComponent;
import java.awt.*;

public class Explorer extends JComponent {
    public double x_coord;
    public double y_coord;

    public Explorer(double x, double y){
        this.x_coord = x;
        this.y_coord = y;
        repaint();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval((int) x_coord, (int) y_coord, 20, 20);
    }
    
}
