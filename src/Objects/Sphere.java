package Objects;

public class Sphere extends Surface {
	
	private Point center;
	private double radius;	
	
	public Sphere(Point center, double radius, Material material) {
		super(material);
		this.center = center;
		this.radius = radius;
	}
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}

	
	
	
}
