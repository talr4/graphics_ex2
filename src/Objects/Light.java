package Objects;

import java.awt.Color;

public class Light {
	private Point position;
	private float r;
	private float g;
	private float b;
	private float specularIntensity;
	private float shadowIntensity;
	private float lightRadius;

	public Light(Point position, float r, float g, float b, float specularIntensity, float shadowIntensity,
			float lightRadius) {
		super();
		this.position = position;
		this.r = r;
		this.g = g;
		this.b = b;
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

	public float getReflectedIntensityR(Point point, Surface surface, Ray ray) {
		return surface.getMaterial().getDr() * this.getR() * (surface.getNormal(point).dotProduct(ray.getVector()));

	}

	public float getReflectedIntensityG(Point point, Surface surface, Ray ray) {

		return surface.getMaterial().getDg() * this.getG() * (surface.getNormal(point).dotProduct(ray.getVector()));

	}

	public float getReflectedIntensityB(Point point, Surface surface, Ray ray) {

		return surface.getMaterial().getDb() * this.getB() * (surface.getNormal(point).dotProduct(ray.getVector()));

	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

}
