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
	
	public Color getOutputColorInPoint(Point point, Scene scene)
	{
		float r = 0;
		float g = 0;
		float b = 0;
		for (Light light: scene.lights)
		{
			
			Vector v = new Vector(light.getPosition().getX() - point.getX(), light.getPosition().getY() - point.getY(), light.getPosition().getZ() - point.getZ());
			Ray ray = new Ray(point, v);
			
			r += Math.abs(light.getReflectedIntensityR(point, this, ray));
			g += Math.abs(light.getReflectedIntensityG(point, this, ray));
			b += Math.abs(light.getReflectedIntensityB(point, this, ray));
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
		return new Color(r, g, b);
	}
	
	public abstract Vector getNormal(Point p);
	
	
}
