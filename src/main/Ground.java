package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Ground {
	//ͼƬ
	public BufferedImage image;
	//λ��
	public int x, y;
	public int width, height;
	
	//���캯�� ��ʼ��
	public Ground() throws Exception{
		image = ImageIO.read(new File("D:\\Java\\Eclipse\\items\\Img\\ground.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
		y = 500;
	}
	
	//�����ƶ�һ��
	public void step() {
		 x--;
		if(x==-109) {
			x = 0;
		}
		return;
	}

}






