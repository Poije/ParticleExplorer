import javax.swing.JComponent;
import java.awt.*;

public class Explorer extends JComponent {
    public double x_coord;
    public double y_coord;
    public boolean setClick;

    public Explorer(double x, double y, boolean setClick){
        this.x_coord = x;
        this.y_coord = y;
        this.setClick = setClick;
        repaint();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        if (setClick){
            g.fillOval((int) x_coord,(int) y_coord, 100, 100);
            setClick = false;
        }
        else{
            g.fillOval((int) x_coord, 720 - (int) y_coord, 100, 100);
        }
    }
    
}
