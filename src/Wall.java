
public class Wall {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double angle; //in radians already
    
    public Wall(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }
        this.angle = angle;
    }

    public double getX1() {
        return x1;
    }
    public double getY1() {
        return y1;
    }
    public double getX2() {
        return x2;
    }
    public double getY2() {
        return y2;
    }
    public double getAngle() {
        return angle;
    }
}
