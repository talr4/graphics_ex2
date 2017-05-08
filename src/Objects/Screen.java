package Objects;

public class Screen {
	Point p; //Left up point
	Vector horizontal;
	Vector vertical;
	private double screenDistance;
	private double screenWidth;
	
	
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
	public double getScreenDistance() {
		return screenDistance;
	}
	public void setScreenDistance(double screenDistance) {
		this.screenDistance = screenDistance;
	}
	public double getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(double screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	
}
