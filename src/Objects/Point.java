package Objects;

public class Point {
	private float x;
	private float y;
	private float z;
	
	public Point() {}
	
	public Point(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	
	public Point add(Point other){
		float newX = getX()+other.getX();
		float newY = getY()+other.getY();
		float newZ = getZ()+other.getZ();
		return new Point(newX,newY,newZ);
	}
	
	public Point multiply(float scalar){
		float newX = getX()*scalar;
		float newY = getY()*scalar;
		float newZ = getZ()*scalar;
		return new Point(newX,newY,newZ);
	}
	
	public float FindDistanceFromPoint(Point p)
	{
		return (float)Math.sqrt((this.x - p.x)*(this.x - p.x) + (this.y - p.y)*(this.y - p.y) + (this.z-p.z)*(this.z - p.z));
		
	}
	
	public float dotProduct(Point other){
		return getX()*other.getX() + getY()*other.getY() + getZ()*other.getZ();
	}
	
}
