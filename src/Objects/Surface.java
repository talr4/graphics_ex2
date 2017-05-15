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
		int r = material.getDiffuseColor().getRed();
		int g = material.getDiffuseColor().getGreen();
		int b = material.getDiffuseColor().getBlue();
		
		return new Color(r, g, b);
	}
	
	
}
