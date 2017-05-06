package Objects;

import java.awt.Color;

public class Scene {
	
	private Color backgroundColor;
	private int shadowRaysNumber;
	private int maxRecursionLevel;
	private int superSamplingLevel;
	private Camera camera;
	private Surface[] surfaces;
	
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
	public Surface[] getSurfaces() {
		return surfaces;
	}
	public void setSurfaces(Surface[] surfaces) {
		this.surfaces = surfaces;
	}
	
	
}
