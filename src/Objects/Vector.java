package Objects;

public class Vector {
	private float x;
	private float y;
	private float z;
	
	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		normalize();
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
	
	public float length(){
		return (float)Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2)+ Math.pow(getZ(), 2));
	}
	
	public void normalize(){
		float length = length();
		setX(getX() / length);
		setY(getY() / length);
		setZ(getZ() / length);
	}
	
	public float dotProduct(Vector other){
		return getX()*other.getX() + getY()*other.getY() + getZ()*other.getZ();
	}
	
	public float dotProductWithPoint(Point other){
		return getX()*other.getX() + getY()*other.getY() + getZ()*other.getZ();
	}
	
	public Vector crossProduct(Vector other){
		float newX = getY()*other.getZ() - getZ()*other.getY();
		float newY = getZ()*other.getX() - getX()*other.getZ();
		float newZ = getX()*other.getY() - getY()*other.getX();
		return new Vector(newX,newY,newZ);
	}
	
	public Vector add(Vector other){
		float newX = getX()+other.getX();
		float newY = getY()+other.getY();
		float newZ = getZ()+other.getZ();
		return new Vector(newX,newY,newZ);
	}
	
	public Vector multiply(float scalar){
		float newX = getX()*scalar;
		float newY = getY()*scalar;
		float newZ = getZ()*scalar;
		return new Vector(newX,newY,newZ);
	}
	
}
