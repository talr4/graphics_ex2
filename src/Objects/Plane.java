package Objects;

public class Plane extends Surface{
	
	private Vector normal;
	private float offset;
	
	public Vector getNormal() {
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
	
	
}
