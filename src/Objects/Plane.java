package Objects;

public class Plane extends Surface{
	
	private Vector normal;
	private float offset;
	
	
	
	public Plane(Vector normal, float offset, Material material) {
		super(material);
		this.normal = normal.multiply(-1);
		this.offset = offset;
	}
	
	@Override
	public Vector getNormal(Point p) {
		return normal;
	}
	public void setNormal(Vector normal) {
		this.normal = normal;
	}
	public float getOffset() {
		return offset;
	}
	public void setOffset(float offset) {
		this.offset = offset;
	}

	@Override
	public Point findClosestIntesectionWithRay(Ray ray) {
		if(this.normal.dotProduct(ray.getVector()) == 0)
		{
			return null;
		}
		float t = -(this.normal.dotProductWithPoint(ray.getPoint())+this.offset)/(this.normal.dotProduct(ray.getVector()));
		
		if ( t < 0 )
		{
			return null; // can't be seen by the camera
		}
		
		return ray.getPointOnRayByDistance(t);
	}
	
	
	
	
}
