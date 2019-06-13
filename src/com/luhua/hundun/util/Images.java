package com.luhua.hundun.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Images{
	
	public static BufferedImage getBufferedImage(String path) throws IOException {
		return ImageIO.read(new File(System.getProperty("user.dir")+path));
	}
	
	public static ImageIcon getImageIcon(String path) {
		return new ImageIcon(System.getProperty("user.dir")+path);//’‚ «±≥æ∞Õº∆¨
	}

}