package Objects;
import java.util.Random;
import RayTracing.RayTracer;

public class Light {
	private Point position;
	private float r;
	private float g;
	private float b;
	private float specularIntensity;
	private float shadowIntensity;
	private float lightRadius;

	public Light(Point position, float r, float g, float b, float specularIntensity, float shadowIntensity,
			float lightRadius) {
		super();
		this.position = position;
		this.r = r;
		this.g = g;
		this.b = b;
		this.specularIntensity = specularIntensity;
		this.shadowIntensity = shadowIntensity;
		this.lightRadius = lightRadius;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public float getSpecularIntensity() {
		return specularIntensity;
	}

	public void setSpecularIntensity(float specularIntensity) {
		this.specularIntensity = specularIntensity;
	}

	public float getShadowIntensity() {
		return shadowIntensity;
	}

	public void setShadowIntensity(float shadowIntensity) {
		this.shadowIntensity = shadowIntensity;
	}

	public float getLightRadius() {
		return lightRadius;
	}

	public void setLightRadius(float lightRadius) {
		this.lightRadius = lightRadius;
	}

	public float getDiffuseColorR(Point point, Surface surface, Ray rayToLight) {
		return surface.getMaterial().getDr() * this.getR() * (surface.getNormal(point).dotProduct(rayToLight.getVector()));

	}

	public float getDiffuseColorG(Point point, Surface surface, Ray rayToLight) {

		return surface.getMaterial().getDg() * this.getG() * (surface.getNormal(point).dotProduct(rayToLight.getVector()));

	}

	public float getDiffuseColorB(Point point, Surface surface, Ray rayToLight) {

		return surface.getMaterial().getDb() * this.getB() * (surface.getNormal(point).dotProduct(rayToLight.getVector()));

	}
	
	public float getSpecularColorR(Point point, Surface surface, Ray rayToLight, Ray rayFromLight, Ray rayFromViewer) 
	{
		
		Ray R = surface.getReflectedRay(rayFromLight,point);

		return (float) (surface.getMaterial().sr * Math.pow(rayFromViewer.getVector().dotProduct(R.getVector()), surface.getMaterial().getPhong())*this.getR()*specularIntensity);
	}
	
	public float getSpecularColorG(Point point, Surface surface, Ray rayToLight, Ray rayFromLight, Ray rayFromViewer) 
	{
		Ray R = surface.getReflectedRay(rayFromLight,point);

		return (float) (surface.getMaterial().sg * Math.pow(rayFromViewer.getVector().dotProduct(R.getVector()), surface.getMaterial().getPhong())*this.getG()*specularIntensity);
	}
	
	public float getSpecularColorB(Point point, Surface surface, Ray rayToLight, Ray rayFromLight, Ray rayFromViewer) 
	{
		Ray R = surface.getReflectedRay(rayFromLight,point);

		return (float) (surface.getMaterial().sb * Math.pow(rayFromViewer.getVector().dotProduct(R.getVector()), surface.getMaterial().getPhong())*this.getB()*specularIntensity);
	}
	
	public float computeSoftShadowsCoef(Scene scene, Point point, Surface surface, Ray rayToLight)
	{
		int N = scene.getShadowRaysNumber();
		Vector v = new Vector(2,6,7);
		
		//Find vector perpendicular to the ray
		Vector vertical = v.crossProduct(rayToLight.getVector());
		
		float distance = position.FindDistanceFromPoint(point);
		
		Screen screen = RayTracer.calculateScreen(point, this.position, this.lightRadius, distance, vertical, scene.getShadowRaysNumber(), scene.getShadowRaysNumber());
		
		float badRaysCounter = 0;
		Random random = new Random();
		for (int i = 0; i < scene.getShadowRaysNumber(); i++) {
			for (int j = 0; j < scene.getShadowRaysNumber(); j++) {
				
				float xNoise = random.nextFloat() * (screen.getPixelSize());
				float yNoise = random.nextFloat() * (screen.getPixelSize());
				
				Ray right = new Ray(screen.getUpperLeft(), screen.getHorizontal());
				Point screenRight = right.getPointOnRayByDistance(screen.getPixelSize() * i + xNoise);
				
				Ray down = new Ray(screenRight, screen.getVertical());
				Point screenPoint = down.getPointOnRayByDistance(screen.getPixelSize() * j + yNoise);
				
				Vector direction = new Vector(screenPoint, point);
				Ray ray = new Ray(screenPoint, direction);				
				
				float badCoef = 0;
				for (Surface surface1 : scene.getSurfaces())
				{
					Point p = surface1.findClosestIntesectionWithRay(ray);
					float intersectionDistace = 0, pointDistance = 0;
					if(p != null)
					{
						intersectionDistace = p.FindDistanceFromPoint(screenPoint);
						pointDistance = point.FindDistanceFromPoint(screenPoint);

					}
					if ( p != null && intersectionDistace < pointDistance)
					{
						
						if(surface1.getMaterial().getTransparency() == 0){
							badCoef = 1;
							break;
						}else{
							badCoef = 1 - ( (1 - badCoef) * (surface1.getMaterial().getTransparency()));
						}
						
					}
				}
				badRaysCounter += badCoef;
			}
		}
		float allRaysCounter = (float)(N*N);
		float goodRaysCounter = (allRaysCounter - badRaysCounter);
		
		return goodRaysCounter / allRaysCounter;
	}
	
	public float computeHardShadows(Scene scene, Surface surface, Ray rayFromLight, Point point)
	{
		float badCoef = 0;
		for (Surface surface1 : scene.getSurfaces())
		{
			Point p = surface1.findClosestIntesectionWithRay(rayFromLight);
			
			if ( p != null && p.FindDistanceFromPoint(position) < point.FindDistanceFromPoint(position))
			{

				if(surface1.getMaterial().getTransparency() == 0){
					badCoef = 1;
					break;
				}else{
					badCoef = 1 - ( (1 - badCoef) * (surface1.getMaterial().getTransparency()));
				}
			}	
		}

		return 1-badCoef;
	}
	
	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

}
