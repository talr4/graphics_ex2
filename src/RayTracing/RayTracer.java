package RayTracing;

import java.awt.*;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import Objects.*;
import Objects.Point;

/**
 * Main class for ray tracing exercise.
 */
public class RayTracer {

	public int imageWidth;
	public int imageHeight;
	public Scene scene;

	/**
	 * Runs the ray tracer. Takes scene file, output image file and image size
	 * as input.
	 */
	public static void main(String[] args) {

		try {

			RayTracer tracer = new RayTracer();

			// Default values:
			tracer.imageWidth = 500;
			tracer.imageHeight = 500;

			if (args.length < 2)
				throw new RayTracerException(
						"Not enough arguments provided. Please specify an input scene file and an output image file for rendering.");

			String sceneFileName = args[0];
			String outputFileName = args[1];

			if (args.length > 3) {
				tracer.imageWidth = Integer.parseInt(args[2]);
				tracer.imageHeight = Integer.parseInt(args[3]);
			}

			// Parse scene file:
			tracer.scene = tracer.parseScene(sceneFileName);

			// Render scene:
			tracer.renderScene(outputFileName);

			// } catch (IOException e) {
			// System.out.println(e.getMessage());
		} catch (RayTracerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Parses the scene file and creates the scene. Change this function so it
	 * generates the required objects.
	 */
	public Scene parseScene(String sceneFileName) throws IOException, RayTracerException {
		FileReader fr = new FileReader(sceneFileName);

		BufferedReader r = new BufferedReader(fr);
		String line = null;
		int lineNum = 0;
		System.out.println("Started parsing scene file " + sceneFileName);

		Scene scene = new Scene();
		scene.materials = new ArrayList<Material>();
		scene.surfaces = new ArrayList<Surface>();
		scene.lights = new ArrayList<Light>();

		while ((line = r.readLine()) != null) {
			line = line.trim();
			++lineNum;

			if (line.isEmpty() || (line.charAt(0) == '#')) { // This line in the
																// scene file is
																// a comment
				continue;
			} else {
				String code = line.substring(0, 3).toLowerCase();
				// Split according to white space characters:
				String[] params = line.substring(3).trim().toLowerCase().split("\\s+");

				if (code.equals("cam")) {

					Point position = new Point();
					position.setX(Float.parseFloat(params[0]));
					position.setY(Float.parseFloat(params[1]));
					position.setZ(Float.parseFloat(params[2]));

					Point lookAt = new Point();
					lookAt.setX(Float.parseFloat(params[3]));
					lookAt.setY(Float.parseFloat(params[4]));
					lookAt.setZ(Float.parseFloat(params[5]));

					Vector up = new Vector(Float.parseFloat(params[6]), Float.parseFloat(params[7]),
							Float.parseFloat(params[8]));

					float screenDistance = (Float.parseFloat(params[9]));
					float screenWidth = (Float.parseFloat(params[10]));

					Screen screen = calculateScreen(position, lookAt, screenWidth, screenDistance, up, imageWidth,
							imageHeight);

					Camera camera = new Camera(position, lookAt, up, screen);
					scene.setCamera(camera);

					System.out.println(String.format("Parsed camera parameters (line %d)", lineNum));
				} else if (code.equals("set")) {
					float bgr = Float.parseFloat(params[0]);
					float bgg = Float.parseFloat(params[1]);
					float bgb = Float.parseFloat(params[2]);
						
					scene.setBgr(bgr);
					scene.setBgg(bgg);
					scene.setBgb(bgb);
					
					scene.setShadowRaysNumber(Integer.parseInt(params[3]));
					scene.setMaxRecursionLevel(Integer.parseInt(params[4]));
					scene.setSuperSamplingLevel(Integer.parseInt(params[5]));

					System.out.println(String.format("Parsed general settings (line %d)", lineNum));
				} else if (code.equals("mtl")) {

					float dr = Float.parseFloat(params[0]);
					float dg = Float.parseFloat(params[1]);
					float db = Float.parseFloat(params[2]);

					float sr = Float.parseFloat(params[3]);
					float sg = Float.parseFloat(params[4]);
					float sb = Float.parseFloat(params[5]);

					float rr = Float.parseFloat(params[6]);
					float rg = Float.parseFloat(params[7]);
					float rb = Float.parseFloat(params[8]);

					float phong = Float.parseFloat(params[9]);
					float transparency = Float.parseFloat(params[10]);

					scene.materials.add(new Material(dr, dg, db, sr, sg, sb, rr, rg, rb, phong, transparency));

					System.out.println(String.format("Parsed material (line %d)", lineNum));
				} else if (code.equals("sph")) {

					Float cx = Float.parseFloat(params[0]);
					Float cy = Float.parseFloat(params[1]);
					Float cz = Float.parseFloat(params[2]);
					Point center = new Point(cx, cy, cz);

					float radius = Float.parseFloat(params[3]);

					int matIndex = Integer.parseInt(params[4]);
					Material material = scene.materials.get(matIndex - 1);

					Sphere sphere = new Sphere(center, radius, material);
					scene.surfaces.add(sphere);

					System.out.println(String.format("Parsed sphere (line %d)", lineNum));
				} else if (code.equals("pln")) {
					Float nx = Float.parseFloat(params[0]);
					Float ny = Float.parseFloat(params[1]);
					Float nz = Float.parseFloat(params[2]);
					Vector normal = new Vector(nx, ny, nz);

					float offset = Float.parseFloat(params[3]);

					int matIndex = Integer.parseInt(params[4]);
					Material material = scene.materials.get(matIndex - 1);

					Plane plane = new Plane(normal, offset, material);
					scene.surfaces.add(plane);

					System.out.println(String.format("Parsed plane (line %d)", lineNum));
				} else if (code.equals("trg")) {
					Float v1x = Float.parseFloat(params[0]);
					Float v1y = Float.parseFloat(params[1]);
					Float v1z = Float.parseFloat(params[2]);
					Point v1 = new Point(v1x, v1y, v1z);

					Float v2x = Float.parseFloat(params[3]);
					Float v2y = Float.parseFloat(params[4]);
					Float v2z = Float.parseFloat(params[5]);
					Point v2 = new Point(v2x, v2y, v2z);

					Float v3x = Float.parseFloat(params[6]);
					Float v3y = Float.parseFloat(params[7]);
					Float v3z = Float.parseFloat(params[8]);
					Point v3 = new Point(v3x, v3y, v3z);

					int matIndex = Integer.parseInt(params[4]);
					Material material = scene.materials.get(matIndex - 1);

					Triangle tri = new Triangle(v1, v2, v3, material);
					scene.surfaces.add(tri);

					System.out.println(String.format("Parsed cylinder (line %d)", lineNum));
				} else if (code.equals("lgt")) {
					Float x = Float.parseFloat(params[0]);
					Float y = Float.parseFloat(params[1]);
					Float z = Float.parseFloat(params[2]);
					Point position = new Point(x, y, z);

					float r1 = Float.parseFloat(params[3]);
					float g1 = Float.parseFloat(params[4]);
					float b1 = Float.parseFloat(params[5]);

					float specularIntensity = Float.parseFloat(params[6]);
					float shadowIntensity = Float.parseFloat(params[7]);
					float lightRadius = Float.parseFloat(params[8]);

					Light light = new Light(position, r1, g1, b1, specularIntensity, shadowIntensity, lightRadius);
					scene.lights.add(light);

					System.out.println(String.format("Parsed light (line %d)", lineNum));
				} else {
					System.out.println(String.format("ERROR: Did not recognize object: %s (line %d)", code, lineNum));
				}
			}
		}

		// It is recommended that you check here that the scene is valid,
		// for example camera settings and all necessary materials were defined.

		System.out.println("Finished parsing scene file " + sceneFileName);
		r.close();

		return scene;
	}

	/**
	 * Renders the loaded scene and saves it to the specified file location.
	 */
	public void renderScene(String outputFileName) {
		long startTime = System.currentTimeMillis();

		// Create a byte array to hold the pixel data:
		byte[] rgbData = new byte[this.imageWidth * this.imageHeight * 3];

		for (int x = 0; x < this.imageWidth; x++) {
			for (int y = 0; y < this.imageHeight; y++) {
				ArrayList<Ray> rays = getRaysByPixel(scene.getCamera().getScreen(), scene.getCamera().getPosition(), x,
						y, scene.getSuperSamplingLevel());
				ArrayList<Color> colors = new ArrayList<>();
				for (Ray ray : rays) {
					Intersection intersection = getIntersectionFromRay(ray);
					if(intersection != null){
						colors.add(intersection.getSurface().getOutputColorInPoint(intersection.getPoint(), scene, ray, this, 0));
					}
				}
				int red = 0;
				int green = 0;
				int blue = 0;
				for (Color color : colors) {
					red += color.getRed();
					green += color.getGreen();
					blue += color.getBlue();
				}
				if (colors.size() > 0) {
					red = red / colors.size();
					green = green / colors.size();
					blue = blue / colors.size();
				}
				rgbData[(y * this.imageWidth + x) * 3] = (byte) red;
				rgbData[(y * this.imageWidth + x) * 3 + 1] = (byte) green;
				rgbData[(y * this.imageWidth + x) * 3 + 2] = (byte) blue;
			}
		}

		// Put your ray tracing code here!
		//
		// Write pixel color values in RGB format to rgbData:
		// Pixel [x, y] red component is in rgbData[(y * this.imageWidth + x) *
		// 3]
		// green component is in rgbData[(y * this.imageWidth + x) * 3 + 1]
		// blue component is in rgbData[(y * this.imageWidth + x) * 3 + 2]
		//
		// Each of the red, green and blue components should be a byte, i.e.
		// 0-255

		long endTime = System.currentTimeMillis();
		Long renderTime = endTime - startTime;

		// The time is measured for your own conveniece, rendering speed will
		// not affect your score
		// unless it is exceptionally slow (more than a couple of minutes)
		System.out.println("Finished rendering scene in " + renderTime.toString() + " milliseconds.");

		// This is already implemented, and should work without adding any code.
		saveImage(this.imageWidth, rgbData, outputFileName);

		System.out.println("Saved file " + outputFileName);

	}

	public Intersection getIntersectionFromRay(Ray ray){
		Surface closestSurface = null;
		Point closestIntersection = null;
		for (Surface surface : scene.getSurfaces()) {
			Point intersection = surface.findClosestIntesectionWithRay(ray);
			if (intersection != null) {
				if (closestIntersection == null) {
					closestIntersection = intersection;
					closestSurface = surface;
				} else if (scene.getCamera().getPosition().FindDistanceFromPoint(intersection) < scene
						.getCamera().getPosition().FindDistanceFromPoint(closestIntersection)) {
					closestIntersection = intersection;
					closestSurface = surface;
				}
			}
		}
		if (closestSurface != null) {
			return new Intersection(closestSurface, closestIntersection);
		}else{
			return null;
		}
	}

	//////////////////////// FUNCTIONS TO SAVE IMAGES IN PNG FORMAT
	//////////////////////// //////////////////////////////////////////

	/*
	 * Saves RGB data as an image in png format to the specified location.
	 */
	public static void saveImage(int width, byte[] rgbData, String fileName) {
		try {

			BufferedImage image = bytes2RGB(width, rgbData);
			ImageIO.write(image, "png", new File(fileName));

		} catch (IOException e) {
			System.out.println("ERROR SAVING FILE: " + e.getMessage());
		}

	}

	/*
	 * Producing a BufferedImage that can be saved as png from a byte array of
	 * RGB values.
	 */
	public static BufferedImage bytes2RGB(int width, byte[] buffer) {
		int height = buffer.length / width / 3;
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
		ColorModel cm = new ComponentColorModel(cs, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		SampleModel sm = cm.createCompatibleSampleModel(width, height);
		DataBufferByte db = new DataBufferByte(buffer, width * height);
		WritableRaster raster = Raster.createWritableRaster(sm, db, null);
		BufferedImage result = new BufferedImage(cm, raster, false, null);

		return result;
	}

	public static class RayTracerException extends Exception {
		public RayTracerException(String msg) {
			super(msg);
		}
	}

	public static Screen calculateScreen(Point camera, Point lookat, float width, float distance, Vector vertical,
			int horizontalPixels, int verticalPixels) {
		Vector toward = new Vector(lookat.getX() - camera.getX(), lookat.getY() - camera.getY(),
				lookat.getZ() - camera.getZ());
		Vector horizontal = toward.crossProduct(vertical);
		Ray in = new Ray(camera, toward);
		Point screenMiddle = in.getPointOnRayByDistance(distance);
		float pixelSize = width / horizontalPixels;
		Ray left = new Ray(screenMiddle, horizontal);
		Point screenMiddleLeft = left.getPointOnRayByDistance((horizontalPixels / 2) * pixelSize);
		Ray up = new Ray(screenMiddleLeft, vertical);
		Point screenUpperLeft = up.getPointOnRayByDistance((verticalPixels / 2) * pixelSize);
		return new Screen(screenUpperLeft, horizontal.multiply(-1), vertical.multiply(-1), pixelSize);
	}

	public static ArrayList<Ray> getRaysByPixel(Screen screen, Point camera, int x, int y, int superSampling) {
		ArrayList<Ray> rays = new ArrayList<Ray>();
		Random random = new Random();
		for (int i = 0; i < superSampling; i++) {
			for (int j = 0; j < superSampling; j++) {
				float xNoise = random.nextFloat() * (screen.getPixelSize() / superSampling);
				float yNoise = random.nextFloat() * (screen.getPixelSize() / superSampling);
				Ray right = new Ray(screen.getUpperLeft(), screen.getHorizontal());
				Point screenRight = right.getPointOnRayByDistance(
						screen.getPixelSize() * x + (screen.getPixelSize() / superSampling) * j + xNoise);
				Ray down = new Ray(screenRight, screen.getVertical());
				Point screenPoint = down.getPointOnRayByDistance(
						screen.getPixelSize() * y + (screen.getPixelSize() / superSampling) * i + yNoise);
				Vector direction = new Vector(screenPoint.getX() - camera.getX(), screenPoint.getY() - camera.getY(),
						screenPoint.getZ() - camera.getZ());
				rays.add(new Ray(camera, direction));
			}
		}
		return rays;
	}

}
