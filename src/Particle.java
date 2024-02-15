
public class Particle {
    private double x;
    private double y;
    private double velocity;
    private double angle;

    public Particle(double x, double y, double velocity, double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.velocity = velocity;
    }

    public double getX(){
        return x;
    }

    public double getY() {
        return y;
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
        return velocity * Math.cos(Math.toRadians(angle));
    }
}
