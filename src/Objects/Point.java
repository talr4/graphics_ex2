package Objects;

public class Point {
	private double x;
	private double y;
	private double z;
	
	public Point() {}
	
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	
	public Point add(Point other){
		double newX = getX()+other.getX();
		double newY = getY()+other.getY();
		double newZ = getZ()+other.getZ();
		return new Point(newX,newY,newZ);
	}
	
	public Point multiply(double scalar){
		double newX = getX()*scalar;
		double newY = getY()*scalar;
		double newZ = getZ()*scalar;
		return new Point(newX,newY,newZ);
	}
	
	public double FindDistanceFromPoint(Point p)
	{
		return Math.sqrt((this.x - p.x)*(this.x - p.x) + (this.y - p.y)*(this.y - p.y) + (this.z-p.z)*(this.y - p.y));
		
	}
	
}
