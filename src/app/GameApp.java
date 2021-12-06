package app;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Bird;
import main.Bird2;
import main.Column;
import main.Ground;
import main.booster;


public class GameApp extends JPanel{
	//背景图片
	BufferedImage background;
	//开始图片
	BufferedImage startImage;
	//结束图片
	BufferedImage gameoverImage;
	
	//地面
	Ground ground;
	//柱子
	Column column1, column2;
	//小鸟
	public Bird bird1;
	public Bird2 bird2;
	//加速
	booster bos;
	
	//游戏分数
	int score1;
	int score2;
	//游戏状态
	int state;
	//flag 
	int flag = 1;
	int flag2 = 1;
	
	//char
	public char c1;
	public char c2;
	
	//状态常量
	public static final int START = 0;	//开始
	public static final int RUNNING = 1;	//运行
	public static final int GAME_OVER = 2;	//结束
	
	//初始化游戏
	public GameApp() throws Exception{
		//初始化
		//File fo = new File("./Img/bg.png");//D:\\Java\\Eclipse\\items\\Img\\
		//background = ImageIO.read(fo);
		background = ImageIO.read(new File("../Img/bg.png"));
		//File fk = new File("D:\\Java\\Eclipse\\items\\Img\\start.png");
		startImage = ImageIO.read(new File("../Img/start.png"));
		gameoverImage = ImageIO.read(new File("../Img/gameover.png"));
		
		//初始化地面、柱子、小鸟
		ground = new Ground();
		column1 = new Column(1);
		column2 = new Column(2);
		bos = new booster();
		bird1 = new Bird();
		bird2 = new Bird2();		
		//初始化分数
		score1 = 0;
		score2 = 0;
		//初始化状态
		state = START;
		
	}
	
	//绘制界面
	public void paint(Graphics g) {
		//绘制背景
		g.drawImage(background, 0, 0, null);
		//绘制地面
		g.drawImage(ground.image, ground.x, ground.y, null);
		//绘制柱子
		g.drawImage(column1.image, column1.x-column1.width/2, column1.y-column1.height/2, null);
		g.drawImage(column2.image, column2.x-column2.width/2, column2.y-column2.height/2, null);
		//绘制火箭
		g.drawImage(bos.image, bos.x, bos.y,null);
		
		//绘制小鸟
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird1.alpha, bird1.x, bird1.y);
		g2.drawImage(bird1.image, bird1.x-bird1.width/2, bird1.y-bird1.height/2, null);
		g2.rotate(bird1.alpha, bird1.x, bird1.y);
		
		Graphics2D g3 = (Graphics2D) g;
		g3.rotate(-bird2.alpha, bird2.x, bird2.y);
		g3.drawImage(bird2.image, bird2.x-bird2.width/2, bird2.y-bird2.height/2, null);
		g3.rotate(bird2.alpha, bird2.x, bird2.y);
		
		//绘制分数
		Font f1 = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g.setFont(f1);
		g.setColor(Color.blue);
		g.drawString(""+score1, 40,50);
		
		//g.drawString(""+score,40-3,60-3);
		
		Font f2 = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g.setFont(f2);
		g.setColor(Color.red);
		g.drawString(""+score2, 360,50);
		
		//g.drawString(""+score,380+3,60+3);
		
		
		//绘制开始和结束界面
		switch(state) {
			case START:
				g.drawImage(startImage,0,0,null);
				break;
			case GAME_OVER:
				g.drawImage(gameoverImage,0,0,null);
				break;
		}
	}
	//监听键盘类
	public class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			char ch = e.getKeyChar();
			System.out.println("你按了"+ch);
			c1 = ch;
		}
	}
		
	
	//开始游戏
	public void action()throws Exception{
		//鼠标监听器
		MouseListener l = new MouseAdapter() {
			//鼠标按下事件
			public void mousePressed(MouseEvent e) {
				try {
					switch(state) {
					case START:
					//在开始状态，按下鼠标则转为运行状态
						state = RUNNING;
						break;
					case RUNNING:
					//在运行状态。按下鼠标向上飞行
						 
						//bird1.flappy();
						//bird2.flappy();
						break;
					case GAME_OVER:
					//在结束状态，按下鼠标重置数据，再次转为开始状态
						column1= new Column(1);
						column2 = new Column(2);
						bos = new booster();
						bird1 = new Bird();
						bird2 = new Bird2();
						score1 = 0;
						score2 = 0;
						state = START;
						break;
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		//取消buff
		
		
		//将监听器添加到当前的面板上
		addMouseListener(l);
		//不断的移动与重绘
		while(true) {
			switch(state) {
			case START:
				//小鸟做出飞行动作
				bird1.fly();
				bird2.fly();
				//地面向左移动
				ground.step();
				break;
			case RUNNING:
				//地面向左移动一步
				ground.step();
				//柱子向左移动
				column1.step();
				column2.step();
				//rocket remove
				if(bos.buff) {
				}else {
					bos.step();
				}
				//小鸟飞行动作
				bird1.fly();
				bird2.fly();
				//小鸟上下移动一步
				bird1.step();
				bird2.step();
				//计算分数
				if(bird1.x == column1.x || bird1.x == column2.x ) {
					score1++;
				}
				if(bird2.x == column1.x || bird2.x == column2.x ) {
					score2++;
				}
				//检测是否发生碰撞
				if(bird1.hit(ground) || bird1.hit(column1)|| bird1.hit(column2)) {
					if(bird1.buff) {
						System.out.println("yes!");
					}else {
						state = GAME_OVER;
					}
				}
				if(bird2.hit(ground) || bird2.hit(column1)|| bird2.hit(column2)) {
					if(bird2.buff) {
						System.out.println("yes!");
					}else {
						state = GAME_OVER;
					}
				}
				//检查是否吃到BUFF
				if(bird1.getbuff(bos)) {
					bos.x = -56;
					bird1.buff = true;
					bos.buff = true;
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
								bos.buff = false;
								bird1.buff = false;
						}
					}, 10000);
				}
				if(bird2.getbuff(bos)) {
					bos.x = -56;
					bird2.buff = true;
					bos.buff = true;
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
								bos.buff = false;
								bird2.buff = false;
						}
					}, 10000);
				}
				//分数达到XX，加速
				/*if(score>=2) {
					column1.quick();
					column2.quick();
				}*/
				
				break;
			}
			repaint();
			Thread.sleep(1000/60);
		}
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		
	}
}
