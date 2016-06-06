/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spycam;

/**
 *
 * @author shadman264
 */
import org.opencv.core.*;        
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;        
        
public class Spycam {
    public static void main (String args[]){
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	VideoCapture camera = new VideoCapture(0);
        
    	if(!camera.isOpened()){
    		System.out.println("Error");
    	}
    	else {
    		Mat frame = new Mat();
    	    while(true){
    	    	if (camera.read(frame)){
    	    		System.out.println("Frame Obtained");
    	    		System.out.println("Captured Frame Width " + 
    	    		frame.width() + " Height " + frame.height());
    	    		Imgcodecs.imwrite("camera.jpg", frame);
    	    		System.out.println("OK");
    	    		break;
    	    	}
    	    }	
    	}
    	camera.release();
    }
}   