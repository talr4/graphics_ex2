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
		for (Light light: scene.lights)
		{
			Vector v = new Vector(light.getPosition().getX() - point.getX(), light.getPosition().getY() - point.getY(), light.getPosition().getZ() - point.getZ());
			Ray ray = new Ray(point, v);
			
			float r = light.getReflectedIntensityR(point, this, ray);
			float g = light.getReflectedIntensityG(point, this, ray);
			float b = light.getReflectedIntensityB(point, this, ray);
		
			
		}
		return new Color(material.getDr(), material.dg, material.db);
	}
	
	public abstract Vector getNormal(Point p);
	
	
}
