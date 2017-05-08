package Objects;

import java.awt.*;

public class Camera {
	private Point Position;
	private Point lookAt;
	private Vector up;
	private Screen screen;

	
	public Point getPosition() {
		return Position;
	}
	public void setPosition(Point position) {
		Position = position;
	}
	public Point getLookAt() {
		return lookAt;
	}
	public void setLookAt(Point lookAt) {
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
