import java.util.ArrayList;

import java.io.IOException;
import edu.wpi.first.wpilibj.networktables.*;
import edu.wpi.first.wpilibj.tables.*;
import edu.wpi.cscore.*;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

public class RosieVision implements ITableListener{
	NetworkTable table = null;
	CvSink imageSink = null;
	double focalLength = 0;//TODO set actual value
	double targetInitialWidth = 0;//TODO set actual value
	public static void main(String[] args) {
 
		RosieVision vision = new RosieVision();
		while(true);
	}
	
	public RosieVision(){
		// Loads our OpenCV library. This MUST be included
		System.loadLibrary("opencv_java310");
		// Connect NetworkTables, and get access to the publishing table
		NetworkTable.setClientMode();
		NetworkTable.setTeam(839);
		NetworkTable.initialize();
		table = NetworkTable.getTable("visionTable");
		table.addTableListener("command", this, true);
		// This is the network port you want to stream the raw received image to
		// By rules, this has to be between 1180 and 1190, so 1185 is a good choice
		int streamPort = 1185;

		// This stores our reference to our mjpeg server for streaming the input image
		MjpegServer inputStream = new MjpegServer("MJPEG Server", streamPort);

		/***********************************************/

		// USB Camera
		// This gets the image from a USB camera 
		// Usually this will be on device 0, but there are other overloads
		// that can be used
		UsbCamera camera = setUsbCamera(0, inputStream);
		// Set the resolution for our camera, since this is over USB
		camera.setResolution(800,600);//640,480);

		// This creates a CvSink for us to use. This grabs images from our selected camera, 
		// and will allow us to use those images in opencv
		imageSink = new CvSink("CV Image Grabber");
		imageSink.setSource(camera);

		// This creates a CvSource to use. This will take in a Mat image that has had OpenCV operations
		// operations 
		CvSource imageSource = new CvSource("CV Image Source", VideoMode.PixelFormat.kMJPEG, 800, 600, 30);
		MjpegServer cvStream = new MjpegServer("CV Image Stream", 1186);
		cvStream.setSource(imageSource);

	}
	
	public void getPosition(){
		// All Mats and Lists should be stored outside the loop to avoid allocations
		// as they are expensive to create
		Mat inputImage = new Mat();

		GripPipeline pipeline = new GripPipeline();
		ArrayList<MatOfPoint> contourPoints = null;
		// Infinitely process image
		boolean processFrames = true;
		double distance = 0;
		double turnAngle = 0;
		Point center = null;
		while (processFrames) {

		  // Grab a frame. If it has a frame time of 0, there was an error.
		  // Just skip and continue
		  long frameTime = imageSink.grabFrame(inputImage);
		  if (frameTime == 0) continue;

		  // Below is where you would do your OpenCV operations on the provided image
		  // The sample below just changes color source to HSV

		  pipeline.process(inputImage);
		  contourPoints =  pipeline.filterContoursOutput();
		   
		  if(contourPoints.size() > 0)
			System.out.println("Detected Points===========================");
		  
		  for(MatOfPoint point : contourPoints)
			{
				final Rect bb = Imgproc.boundingRect(point);
				center = calculateMiddle(bb);
			  	distance = calculateDistance(bb);
			  	turnAngle = calculateTurnAngle(bb,center,distance)
				System.out.println(point.toString());
				System.out.println(bb.toString());
				System.out.println(bb.tl().toString()+", "+ bb.br().toString());
				System.out.println("Center===========> "+ center.toString());
				System.out.println("Distance=========> "+ distance);
				System.out.println("Turn Angle=========> "+ turnAngle);
			}
//		  new LoadImage(inputImage);
//		  new LoadImage(pipeline.hslThresholdOutput());
//		  table.putNumber("x",center.x);
//		  table.putNumber("y",center.y);
		  table.putNumber("distance",distance);
		  table.putNumber("turnAngle",turnAngle);
		  
		  processFrames = false;
		}

	}
  
  private static UsbCamera setUsbCamera(int cameraId, MjpegServer server) {
    // This gets the image from a USB camera 
    // Usually this will be on device 0, but there are other overloads
    // that can be used
    UsbCamera camera = new UsbCamera("CoprocessorCamera", cameraId);
    server.setSource(camera);
    return camera;
  }
  
  private static Point calculateMiddle(Rect bb){
	  Point br = bb.br();
	  Point tl = bb.tl();
	  
	  Point center = new Point(tl.x + .5*(br.x-tl.x),br.y + .5*(tl.y - br.y));
	  return center;
  }
  private static int calculateDistance(Rect bb){
  	//W = actual width
	//D = distance
	//P = # of pixels wide
	//F = focal length
	//F = (P x  D) / W
	//D’ = (W x F) / P	  
	  return (this.targetInitialWidth * this.focalLength)/bb.width;
  }

  private static int calculateTurnAngle(Rect bb, Point center, double distance){
	  double targetWidth = (bb.width*distance)/this.focalLength;
	  double pixelsFromCenter = (center.x-(this.width/2));
	  double pixleRatio = targetWidth/bb.width;
	  double distanceFromCenter = pixelsFromCenter*pixelRatio;
	  double turnAngle = Math.arcTan(distanceFromCenter/distance);
	  
	  return turnAngle;
  }

  public void valueChanged(ITable itable, String key, Object value, boolean b){
	if(((String)value).equals("getDistance"))
		getPosition();
	else if(((String)value).equals("shutdown"))
		shutdown();
  }
  
  private static void shutdown(){
	  	try {
		Process p = Runtime.getRuntime().exec("sudo shutdown -h now");
        p.waitFor();
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
     }  
  }
}
