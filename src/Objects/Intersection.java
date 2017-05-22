package Objects;

public class Intersection {

	private Surface surface;
	private Point point;
	
	public Intersection(Surface surface, Point point) {
		this.surface = surface;
		this.point = point;
	}
	public Surface getSurface() {
		return surface;
	}
	public void setSurface(Surface surface) {
		this.surface = surface;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	
	
	
}
