package Objects;

import java.awt.Color;
import java.util.List;

public class Scene {
	
	private Color backgroundColor;
	private int shadowRaysNumber;
	private int maxRecursionLevel;
	private int superSamplingLevel;
	private Camera camera;
	private List<Surface> surfaces;
	private List<Material> materials;
	
	//Getters and Setters
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public int getShadowRaysNumber() {
		return shadowRaysNumber;
	}
	public void setShadowRaysNumber(int shadowRaysNumber) {
		this.shadowRaysNumber = shadowRaysNumber;
	}
	public int getMaxRecursionLevel() {
		return maxRecursionLevel;
	}
	public void setMaxRecursionLevel(int maxRecursionLevel) {
		this.maxRecursionLevel = maxRecursionLevel;
	}
	public int getSuperSamplingLevel() {
		return superSamplingLevel;
	}
	public void setSuperSamplingLevel(int superSamplingLevel) {
		this.superSamplingLevel = superSamplingLevel;
	}
	public Camera getCamera() {
		return camera;
	}
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	public List<Surface> getSurfaces() {
		return surfaces;
	}
	public void setSurfaces(List<Surface> surfaces) {
		this.surfaces = surfaces;
	}
	public List<Material> getMaterials() {
		return materials;
	}
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	
	
	
}
