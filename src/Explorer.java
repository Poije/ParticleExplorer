import javax.swing.JComponent;
import java.awt.*;

public class Explorer extends JComponent {
    public double x_coord;
    public double y_coord;
    public double real_y;
    public boolean setClick = true;

    public Explorer(double x, double y, boolean setClick){
        this.x_coord = x;
        this.y_coord = y;
        repaint();
    }

    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval((int) x_coord, (int) y_coord, 100, 100);
        
    }
    
}
