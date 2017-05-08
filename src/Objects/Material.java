package Objects;
import java.awt.*;


public class Material {
	
	private Color diffuseColor;
	private Color specularColor;
	private Color ReflectionColor;
	private float phong;
	private float transparency;
	
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
		return ReflectionColor;
	}
	public void setReflectionColor(Color reflectionColor) {
		ReflectionColor = reflectionColor;
	}
	public float getPhong() {
		return phong;
	}
	public void setPhong(float phong) {
		this.phong = phong;
	}
	public float getTransparency() {
		return transparency;
	}
	public void setTransparency(float transparency) {
		this.transparency = transparency;
	}
	
	
	
}
