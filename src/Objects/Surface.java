package Objects;

import java.awt.Color;

import RayTracing.RayTracer;

public abstract class Surface {
	
	Material material;
	
	

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
	
	public Color getOutputColorInPoint(Point point, Scene scene, Ray rayFromViewer,RayTracer rayTracer , int recursionStep)
	{
		float r = 0;
		float g = 0;
		float b = 0;
		for (Light light: scene.lights)
		{
			
			Vector v = new Vector(light.getPosition().getX() - point.getX(), light.getPosition().getY() - point.getY(), light.getPosition().getZ() - point.getZ());
			Ray rayToLight = new Ray(point, v);
			
			// check if there is interfering object between the surface and the light
			boolean isInterfered = false;
			for (Surface surface : scene.getSurfaces())
			{
				if ( surface != this && surface.findClosestIntesectionWithRay(rayToLight) != null)
				{
					isInterfered = true;
				}
			}
			
			if (isInterfered == false)
			{
				r += Math.abs(light.getDiffuseColorR(point, this, rayToLight)) +  Math.abs(light.getSpecularColorR(point, this, rayToLight, rayFromViewer));
				g += Math.abs(light.getDiffuseColorG(point, this, rayToLight)) +  Math.abs(light.getSpecularColorG(point, this, rayToLight, rayFromViewer));
				b += Math.abs(light.getDiffuseColorB(point, this, rayToLight)) +  Math.abs(light.getSpecularColorB(point, this, rayToLight, rayFromViewer));
				
			}

		}
		
		//Intersection rayTracer.get
		
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
		return new Color(r, g, b);
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
		return new Ray(p, ray.getVector());
	}
	
}
