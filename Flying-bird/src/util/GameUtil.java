package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;



public class GameUtil {
	//º”‘ÿÕº∆¨
	public static BufferedImage loadBufferedImage(String imgPath) {
		try {
			return ImageIO.read(new FileInputStream(imgPath));
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	

}
