package Objects;

import java.awt.Color;

public class Light {
	private Point position;
	private Color color;
	private double specularIntensity;
	private double shadowIntensity;
	private double lightRadius;
	
	
	
	public Light(Point position, Color color, double specularIntensity, double shadowIntensity, double lightRadius) {
		super();
		this.position = position;
		this.color = color;
		this.specularIntensity = specularIntensity;
		this.shadowIntensity = shadowIntensity;
		this.lightRadius = lightRadius;
	}
	
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getSpecularIntensity() {
		return specularIntensity;
	}
	public void setSpecularIntensity(double specularIntensity) {
		this.specularIntensity = specularIntensity;
	}
	public double getShadowIntensity() {
		return shadowIntensity;
	}
	public void setShadowIntensity(double shadowIntensity) {
		this.shadowIntensity = shadowIntensity;
	}
	public double getLightRadius() {
		return lightRadius;
	}
	public void setLightRadius(double lightRadius) {
		this.lightRadius = lightRadius;
	}
	
	
}
