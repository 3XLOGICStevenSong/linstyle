package com.djb.highway.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CompressImage {
	private File file = null;
	private String inputDir;
	private String outputDir;
	private int outputWidth = 100;
	private int outputHeight = 100;
	private boolean proportion = true;

	public CompressImage() {
		inputDir = "";
		outputDir = "";
		outputWidth = 100;
		outputHeight = 100;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public void setOutputWidth(int outputWidth) {
		this.outputWidth = outputWidth;
	}

	public void setOutputHeight(int outputHeight) {
		this.outputHeight = outputHeight;
	}

	public void setWidthAndHeight(int Width, int Height) {
		this.outputWidth = Width;
		this.outputHeight = Height;
	}

	public long getPicSize(String path) {
		file = new File(path);
		return file.length();
	}

	public String compressPic() {
		try {
			File file = new File(inputDir);
			if (!file.exists()) {
				return "";
			}
			File[] srcfile = file.listFiles();

			if (srcfile != null) {
				for (int i = 0; i < srcfile.length; i++) {

					if (srcfile[i].isFile()
							&& (srcfile[i].getName().endsWith(".jpg")
									|| srcfile[i].getName().endsWith(".JPG")
									|| srcfile[i].getName().endsWith(".png") || srcfile[i]
									.getName().endsWith(".PNG"))) {
						Image img = ImageIO.read(srcfile[i]);

						if (img.getWidth(null) == -1) {
							// System.out.println("can't read,retry" + "<BR>");
							return "no";
						} else {
							if (img.getWidth(null) > 1000) {
								int Width = (int) (img.getWidth(null) * 30 / 100.0);
								int Height = (int) (img.getHeight(null) * 30 / 100.0);

								BufferedImage tag = new BufferedImage(Width,
										Height, BufferedImage.TYPE_INT_RGB);

								tag.getGraphics()
										.drawImage(
												img.getScaledInstance(Width,
														Height,
														Image.SCALE_SMOOTH), 0,
												0, null);
								FileOutputStream out = new FileOutputStream(
										outputDir + srcfile[i].getName());
								JPEGImageEncoder encoder = JPEGCodec
										.createJPEGEncoder(out);
								// JPEG编码
								encoder.encode(tag);
								out.close();
							} else {
								int newWidth;
								int newHeight;

								if (this.proportion == true) {

									double rate1 = ((double) img.getWidth(null))
											/ (double) outputWidth + 0.1;
									double rate2 = ((double) img
											.getHeight(null))
											/ (double) outputHeight + 0.1;

									double rate = rate1 > rate2 ? rate1 : rate2;
									newWidth = (int) (((double) img
											.getWidth(null)) / rate);
									newHeight = (int) (((double) img
											.getHeight(null)) / rate);
								} else {
									newWidth = img.getWidth(null);
									newHeight = img.getHeight(null);
								}
								BufferedImage tag = new BufferedImage(
										(int) newWidth, (int) newHeight,
										BufferedImage.TYPE_INT_RGB);

								tag.getGraphics().drawImage(
										img.getScaledInstance(newWidth,
												newHeight, Image.SCALE_SMOOTH),
										0, 0, null);
								FileOutputStream out = new FileOutputStream(
										outputDir + srcfile[i].getName());
								JPEGImageEncoder encoder = JPEGCodec
										.createJPEGEncoder(out);
								// JPEG编码
								encoder.encode(tag);
								out.close();
							}
						}

					}

				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "ok";
	}

	public String compressPic(String inputDir, String outputDir) {
		this.inputDir = inputDir;
		this.outputDir = outputDir;
		return compressPic();
	}

	public String compressPic(String inputDir, String outputDir, int width,
			int height, boolean gp) {

		this.inputDir = inputDir;
		this.outputDir = outputDir;
		setWidthAndHeight(width, height);
		this.proportion = gp;
		return compressPic();

	}

	public static void main(String[] arg) {
		CompressImage mypic = new CompressImage();
		mypic.compressPic("d:\\55\\", "d:\\test\\", 120, 120, false);// 55是源文件，test是压缩后的文件，
		// 只需要填入文件地址即可
	}

}
