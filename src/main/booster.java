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
	//λ��
	public int x, y;
	//ͼƬ
	public BufferedImage image;
	//������
	public int height, width;
	//�ƶ��ٶ�
	int speed;
	//buff
	public boolean buff;
	//���������
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
	
	//�����ƶ�һ��
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
		
	
	//"���ٵ���"����λ��,�ӿ����������ƶ��ٶ�
	public void boost() {
		/*
		 * λ��ѡ�񣺲��ܸ�����ײ��һ��
		 * 
		 */
		return;
	}
	//�ж�ȡ��buff
	public boolean delete() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
					
			}
		}, 6000);
		return true;
	}

}
