package RayTracing;

import java.awt.*;
import java.awt.Transparency;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import Objects.*;
import Objects.Point;
import java.util.*;

/**
 *  Main class for ray tracing exercise.
 */
public class RayTracer {

	public int imageWidth;
	public int imageHeight;

	/**
	 * Runs the ray tracer. Takes scene file, output image file and image size as input.
	 */
	public static void main(String[] args) {

		try {

			RayTracer tracer = new RayTracer();

                        // Default values:
			tracer.imageWidth = 500;
			tracer.imageHeight = 500;

			if (args.length < 2)
				throw new RayTracerException("Not enough arguments provided. Please specify an input scene file and an output image file for rendering.");

			String sceneFileName = args[0];
			String outputFileName = args[1];

			if (args.length > 3)
			{
				tracer.imageWidth = Integer.parseInt(args[2]);
				tracer.imageHeight = Integer.parseInt(args[3]);
			}


			// Parse scene file:
			tracer.parseScene(sceneFileName);

			// Render scene:
			tracer.renderScene(outputFileName);

//		} catch (IOException e) {
//			System.out.println(e.getMessage());
		} catch (RayTracerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}

	/**
	 * Parses the scene file and creates the scene. Change this function so it generates the required objects.
	 */
	public void parseScene(String sceneFileName) throws IOException, RayTracerException
	{
		FileReader fr = new FileReader(sceneFileName);

		BufferedReader r = new BufferedReader(fr);
		String line = null;
		int lineNum = 0;
		System.out.println("Started parsing scene file " + sceneFileName);
		
		Scene scene = new Scene();
		scene.setMaterials(new ArrayList<Material>());
		
		while ((line = r.readLine()) != null)
		{
			line = line.trim();
			++lineNum;

			if (line.isEmpty() || (line.charAt(0) == '#'))
			{  // This line in the scene file is a comment
				continue;
			}
			else
			{
				String code = line.substring(0, 3).toLowerCase();
				// Split according to white space characters:
				String[] params = line.substring(3).trim().toLowerCase().split("\\s+");

				if (code.equals("cam"))
				{
					Camera camera = new Camera();
					
					Point position = new Point();
					position.setX(Double.parseDouble(params[0]));
					position.setY(Double.parseDouble(params[1]));
					position.setZ(Double.parseDouble(params[2]));
					camera.setPosition(position);
					
					Point lookAt = new Point();
					lookAt.setX(Double.parseDouble(params[3]));
					lookAt.setY(Double.parseDouble(params[4]));
					lookAt.setZ(Double.parseDouble(params[5]));
					camera.setLookAt(lookAt);
					
<<<<<<< HEAD
					Vector up = new Vector();
					up.setX(Double.parseDouble(params[6]));
					up.setY(Double.parseDouble(params[7]));
					up.setZ(Double.parseDouble(params[8]));
					camera.setUp(up);
					
					camera.setScreenDistance(Double.parseDouble(params[9]));
					camera.setScreenWidth(Double.parseDouble(params[10]));
=======
					Vector up = new Vector(Double.parseDouble(params[6]), Double.parseDouble(params[7]), Double.parseDouble(params[8]));
					camera.setUp(up);
					
					// TODO Tal: change
					Screen screen = new Screen();
					screen.setScreenDistance(Double.parseDouble(params[9]));
					screen.setScreenWidth(Double.parseDouble(params[10]));
					camera.setScreen(screen);
>>>>>>> parent of 6732a86... fix colisions
					
					scene.setCamera(camera);
					
					System.out.println(String.format("Parsed camera parameters (line %d)", lineNum));
				}
				else if (code.equals("set"))
				{
					int bgr = Integer.parseInt(params[0]);
					int bgg = Integer.parseInt(params[1]);
					int bgb = Integer.parseInt(params[2]);
					scene.setBackgroundColor(new Color(bgr, bgg, bgb));
					
					scene.setShadowRaysNumber(Integer.parseInt(params[3]));
					scene.setMaxRecursionLevel(Integer.parseInt(params[4]));
					scene.setSuperSamplingLevel(Integer.parseInt(params[5]));
					
					System.out.println(String.format("Parsed general settings (line %d)", lineNum));
				}
				else if (code.equals("mtl"))
				{

					int dr = Integer.parseInt(params[0]);
					int dg = Integer.parseInt(params[1]);
					int db = Integer.parseInt(params[2]);
					Color diffuseColor = new Color(dr, dg, db);
					      
					
					int sr = Integer.parseInt(params[3]);
					int sg = Integer.parseInt(params[4]);
					int sb = Integer.parseInt(params[5]);
					Color specularColor = new Color(sr, sg, sb);

					int rr = Integer.parseInt(params[6]);
					int rg = Integer.parseInt(params[7]);
					int rb = Integer.parseInt(params[8]);
					Color reflectionColor = new Color(rr, rg, rb);
					
					double phong = Double.parseDouble(params[9]);
					double transparency = Double.parseDouble(params[10]);
					
					scene.getMaterials().add(new Material(diffuseColor, specularColor, reflectionColor, phong, transparency));

					System.out.println(String.format("Parsed material (line %d)", lineNum));
				}
				else if (code.equals("sph"))
				{
                                        // Add code here to parse sphere parameters

                                        // Example (you can implement this in many different ways!):
					                    // Sphere sphere = new Sphere();
                                        // sphere.setCenter(params[0], params[1], params[2]);
                                        // sphere.setRadius(params[3]);
                                        // sphere.setMaterial(params[4]);

					System.out.println(String.format("Parsed sphere (line %d)", lineNum));
				}
				else if (code.equals("pln"))
				{
                                        // Add code here to parse plane parameters

					System.out.println(String.format("Parsed plane (line %d)", lineNum));
				}
				else if (code.equals("trg"))
				{
                                        // Add code here to parse cylinder parameters

					System.out.println(String.format("Parsed cylinder (line %d)", lineNum));
				}
				else if (code.equals("lgt"))
				{
                                        // Add code here to parse light parameters

					System.out.println(String.format("Parsed light (line %d)", lineNum));
				}
				else
				{
					System.out.println(String.format("ERROR: Did not recognize object: %s (line %d)", code, lineNum));
				}
			}
		}

                // It is recommended that you check here that the scene is valid,
                // for example camera settings and all necessary materials were defined.

		System.out.println("Finished parsing scene file " + sceneFileName);
		r.close();
	}

	/**
	 * Renders the loaded scene and saves it to the specified file location.
	 */
	public void renderScene(String outputFileName)
	{
		long startTime = System.currentTimeMillis();

		// Create a byte array to hold the pixel data:
		byte[] rgbData = new byte[this.imageWidth * this.imageHeight * 3];


                // Put your ray tracing code here!
                //
                // Write pixel color values in RGB format to rgbData:
                // Pixel [x, y] red component is in rgbData[(y * this.imageWidth + x) * 3]
                //            green component is in rgbData[(y * this.imageWidth + x) * 3 + 1]
                //             blue component is in rgbData[(y * this.imageWidth + x) * 3 + 2]
                //
                // Each of the red, green and blue components should be a byte, i.e. 0-255


		long endTime = System.currentTimeMillis();
		Long renderTime = endTime - startTime;

                // The time is measured for your own conveniece, rendering speed will not affect your score
                // unless it is exceptionally slow (more than a couple of minutes)
		System.out.println("Finished rendering scene in " + renderTime.toString() + " milliseconds.");

                // This is already implemented, and should work without adding any code.
		saveImage(this.imageWidth, rgbData, outputFileName);

		System.out.println("Saved file " + outputFileName);

	}




	//////////////////////// FUNCTIONS TO SAVE IMAGES IN PNG FORMAT //////////////////////////////////////////

	/*
	 * Saves RGB data as an image in png format to the specified location.
	 */
	public static void saveImage(int width, byte[] rgbData, String fileName)
	{
		try {

			BufferedImage image = bytes2RGB(width, rgbData);
			ImageIO.write(image, "png", new File(fileName));

		} catch (IOException e) {
			System.out.println("ERROR SAVING FILE: " + e.getMessage());
		}

	}

	/*
	 * Producing a BufferedImage that can be saved as png from a byte array of RGB values.
	 */
	public static BufferedImage bytes2RGB(int width, byte[] buffer) {
	    int height = buffer.length / width / 3;
	    ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
	    ColorModel cm = new ComponentColorModel(cs, false, false,
	            Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
	    SampleModel sm = cm.createCompatibleSampleModel(width, height);
	    DataBufferByte db = new DataBufferByte(buffer, width * height);
	    WritableRaster raster = Raster.createWritableRaster(sm, db, null);
	    BufferedImage result = new BufferedImage(cm, raster, false, null);

	    return result;
	}

	public static class RayTracerException extends Exception {
		public RayTracerException(String msg) {  super(msg); }
	}


}
