package Objects;

import java.awt.Color;

import RayTracing.RayTracer;

public abstract class Surface {
	
	Material material;
	float epsilon = (float)0.0001;

	public Surface(Material material) {
		super();
		this.material = material;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public abstract Point findClosestIntesectionWithRay(Ray ray);
	
	public Color getOutputColor(Color backgroundColor)
	{
		
		return new Color(material.getDr(), material.dg, material.db);
	}
	
	public Objects.Color getOutputColorInPoint(Point point, Scene scene, Ray ray, RayTracer rayTracer, int recursionStep)
	{
		Point normal = new Point(getNormal(point).getX(), getNormal(point).getY(), getNormal(point).getZ());
		Point smallAddOn = normal.multiply(epsilon);
		if(this instanceof Sphere){
			point = point.add(smallAddOn);
		}else{
			point = point.add(smallAddOn.multiply(-1));
		}
		
		float r = 0;
		float g = 0;
		float b = 0;
		for (Light light: scene.lights)
		{
			
			Vector v = new Vector(light.getPosition().getX() - point.getX(), light.getPosition().getY() - point.getY(), light.getPosition().getZ() - point.getZ());
			
			Ray rayToLight = new Ray(point, v);
				
			float Ir = Math.abs(light.getDiffuseColorR(point, this, rayToLight)) +  Math.abs(light.getSpecularColorR(point, this, rayToLight, ray));
			float Ig = Math.abs(light.getDiffuseColorG(point, this, rayToLight)) +  Math.abs(light.getSpecularColorG(point, this, rayToLight, ray));
			float Ib = Math.abs(light.getDiffuseColorB(point, this, rayToLight)) +  Math.abs(light.getSpecularColorB(point, this, rayToLight, ray));
			float softShadows;
			if(scene.getShadowRaysNumber() == 1)
			{
				softShadows = light.computeHardShadows(scene, rayToLight, point);
			}
			else
			{
				softShadows = light.computeSoftShadowsCoef(scene, point, this, rayToLight);
			}
			
			
			r += (Ir*softShadows);
			g += (Ig*softShadows);
			b += (Ib*softShadows);
				
			

		}
		
		// Added recursive color from reflected and refracted rays
		Objects.Color nextSurfaceReflectedInteractionColor = null;
		Objects.Color nextSurfaceRefractedInteractionColor = null;
		if(recursionStep < scene.getMaxRecursionLevel()){
			if(getMaterial().getRr() != 0 || getMaterial().getRg() != 0 || getMaterial().getRb() != 0){
				Ray reflectedRay = getReflectedRay(ray, point);
				Intersection reflectionIntersaection =  rayTracer.getIntersectionFromRay(reflectedRay);
				if(reflectionIntersaection != null){
					nextSurfaceReflectedInteractionColor = reflectionIntersaection.getSurface().getOutputColorInPoint(reflectionIntersaection.getPoint(), scene, reflectedRay, rayTracer, recursionStep+1);
				}else{
					nextSurfaceReflectedInteractionColor = scene.getBackgroundColor();
				}
			}
			if(getMaterial().getTransparency() != 0){
				Ray refractedRay = getRefractedRay(ray, point);
				Intersection refractedIntersaection =  rayTracer.getIntersectionFromRay(refractedRay);
				if(refractedIntersaection != null){
					nextSurfaceRefractedInteractionColor = refractedIntersaection.getSurface().getOutputColorInPoint(refractedIntersaection.getPoint(), scene, refractedRay, rayTracer, recursionStep+1);
				}else{
					nextSurfaceRefractedInteractionColor = scene.getBackgroundColor();
				}
			}
		}else{
			nextSurfaceReflectedInteractionColor = scene.getBackgroundColor();
			nextSurfaceRefractedInteractionColor = scene.getBackgroundColor();
		}
		
		if(nextSurfaceReflectedInteractionColor != null){
			r += getMaterial().getRr()*nextSurfaceReflectedInteractionColor.getRed();
			g += getMaterial().getRg()*nextSurfaceReflectedInteractionColor.getGreen();
			b += getMaterial().getRb()*nextSurfaceReflectedInteractionColor.getBlue();
		}
		if(nextSurfaceRefractedInteractionColor != null){
			r += (1-getMaterial().getTransparency())*nextSurfaceRefractedInteractionColor.getRed();
			g += (1-getMaterial().getTransparency())*nextSurfaceRefractedInteractionColor.getGreen();
			b += (1-getMaterial().getTransparency())*nextSurfaceRefractedInteractionColor.getBlue();
		}
		
		if(g > 1)
		{
			g = 1;
		}
		if(r > 1)
		{
			r = 1;
		}
		if(b > 1)
		{
			b = 1;
		}
		return new Objects.Color(r, g, b);
	}
	
	public abstract Vector getNormal(Point p);
	
	public Ray getReflectedRay(Ray ray ,Point p){
		Vector Lminus = ray.getVector().multiply(-1);
		Point L = new Point(Lminus.getX(), Lminus.getY(), Lminus.getZ());
		Point N = new Point(getNormal(p).getX(), getNormal(p).getY(), getNormal(p).getZ());
		Point R = N.multiply(N.dotProduct(L.multiply(2))).add(L.multiply(-1));
		Vector direction = new Vector(R.getX(), R.getY(), R.getZ());
		return new Ray(p,direction);
	}
	
	public Ray getRefractedRay(Ray ray, Point p) {
		Ray refractedWithoutEpsilon = new Ray(p, ray.getVector());
		Point withEpsilon = refractedWithoutEpsilon.getPointOnRayByDistance(epsilon*10);
		return new Ray(withEpsilon, ray.getVector());
	}
	
}
