package Objects;
import java.awt.*;


public class Material {
	
	private Color diffuseColor;
	private Color specularColor;
	private Color reflectionColor;
	private double phong;
	private double transparency;
	
	
	
	public Material(Color diffuseColor, Color specularColor, Color reflectionColor, double phong, double transparency) {
		
		this.diffuseColor = diffuseColor;
		this.specularColor = specularColor;
		this.reflectionColor = reflectionColor;
		this.phong = phong;
		this.transparency = transparency;
		
	}
	
	
	
	// Getters and Setters
	public Color getDiffuseColor() {
		return diffuseColor;
	}
	public void setDiffuseColor(Color diffuseColor) {
		this.diffuseColor = diffuseColor;
	}
	public Color getSpecularColor() {
		return specularColor;
	}
	public void setSpecularColor(Color specularColor) {
		this.specularColor = specularColor;
	}
	public Color getReflectionColor() {
		return reflectionColor;
	}
	public void setReflectionColor(Color reflectionColor) {
		this.reflectionColor = reflectionColor;
	}
	public double getPhong() {
		return phong;
	}
	public void setPhong(double phong) {
		this.phong = phong;
	}
	public double getTransparency() {
		return transparency;
	}
	public void setTransparency(double transparency) {
		this.transparency = transparency;
	}
	
	
	
}
