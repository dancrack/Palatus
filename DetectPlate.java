package org.palatus;

import java.io.File;
import java.net.URL;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

//
// Detects plates in an image, draws boxes around them, and writes the results
// to "plateDetectionRus.jpg".
//
class DetectPlate {
  public void run() {
    System.out.println("\nRunning DetectFaceDemo");

    // Create a plate detector from the cascade file in the resources
    // directory.
    String haarXML = new File("resources/haarcascade_licence_plate_rus_16stages.xml").getPath(); 
    CascadeClassifier plateDetector = new CascadeClassifier(haarXML);
    File img = new File("resources/rus_x022hp78.jpg");
//    File img = new File("resources/IMAG0285.jpg");
    Mat image = Imgcodecs.imread(img.getPath());

    // Detect plates in the image.
    // MatOfRect is a special container class for Rect.
    MatOfRect platesDetections = new MatOfRect();
    plateDetector.detectMultiScale(image, platesDetections);

    System.out.println(String.format("Detected %s plates", platesDetections.toArray().length));

    // Draw a bounding box around each plate.
    for (Rect rect : platesDetections.toArray()) {
        Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
    }

    // Save the visualized detection.
    String filename = "plateDetectionRus.jpg";
    System.out.println(String.format("Writing %s", filename));
    Imgcodecs.imwrite(filename, image);
  }
}