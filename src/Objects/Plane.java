package Objects;

public class Plane extends Surface{
	
	private Vector normal;
	private double offset;
	
	
	
	public Plane(Vector normal, double offset, Material material) {
		super(material);
		this.normal = normal;
		this.offset = offset;
	}
	
	public Vector getNormal() {
		return normal;
	}
	public void setNormal(Vector normal) {
		this.normal = normal;
	}
	public double getOffset() {
		return offset;
	}
	public void setOffset(double offset) {
		this.offset = offset;
	}

	@Override
	public Point findClosestIntesectionWithRay(Ray ray) {
		if(this.normal.dotProduct(ray.getVector()) == 0)
		{
			return null;
		}
		double t = -(this.normal.dotProductWithPoint(ray.getPoint())+this.offset)/(this.normal.dotProduct(ray.getVector()));
		return ray.getPointOnRayByDistance(t);
	}
	
	
	
	
}
