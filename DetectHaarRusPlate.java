package org.palatus;

import org.opencv.core.Core;

public class DetectHaarRusPlate {
	  public static void main(String[] args) {
	    System.out.println("Hello, OpenCV");

	    // Load the native library.
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    new DetectPlate().run();
	  }
}