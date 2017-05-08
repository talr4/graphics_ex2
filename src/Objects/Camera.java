package Objects;

import java.awt.*;

public class Camera {
	private Vector Position;
	private Vector lookAt;
	private Vector up;
	private double screenDistance;
	private double screenWidth;
	
	public Vector getPosition() {
		return Position;
	}
	public void setPosition(Vector position) {
		Position = position;
	}
	public Vector getLookAt() {
		return lookAt;
	}
	public void setLookAt(Vector lookAt) {
		this.lookAt = lookAt;
	}
	public Vector getUp() {
		return up;
	}
	public void setUp(Vector up) {
		this.up = up;
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
