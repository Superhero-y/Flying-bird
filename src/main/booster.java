package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class booster {
	//位置
	public int x, y;
	//图片
	public BufferedImage image;
	//长、宽
	public int height, width;
	//移动速度
	int speed;
	//buff
	public boolean buff;
	//随机数工具
	Random random = new Random();
	Random random_time = new Random();
	
	public booster() throws IOException
	{
		image = ImageIO.read(new File("D:\\Java\\Eclipse\\items\\Img\\booster.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
		y = random.nextInt(200)+50;
		buff = false;
	}
	
	//向左移动一步
		public void step(){
			x--;
			int t = 0;
			System.out.println(x);
			if(x==-200)
			{
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
							int reset_x = random.nextInt(800);
							System.out.println("reset_x : " + reset_x);
							x = reset_x;
					}
				}, 100);
			}//random.nextInt(20000)<10000?+5000:+0
			return;
		}
		
	
	//"加速道具"出现位置,加快柱子向左移动速度
	public void boost() {
		/*
		 * 位置选择：不能跟柱子撞在一起
		 * 
		 */
		return;
	}
	//判断取消buff
	public boolean delete() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
					
			}
		}, 6000);
		return true;
	}

}
