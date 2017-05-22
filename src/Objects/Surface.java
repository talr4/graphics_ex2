package Objects;

import java.awt.Color;

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
	
	public Color getOutputColorInPoint(Point point, Scene scene, Ray rayFromViewer)
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
			}

		}
		return new Color(r, g, b);
	}
	
	public abstract Vector getNormal(Point p);
	
	
}
