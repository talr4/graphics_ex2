package Objects;

import java.awt.*;

public class Camera {
	private Vector Position;
	private Vector lookAt;
	private Vector up;
	private Screen screen;

	
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
	public Screen getScreen() {
		return screen;
	}
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	
	

	
	
}
