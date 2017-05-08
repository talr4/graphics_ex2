package Objects;
import java.awt.*;


public class Material {
	
	private Color diffuseColor;
	private Color specularColor;
<<<<<<< HEAD
	private Color ReflectionColor;
	private float phong;
	private float transparency;
=======
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
	
	
>>>>>>> parent of 6732a86... fix colisions
	
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
<<<<<<< HEAD
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
=======
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
>>>>>>> parent of 6732a86... fix colisions
		this.transparency = transparency;
	}
	
	
	
}
