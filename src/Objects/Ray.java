package Objects;

public class Ray {
	private Point point;
	private Vector vector;
	
	public Ray(Point point, Vector vector) {
		super();
		this.point = point;
		this.vector = vector;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public Vector getVector() {
		return vector;
	}
	public void setVector(Vector vector) {
		this.vector = vector;
	}
	
	public Point getPointOnRayByDistance(double distance){
		Point p = new Point(vector.getX(),vector.getY(),vector.getZ());
		return point.add(p.multiply(distance));
	}
	
}
