package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Ground {
	//图片
	public BufferedImage image;
	//位置
	public int x, y;
	public int width, height;
	
	//构造函数 初始化
	public Ground() throws Exception{
		image = ImageIO.read(new File("D:\\Java\\Eclipse\\items\\Img\\ground.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
		y = 500;
	}
	
	//向左移动一步
	public void step() {
		 x--;
		if(x==-109) {
			x = 0;
		}
		return;
	}

}






