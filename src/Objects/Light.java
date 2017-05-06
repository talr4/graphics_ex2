package Objects;

import java.awt.Color;

public class Light {
	private Vector position;
	private Color color;
	private float specularIntensity;
	private float shadowIntensity;
	private float lightRadius;
	
	public Vector getPosition() {
		return position;
	}
	public void setPosition(Vector position) {
		this.position = position;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public float getSpecularIntensity() {
		return specularIntensity;
	}
	public void setSpecularIntensity(float specularIntensity) {
		this.specularIntensity = specularIntensity;
	}
	public float getShadowIntensity() {
		return shadowIntensity;
	}
	public void setShadowIntensity(float shadowIntensity) {
		this.shadowIntensity = shadowIntensity;
	}
	public float getLightRadius() {
		return lightRadius;
	}
	public void setLightRadius(float lightRadius) {
		this.lightRadius = lightRadius;
	}
	
	
}
