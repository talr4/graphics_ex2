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
		double L = center.FindDistanceFromPoint(ray.getPoint());
		Point Middle = ray.getPointOnRayByDistance(L);
		double tca = ray.getPoint().FindDistanceFromPoint(Middle);
		if(tca < 0)
		{
			return null;
		}
		
		double squaredD = (L*L) - (tca*tca);
		if(squaredD > this.radius*this.radius)
		{
			return null;
		}
		
		double thc = this.radius*this.radius - squaredD;
		
		double t = tca - thc;
		
		return ray.getPointOnRayByDistance(t);
	}

	
	
	
}
