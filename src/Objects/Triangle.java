package Objects;

public class Triangle extends Surface {
	
	private Point vertex1;
	private Point vertex2;
	private Point vertex3;
	
	
	
	public Triangle(Point vertex1, Point vertex2, Point vertex3, Material material) {
		super(material);
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.vertex3 = vertex3;
	}
	
	public Point getVertex1() {
		return vertex1;
	}
	public void setVertex1(Point vertex1) {
		this.vertex1 = vertex1;
	}
	public Point getVertex2() {
		return vertex2;
	}
	public void setVertex2(Point vertex2) {
		this.vertex2 = vertex2;
	}
	public Point getVertex3() {
		return vertex3;
	}
	public void setVertex3(Point vertex3) {
		this.vertex3 = vertex3;
	}

	@Override
	public Point findClosestIntesectionWithRay(Ray ray) {
		Vector v1 = new Vector(this.vertex1.getX() - ray.getPoint().getX(), this.vertex1.getY() - ray.getPoint().getY(), this.vertex1.getZ() - ray.getPoint().getZ());
		Vector v2 = new Vector(this.vertex2.getX() - ray.getPoint().getX(), this.vertex2.getY() - ray.getPoint().getY(), this.vertex2.getZ() - ray.getPoint().getZ());
		Vector N1 = v1.crossProduct(v2);
		
		
		
		
		
		return null;
	}
	
	

}
