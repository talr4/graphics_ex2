package Objects;

import java.awt.*;

public class Camera {
	private Vector Position;
	private Vector lookAt;
	private Vector up;
	private float screenDistance;
	private float screenWidth;
	
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
	public float getScreenDistance() {
		return screenDistance;
	}
	public void setScreenDistance(float screenDistance) {
		this.screenDistance = screenDistance;
	}
	public float getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(float screenWidth) {
		this.screenWidth = screenWidth;
	}	
	
	
}
