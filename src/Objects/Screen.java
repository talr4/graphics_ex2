package Objects;

public class Screen {
	Point upperLeft; //Left up point
	Vector horizontal;
	Vector vertical;
	private float pixelSize;
	
	public Screen(Point upperLeft, Vector horizontal, Vector vertical, float pixelSize) {
		super();
		this.upperLeft = upperLeft;
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.pixelSize = pixelSize;
	}
	public Point getUpperLeft() {
		return upperLeft;
	}
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
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
	public float getPixelSize() {
		return pixelSize;
	}
	public void setPixelSize(float pixelSize) {
		this.pixelSize = pixelSize;
	}
	
	
}
