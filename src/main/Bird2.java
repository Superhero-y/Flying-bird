package main;
import javax.imageio.ImageIO;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bird2 {
	//图片
	public BufferedImage image;
	//位置
	public int x, y;
	//宽 高
	public int width, height;
	//大小
	public int size;
	
	//重力加速度
	public double g;
	//位移的间隔时间
	public double t;
	//最初上抛速度
	public double v0;
	//当前上抛速度
	public double speed;
	//经过时间t后的位移
	public double s;
	//小鸟的倾角
	public double alpha;
	//是否得到buff
	public boolean buff;
	
	//用一组照片来记录小鸟的动画帧
	BufferedImage[] images;
	//动画帧数组下标
	int index;
	
	//初始化小鸟
	public Bird2() throws Exception{
		//构造函数 初始化
		image = ImageIO.read(new File("D:\\Java\\Eclipse\\items\\Img\\a1.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 132;
		y = 280;
		size = 40;
		buff = false;
		
		//初始化位移参数
		g = 4;
		v0 = 20;
		t = 0.25;
		speed = v0;
		s = 0;
		alpha = 0;
		
		//初始化动画帧参数
		images = new BufferedImage[8];
		for(int i = 0; i <= 6; i++) {
			File fo = new File("D:\\Java\\Eclipse\\items\\Img\\a" + (i+1) +".png");
			images[i] = ImageIO.read(fo);
		}
		index = 0;
	}
	
	//飞行动作（变化一帧）
	public void fly() {
		index++;
		image = images[(index/12%7)];
	}
	
	//移动一步
	public void step() {
		double v0 = speed;
		//计算上抛运动位移
		s = v0  * t + g * t * t / 2;
		//计算小鸟的位置坐标
		y = y - (int)s;
		//计算下次移动速度
		double v = v0 - g * t;
		speed = v;
		//计算倾角,atan计算反正切值
		alpha = Math.atan(s/7);
	}
	//向上飞翔
	public void flappy() {
		//重叠速度
		speed = v0-2;	
	}
	
	//检查小鸟是否碰撞到地面
	public boolean hit(Ground ground) {
		/*... */
		boolean hit = y+size/2 > ground.y;
		if(hit) {
			y = ground.y-size/2;
			alpha = -3.1415926535 / 2;
		}
		return hit;
	}
	
	//检查小鸟是否撞到柱子
	public boolean hit(Column column) {
		//先检测是否在柱子范围内
		if(x>column.x - column.width / 2 -size/2 && x < column.x+column.width/2 + size/2) {
			//检查是否在柱子的缝隙中
			if(y>column.y - column.gap/2 + size/2 && y<column.y + column.gap/2 -size/2) {
				return false;	
			}
			return true;
		}
		return false;
	}
	//检查小鸟是否吃到BUFF
	public boolean getbuff(booster bos) {
		if((x+20>=bos.x && x-20<=bos.x) &&(y<= bos.y+25 && y>= bos.y-12)) {
			return true;
		}
		return false;
	}
	
	
	
	
}
