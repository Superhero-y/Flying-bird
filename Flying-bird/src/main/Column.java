package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Column {
	//ͼƬ
	public BufferedImage image;
	//λ��
	public int x, y;
	//����
	public int width, height;
	//���Ӽ�ķ�϶
	public int gap;
	//���Ӽ�ľ���
	public int distance;
	//���������
	Random random = new Random();
	
	//��ʼ���������
	public Column(int n) throws Exception{
		image = ImageIO.read(new File("D:\\Java\\Eclipse\\items\\Img\\column.png"));
		width = image.getWidth();
		height = image.getHeight();
		gap = 144;
		distance = 245;
		x = 550 + (n-1) *distance;
		y = random.nextInt(218)+132;
	}
	
	//�����ƶ�һ��
	public void step(){
		x--;
		if(x==width/2) {
			x = distance * 2 - width / 2;
			y = random.nextInt(218) + 132;
		}
		return;
	}

}



