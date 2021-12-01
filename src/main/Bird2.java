package main;
import javax.imageio.ImageIO;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bird2 {
	//ͼƬ
	public BufferedImage image;
	//λ��
	public int x, y;
	//�� ��
	public int width, height;
	//��С
	public int size;
	
	//�������ٶ�
	public double g;
	//λ�Ƶļ��ʱ��
	public double t;
	//��������ٶ�
	public double v0;
	//��ǰ�����ٶ�
	public double speed;
	//����ʱ��t���λ��
	public double s;
	//С������
	public double alpha;
	//�Ƿ�õ�buff
	public boolean buff;
	
	//��һ����Ƭ����¼С��Ķ���֡
	BufferedImage[] images;
	//����֡�����±�
	int index;
	
	//��ʼ��С��
	public Bird2() throws Exception{
		//���캯�� ��ʼ��
		image = ImageIO.read(new File("D:\\Java\\Eclipse\\items\\Img\\a1.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 132;
		y = 280;
		size = 40;
		buff = false;
		
		//��ʼ��λ�Ʋ���
		g = 4;
		v0 = 20;
		t = 0.25;
		speed = v0;
		s = 0;
		alpha = 0;
		
		//��ʼ������֡����
		images = new BufferedImage[8];
		for(int i = 0; i <= 6; i++) {
			File fo = new File("D:\\Java\\Eclipse\\items\\Img\\a" + (i+1) +".png");
			images[i] = ImageIO.read(fo);
		}
		index = 0;
	}
	
	//���ж������仯һ֡��
	public void fly() {
		index++;
		image = images[(index/12%7)];
	}
	
	//�ƶ�һ��
	public void step() {
		double v0 = speed;
		//���������˶�λ��
		s = v0  * t + g * t * t / 2;
		//����С���λ������
		y = y - (int)s;
		//�����´��ƶ��ٶ�
		double v = v0 - g * t;
		speed = v;
		//�������,atan���㷴����ֵ
		alpha = Math.atan(s/7);
	}
	//���Ϸ���
	public void flappy() {
		//�ص��ٶ�
		speed = v0-2;	
	}
	
	//���С���Ƿ���ײ������
	public boolean hit(Ground ground) {
		/*... */
		boolean hit = y+size/2 > ground.y;
		if(hit) {
			y = ground.y-size/2;
			alpha = -3.1415926535 / 2;
		}
		return hit;
	}
	
	//���С���Ƿ�ײ������
	public boolean hit(Column column) {
		//�ȼ���Ƿ������ӷ�Χ��
		if(x>column.x - column.width / 2 -size/2 && x < column.x+column.width/2 + size/2) {
			//����Ƿ������ӵķ�϶��
			if(y>column.y - column.gap/2 + size/2 && y<column.y + column.gap/2 -size/2) {
				return false;	
			}
			return true;
		}
		return false;
	}
	//���С���Ƿ�Ե�BUFF
	public boolean getbuff(booster bos) {
		if((x+20>=bos.x && x-20<=bos.x) &&(y<= bos.y+25 && y>= bos.y-12)) {
			return true;
		}
		return false;
	}
	
	
	
	
}
