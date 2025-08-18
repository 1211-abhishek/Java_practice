package com.example.tesseract.preprocess;

import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OcrPreprocessor {

    static {
        OpenCV.loadLocally();
    }

    public static String preprocess(String inputImagePath, String outputImagePath) {
        Mat src = Imgcodecs.imread(inputImagePath);
        if (src.empty()) {
            throw new IllegalArgumentException("Cannot read image: " + inputImagePath);
        }

        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

        Imgproc.GaussianBlur(gray, gray, new Size(1, 1), 0.5);

        Mat binary = new Mat();
        Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);

        Imgcodecs.imwrite(outputImagePath, binary);

        return outputImagePath;
    }
}
