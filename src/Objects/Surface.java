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
		float r =(float)( backgroundColor.getRed()*material.getTransparency() + ((material.getDiffuseColor().getRed() + material.getSpecularColor().getRed())*(1-material.getTransparency())) + material.getReflectionColor().getRed());
		float g =(float)( backgroundColor.getGreen()*material.getTransparency() + ((material.getDiffuseColor().getGreen() + material.getSpecularColor().getGreen())*(1-material.getTransparency())) + material.getReflectionColor().getGreen());
		float b =(float)( backgroundColor.getBlue()*material.getTransparency() + ((material.getDiffuseColor().getBlue() + material.getSpecularColor().getBlue())*(1-material.getTransparency())) + material.getReflectionColor().getBlue());
		
		return new Color(r, g, b);
	}
	
	
}
