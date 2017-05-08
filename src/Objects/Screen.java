package Objects;

public class Screen {
	Point p; //Left up point
	Vector horizontal;
	Vector vertical;
	private double pixelSize;
	
	public Screen(Point p, Vector horizontal, Vector vertical, double pixelSize) {
		super();
		this.p = p;
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.pixelSize = pixelSize;
	}
	public Point getP() {
		return p;
	}
	public void setP(Point p) {
		this.p = p;
	}
	public Vector getHorizontal() {
		return horizontal;
	}
	public void setHorizontal(Vector horizontal) {
		this.horizontal = horizontal;
	}
	public Vector getVertical() {
		return vertical;
	}
	public void setVertical(Vector vertical) {
		this.vertical = vertical;
	}
	public double getPixelSize() {
		return pixelSize;
	}
	public void setPixelSize(double pixelSize) {
		this.pixelSize = pixelSize;
	}
	
	
}
