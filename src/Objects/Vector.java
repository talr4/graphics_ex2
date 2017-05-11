package Objects;

public class Vector {
	private double x;
	private double y;
	private double z;
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		normalize();
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
	
	public double length(){
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2)+ Math.pow(getZ(), 2));
	}
	
	public void normalize(){
		double length = length();
		setX(getX() / length);
		setY(getY() / length);
		setZ(getZ() / length);
	}
	
	public double dotProduct(Vector other){
		return getX()*other.getX() + getY()*other.getY() + getZ()*other.getZ();
	}
	
	public double dotProductWithPoint(Point other){
		return getX()*other.getX() + getY()*other.getY() + getZ()*other.getZ();
	}
	
	public Vector crossProduct(Vector other){
		double newX = getY()*other.getZ() - getZ()*other.getY();
		double newY = getZ()*other.getX() - getX()*other.getZ();
		double newZ = getX()*other.getY() - getY()*other.getX();
		return new Vector(newX,newY,newZ);
	}
	
	public Vector add(Vector other){
		double newX = getX()+other.getX();
		double newY = getY()+other.getY();
		double newZ = getZ()+other.getZ();
		return new Vector(newX,newY,newZ);
	}
	
	public Vector multiply(double scalar){
		double newX = getX()*scalar;
		double newY = getY()*scalar;
		double newZ = getZ()*scalar;
		return new Vector(newX,newY,newZ);
	}
	
}
