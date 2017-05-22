package Objects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import RayTracing.RayTracer;


import javax.imageio.ImageIO;

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

	public float getDiffuseColorR(Point point, Surface surface, Ray rayToLight) {
		return surface.getMaterial().getDr() * this.getR() * (surface.getNormal(point).dotProduct(rayToLight.getVector()));

	}

	public float getDiffuseColorG(Point point, Surface surface, Ray rayToLight) {

		return surface.getMaterial().getDg() * this.getG() * (surface.getNormal(point).dotProduct(rayToLight.getVector()));

	}

	public float getDiffuseColorB(Point point, Surface surface, Ray rayToLight) {

		return surface.getMaterial().getDb() * this.getB() * (surface.getNormal(point).dotProduct(rayToLight.getVector()));

	}
	
	public float getSpecularColorR(Point point, Surface surface, Ray rayToLight, Ray rayFromViewer) 
	{
		Ray R = surface.getReflectedRay(rayToLight,point);

		return (float) (surface.getMaterial().sr * Math.pow(rayFromViewer.getVector().dotProduct(R.getVector()), surface.getMaterial().getPhong())*this.getR()*specularIntensity);
	}
	
	public float getSpecularColorG(Point point, Surface surface, Ray rayToLight, Ray rayFromViewer) 
	{
		Ray R = surface.getReflectedRay(rayToLight,point);

		return (float) (surface.getMaterial().sg * Math.pow(rayFromViewer.getVector().dotProduct(R.getVector()), surface.getMaterial().getPhong())*this.getG()*specularIntensity);
	}
	
	public float getSpecularColorB(Point point, Surface surface, Ray rayToLight, Ray rayFromViewer) 
	{
		Ray R = surface.getReflectedRay(rayToLight,point);

		return (float) (surface.getMaterial().sb * Math.pow(rayFromViewer.getVector().dotProduct(R.getVector()), surface.getMaterial().getPhong())*this.getB()*specularIntensity);
	}
	
	public float computeSoftShadowsCoef(Scene scene, Point point, Surface surface, Ray rayToLight)
	{
		int N = scene.getShadowRaysNumber();
		Vector v = new Vector(2,6,7);
		
		Vector vertical = v.crossProduct(rayToLight.getVector());
		
		float distance = position.FindDistanceFromPoint(point);
		
		Screen screen = RayTracer.calculateScreen(this.position, point, this.lightRadius, distance, vertical, scene.getShadowRaysNumber(), scene.getShadowRaysNumber());
		
		ArrayList<Ray> rays = new ArrayList<Ray>();
		int counter = 0;
		for (int i = 0; i < scene.getShadowRaysNumber(); i++) {
			for (int j = 0; j < scene.getShadowRaysNumber(); j++) {
				
				float xNoise = random.nextFloat() * (screen.getPixelSize());
				float yNoise = random.nextFloat() * (screen.getPixelSize());
				
				Ray right = new Ray(screen.getUpperLeft(), screen.getHorizontal());
				Point screenRight = right.getPointOnRayByDistance(screen.getPixelSize() * i + xNoise);
				
				Ray down = new Ray(screenRight, screen.getVertical());
				Point screenPoint = down.getPointOnRayByDistance(screen.getPixelSize() * j + yNoise);
				
				Vector direction = new Vector(position, point);
				Ray ray = new Ray(position, direction);
				
				boolean isInterfered = false;
				for (Surface surface1 : scene.getSurfaces())
				{
					if ( surface1.findClosestIntesectionWithRay(rayToLight) != null)
					{
						counter++;
						break;
					}
				}
			}
		}
		
		return counter / (N*N);
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
