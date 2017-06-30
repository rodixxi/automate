package com.harriague.automate.module.android.osnative.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.harriague.automate.core.structures.SwipeDirection;

public class ImagesComparison {

	static {
		// Load OpenCv native library
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

	}

	/**
	 * OpenCv parameters
	 */
	private static final double MAXIMUM_MATCH_SCORE = 0.9911;
	private static final double MINIMUM_MATCH_SCORE = 0.8;
	private static final double BLUR_COEFICIENT = 2.0;
	private static final String PNG = "png";

	/**
	 * Logger object
	 */
	private static Logger log = Logger
			.getLogger(ImagesComparison.class.getName());

	/**
	 * Tries to find a match between the images and returns coordinates of the
	 * mach
	 * 
	 * @param inFile
	 * @param templateFile
	 * @param lowPrecision 
	 * @return
	 */
	public static java.awt.Point matchImages(String inFile,
			String templateFile, boolean lowPrecision) {
		log.info("matching images");
		java.awt.Point coord = new java.awt.Point();

		int matchMethod = Imgproc.TM_CCORR_NORMED;

		Mat screenShot = Imgcodecs.imread(inFile);
		Mat template = Imgcodecs.imread(templateFile);

		// Create the result matrix
		int resultCols = screenShot.cols() - template.cols() + 1;
		int resultRows = screenShot.rows() - template.rows() + 1;
		Mat result = new Mat(resultRows, resultCols, CvType.CV_32FC1);

		// Do the Matching and Normalize
		Imgproc.matchTemplate(screenShot, template, result, matchMethod);

		// Localizing the best match with minMaxLoc
		MinMaxLocResult locatedResults = Core.minMaxLoc(result);

		Point matchLoc;
		matchLoc = locatedResults.maxLoc;
		log.info("Image matched a score of: " + locatedResults.maxVal);

		if (locatedResults.maxVal >= MAXIMUM_MATCH_SCORE  || (lowPrecision && locatedResults.maxVal >= MINIMUM_MATCH_SCORE)) {
			if (matchLoc.x == 0 && matchLoc.y == 0) {
				coord.setLocation(1, 1);
			} else {
				coord.setLocation(matchLoc.x, matchLoc.y);
			}
		} else {
			log.info("no matches");
		}
		return coord;
	}

	/**
	 * Blurs an image
	 * 
	 * @param input
	 *            source image matrix
	 * @param numberOfTimes
	 *            times to apply blur filter
	 * @return
	 */
	public static Mat blur(Mat input, int numberOfTimes) {
		Mat sourceImage = new Mat();
		Mat destinationImage = input.clone();
		log.info("applying blur...");

		for (int i = 0; i < numberOfTimes; i++) {
			sourceImage = destinationImage.clone();
			Imgproc.blur(sourceImage, destinationImage,
					new Size(BLUR_COEFICIENT, BLUR_COEFICIENT));
		}
		return destinationImage;
	}
	
	public static Mat dilate(Mat input){
	    Mat dest = new Mat();
	    Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(10, 10));
	    Imgproc.dilate(input, dest, element1);
	    return dest;
	}

	/**
	 * Converts an image to gray scale
	 * 
	 * @param input
	 * @return
	 */
	public static Mat convertToGray(Mat input) {
		Mat destinationImage = input.clone();
		log.info("converting to gray...");
		Imgproc.cvtColor(destinationImage, destinationImage,
				Imgproc.COLOR_BGR2GRAY);

		return destinationImage;
	}

	
    /**
     * Isolate highlighted route from screenshot
     * 
     * @param screenshot image
     * @return image with car icon only
     */
    public static Mat obtainHighlightedRoute(String screenName){  
        Mat hsv = new Mat();
        
        Mat screenShot = Imgcodecs.imread(screenName);
        Imgproc.cvtColor(screenShot, hsv, Imgproc.COLOR_BGR2HSV);
        Core.inRange(hsv, new Scalar (108,218,93),new Scalar(110,220,95), hsv);
        return dilate(hsv);
    }

	/**
	 * Rotates an image 90 degrees
	 * 
	 * @param image
	 * @param angle
	 * @return
	 */
	public static Mat rotateImage(Mat image, int angle) {
		Mat destinationImage = new Mat();
		destinationImage.create(image.size(), image.type());
		Core.transpose(image, destinationImage);
		Core.flip(destinationImage, destinationImage, 1);

		return destinationImage;
	}

	/**
	 * Reads an image, receives image name as: image_directory + image_name
	 * 
	 * @param imageFile
	 * @return
	 */
	public static Mat readImage(String imageFile) {
		Mat image = Imgcodecs.imread(imageFile);
		return image;
	}

	/**
	 * Writes an image from a buffer
	 * 
	 * @param outputFileName
	 * @param bufferedImage
	 */
	public static void writeBufferedImage(String outputFileName,
			BufferedImage bufferedImage) {
		File outputfile = new File(outputFileName);

		try {
			ImageIO.write(bufferedImage, PNG, outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes an image from a matrix
	 * 
	 * @param image
	 * @param outFileName
	 */
	public static void writeMatImage(Mat image, String outFileName) {
		Imgcodecs.imwrite(outFileName, image);
	}

	/**
	 * Returns a region of interest (ROI) from the image matrix
	 * 
	 * @param image
	 * @param direction
	 * @return
	 */
	public static Mat getPortion(Mat image, SwipeDirection direction) {
		// TODO it is not the optimal, the number were getting manually to
		// identify a specific fragment of the image in the EVM.
		// A better solution is needed for this function.
		// 8 represents amount of sections we divide the screen on
		int xPortion = image.width() / 8;
		int yPortion = image.height() / 8;
		Mat regionOfInterest = null;

		switch (direction) {
		case RIGHT:
			regionOfInterest = image.submat(yPortion, yPortion * 4,
					xPortion * 4, xPortion * 5);
			break;
		case LEFT:
			regionOfInterest = image.submat(yPortion, yPortion * 4, xPortion,
					xPortion + xPortion / 3);
			break;
		case DOWN:
			regionOfInterest = image.submat(yPortion * 4, yPortion * 5, xPortion, xPortion * 4);
			break;
		case UP:
			regionOfInterest = image.submat(0, yPortion, xPortion,
					xPortion * 4);
			break;
		case DIAGONAL_LEFT_TOP:
			regionOfInterest = image.submat(0, yPortion + yPortion / 3, 0,
					xPortion + xPortion / 3);
			break;
		case DIAGONAL_LEFT_BOTTOM:
			regionOfInterest = image.submat(yPortion * 4 + yPortion / 4,
			        yPortion * 5 - yPortion / 3, 0, xPortion + xPortion / 3);
			break;
		case DIAGONAL_RIGHT_TOP:
			regionOfInterest = image.submat(0, yPortion + yPortion / 3,
					xPortion * 4, xPortion * 5 + xPortion / 4);
			break;
		case DIAGONAL_RIGHT_BOTTOM:
			regionOfInterest = image.submat(yPortion * 4 + yPortion / 4,
                    yPortion * 5 - yPortion / 3 , xPortion * 4, xPortion * 5 + xPortion / 4);
			break;
		}
		return regionOfInterest;
	}
}