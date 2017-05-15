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
		
		// Find plane containing the triangle
		Vector vector1 = new Vector(vertex3.getX() - vertex1.getX(), vertex3.getY() - vertex1.getY(), vertex3.getZ() - vertex1.getZ());
		Vector vector2 = new Vector(vertex3.getX() - vertex2.getX(), vertex3.getY() - vertex2.getY(), vertex3.getZ() - vertex2.getZ());
		Vector normal = vector1.crossProduct(vector2);
		double offset = normal.dotProductWithPoint(vertex1);
		
		Plane plane = new Plane(normal, offset, material);
		
		// Find intersction with plane
		Point intersection = plane.findClosestIntesectionWithRay(ray);
		if(intersection == null)
		{
			return null;
		}
		
		// Check if the intersection point with the plane is inside the triangle
		Vector v1 = new Vector(this.vertex1.getX() - ray.getPoint().getX(), this.vertex1.getY() - ray.getPoint().getY(), this.vertex1.getZ() - ray.getPoint().getZ());
		Vector v2 = new Vector(this.vertex2.getX() - ray.getPoint().getX(), this.vertex2.getY() - ray.getPoint().getY(), this.vertex2.getZ() - ray.getPoint().getZ());
		Vector N1 = v1.crossProduct(v2);
		Point check = new Point(intersection.getX() - ray.getPoint().getX(), intersection.getY() - ray.getPoint().getY(), intersection.getZ() - ray.getPoint().getZ());
		if(N1.dotProductWithPoint(check) < 0)
		{
			return null;
		}
		
		return intersection;
	}
	
	

}
