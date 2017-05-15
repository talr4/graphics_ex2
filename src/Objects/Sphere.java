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
	
	@Override
	public  Point findClosestIntesectionWithRay(Ray ray)
	{
		Point L = new Point (center.getX() - ray.getPoint().getX(), center.getY() - ray.getPoint().getY(), center.getZ() - ray.getPoint().getZ());
		double tca = ray.getVector().dotProductWithPoint(L);
		if(tca < 0)
		{
			return null;
		}
		
		double squaredD = L.dotProduct(L) - (tca*tca);
		if(squaredD > this.radius*this.radius)
		{
			return null;
		}
		
		double thc = Math.sqrt(this.radius*this.radius - squaredD);
		
		double t = tca - thc;
		
		return ray.getPointOnRayByDistance(t);
	}

	
	
	
}
