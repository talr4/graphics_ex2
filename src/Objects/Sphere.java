package Objects;

public class Sphere extends Surface {
	
	private Point center;
	private float radius;	
	
	public Sphere(Point center, float radius, Material material) {
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
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	@Override
	public Vector getNormal(Point p)
	{
		Vector normal = new Vector(p.getX() - center.getX(), p.getY() - center.getY(), p.getZ() - center.getZ());
		
		return normal;
	}
	
	@Override
	public  Point findClosestIntesectionWithRay(Ray ray)
	{
		Point L = new Point (center.getX() - ray.getPoint().getX(), center.getY() - ray.getPoint().getY(), center.getZ() - ray.getPoint().getZ());
		float tca = ray.getVector().dotProductWithPoint(L);
		if(tca < 0)
		{
			return null;
		}
		
		float squaredD = L.dotProduct(L) - (tca*tca);
		if(squaredD > this.radius*this.radius)
		{
			return null;
		}
		
		float thc = (float) Math.sqrt(this.radius*this.radius - squaredD);		
		float t = tca - thc;
		
		if ( t < 0 )
		{
			return null; // can't be seen by the camera
		}
		return ray.getPointOnRayByDistance(t);
	}
	
}
