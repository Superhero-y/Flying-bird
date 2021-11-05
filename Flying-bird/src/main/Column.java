package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Column {
	//图片
	public BufferedImage image;
	//位置
	public int x, y;
	//宽，高
	public int width, height;
	//柱子间的缝隙
	public int gap;
	//柱子间的距离
	public int distance;
	//随机数工具
	Random random = new Random();
	
	//初始化多个柱子
	public Column(int n) throws Exception{
		image = ImageIO.read(new File("D:\\Java\\Eclipse\\items\\Img\\column.png"));
		width = image.getWidth();
		height = image.getHeight();
		gap = 144;
		distance = 245;
		x = 550 + (n-1) *distance;
		y = random.nextInt(218)+132;
	}
	
	//向左移动一步
	public void step(){
		x--;
		if(x==width/2) {
			x = distance * 2 - width / 2;
			y = random.nextInt(218) + 132;
		}
		return;
	}

}



