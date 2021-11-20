package app;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import main.Column;
import main.Ground;
import main.booster;


public class GameApp extends JPanel{
	//����ͼƬ
	BufferedImage background;
	//��ʼͼƬ
	BufferedImage startImage;
	//����ͼƬ
	BufferedImage gameoverImage;
	
	//����
	Ground ground;
	//����
	Column column1, column2;
	//С��
	Bird bird;
	//����
	booster bos;
	
	//��Ϸ����
	int score;
	//��Ϸ״̬
	int state;
	//flag 
	int flag = 1;
	
	//״̬����
	public static final int START = 0;	//��ʼ
	public static final int RUNNING = 1;	//����
	public static final int GAME_OVER = 2;	//����
	
	//��ʼ����Ϸ
	public GameApp() throws Exception{
		//��ʼ��
		File fo = new File("D:\\Java\\Eclipse\\items\\Img\\bg.png");
		background = ImageIO.read(fo);
		File fk = new File("D:\\Java\\Eclipse\\items\\Img\\start.png");
		startImage = ImageIO.read(fk);
		gameoverImage = ImageIO.read(new File("D:\\Java\\Eclipse\\items\\Img\\gameover.png"));
		
		//��ʼ�����桢���ӡ�С��
		ground = new Ground();
		column1 = new Column(1);
		column2 = new Column(2);
		bos = new booster();
		bird = new Bird();
		
		//��ʼ������
		score = 0;
		//��ʼ��״̬
		state = START;
		
	}
	
	//���ƽ���
	public void paint(Graphics g) {
		//���Ʊ���
		g.drawImage(background, 0, 0, null);
		//���Ƶ���
		g.drawImage(ground.image, ground.x, ground.y, null);
		//��������
		g.drawImage(column1.image, column1.x-column1.width/2, column1.y-column1.height/2, null);
		g.drawImage(column2.image, column2.x-column2.width/2, column2.y-column2.height/2, null);
		//���ƻ��
		g.drawImage(bos.image, bos.x, bos.y,null);
		
		//����С��
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird.alpha, bird.x, bird.y);
		g2.drawImage(bird.image, bird.x-bird.width/2, bird.y-bird.height/2, null);
		g2.rotate(bird.alpha, bird.x, bird.y);
		
		//���Ʒ���
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g.setFont(f);
		g.drawString(""+score, 40,50);
		g.setColor(Color.white);
		g.drawString(""+score,40-3,60-3);
		
		
		//���ƿ�ʼ�ͽ�������
		switch(state) {
			case START:
				g.drawImage(startImage,0,0,null);
				break;
			case GAME_OVER:
				g.drawImage(gameoverImage,0,0,null);
				break;
		}
	}
	
	//��ʼ��Ϸ
	public void action()throws Exception{
		//��������
		MouseListener l = new MouseAdapter() {
			//��갴���¼�
			public void mousePressed(MouseEvent e) {
				try {
					switch(state) {
					case START:
					//�ڿ�ʼ״̬�����������תΪ����״̬
						state = RUNNING;
						break;
					case RUNNING:
					//������״̬������������Ϸ���
						bird.flappy();
						break;
					case GAME_OVER:
					//�ڽ���״̬����������������ݣ��ٴ�תΪ��ʼ״̬
						column1= new Column(1);
						column2 = new Column(2);
						bos = new booster();
						bird = new Bird();
						score = 0;
						state = START;
						break;
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		//ȡ��buff
		
		
		//����������ӵ���ǰ�������
		addMouseListener(l);
		//���ϵ��ƶ����ػ�
		while(true) {
			switch(state) {
			case START:
				//С���������ж���
				bird.fly();
				//���������ƶ�
				ground.step();
				break;
			case RUNNING:
				//���������ƶ�һ��
				ground.step();
				//���������ƶ�
				column1.step();
				column2.step();
				//rocket remove
				if(bos.buff) {
				}else {
					bos.step();
				}
				//С����ж���
				bird.fly();
				//С�������ƶ�һ��
				bird.step();
				//�������
				if(bird.x == column1.x || bird.x == column2.x ) {
					score++;
				}
				//����Ƿ�����ײ
				if(bird.hit(ground) || bird.hit(column1)|| bird.hit(column2)) {
					if(bos.buff) {
						System.out.println("yes!");
					}else {
						state = GAME_OVER;
					}
				}
				//����Ƿ�Ե�BUFF
				if(bird.getbuff(bos)) {
					bos.x = -56;
					bos.buff = true;
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
								bos.buff = false;
						}
					}, 10000);
				}
				//�����ﵽXX������
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
		try {
			ServerSocket server = null;
			try {
				server = new ServerSocket(4800);
			}catch(Exception e) {
				System.out.println("can not listen to:" +e);
			}
			Socket socket = null;
			try {
				socket = server.accept();
			}catch(Exception e) {
				System.out.println("Error."+e);
			}
			String line;
			BufferedReader is = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Client:"+is.readLine());
			line = sin.readLine();
			while(!line.equals("bye")) {
				os.println(line);
				os.flush();
				System.out.println("Server:"+line);
				System.out.println("Client:"+is.readLine());
				line = sin.readLine();
			}
			os.close();
			is.close();
			socket.close();
			server.close();
		}catch(Exception e) {
			System.out.println("Error:"+e);
		}
		
		JFrame f = new JFrame();
		GameApp game = new GameApp();
		f.add(game);
		f.setSize(440,600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		game.action();
	}

}
