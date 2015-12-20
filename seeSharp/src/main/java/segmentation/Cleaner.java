package segmentation;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Cleaner {
	private Mat original;
	private Mat binary, mask, cleaned;
	private double minScale, maxScale, sigma;
	private int binaryThreshold, maxWhite;
	private int h, w, d;
	
	public Cleaner(Mat original, double minScale, double maxScale, double sigma,
			int binaryThreshold, int maxWhite) {
		super();
		this.original = original;
		this.minScale = minScale;
		this.maxScale = maxScale;
		this.sigma = sigma;
		this.binaryThreshold = binaryThreshold;
		this.maxWhite = maxWhite;
		this.h = original.rows();
		this.w = original.cols();
		this.d = original.channels();
	}

	public void clean() {
		
		Mat gaussianFiltered = new Mat(h, w, d);
		Imgproc.GaussianBlur(original, gaussianFiltered, new Size(3, 3), sigma);
		
		Mat gaussianBinary = new Mat(h, w, d);
		Imgproc.threshold(gaussianBinary, gaussianBinary, binaryThreshold, maxWhite, Imgproc.THRESH_BINARY_INV);
		
		Imgproc.threshold(original, binary, binaryThreshold, maxWhite, Imgproc.THRESH_BINARY_INV);
		
	}
}