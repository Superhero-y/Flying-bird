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
	Bird bird1;
	Bird2 bird2;
	//����
	booster bos;
	
	//��Ϸ����
	int score1;
	int score2;
	//��Ϸ״̬
	int state;
	//flag 
	int flag = 1;
	int flag2 = 1;
	
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
		bird1 = new Bird();
		bird2 = new Bird2();		
		//��ʼ������
		score1 = 0;
		score2 = 0;
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
		g2.rotate(-bird1.alpha, bird1.x, bird1.y);
		g2.drawImage(bird1.image, bird1.x-bird1.width/2, bird1.y-bird1.height/2, null);
		g2.rotate(bird1.alpha, bird1.x, bird1.y);
		
		Graphics2D g3 = (Graphics2D) g;
		g3.rotate(-bird2.alpha, bird2.x, bird2.y);
		g3.drawImage(bird2.image, bird2.x-bird2.width/2, bird2.y-bird2.height/2, null);
		g3.rotate(bird2.alpha, bird2.x, bird2.y);
		
		//���Ʒ���
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
	//����������
	public class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			char ch = e.getKeyChar();
			System.out.println("�㰴��"+ch);
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
						 
						//bird1.flappy();
						//bird2.flappy();
						break;
					case GAME_OVER:
					//�ڽ���״̬����������������ݣ��ٴ�תΪ��ʼ״̬
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
		//ȡ��buff
		
		
		//����������ӵ���ǰ�������
		addMouseListener(l);
		//���ϵ��ƶ����ػ�
		while(true) {
			switch(state) {
			case START:
				//С���������ж���
				bird1.fly();
				bird2.fly();
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
				bird1.fly();
				bird2.fly();
				//С�������ƶ�һ��
				bird1.step();
				bird2.step();
				//�������
				if(bird1.x == column1.x || bird1.x == column2.x ) {
					score1++;
				}
				if(bird2.x == column1.x || bird2.x == column2.x ) {
					score2++;
				}
				//����Ƿ�����ײ
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
				//����Ƿ�Ե�BUFF
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
		/*try {
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
		*/
		
		JFrame f = new JFrame();
		GameApp game = new GameApp();
		f.add(game);
		f.setSize(440,600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		game.addKeyListener(new KeyAdapter(){  
            public void keyPressed(KeyEvent e){  
                char charA=e.getKeyChar();  
                System.out.println("�㰴�ˡ�"+charA+"����");
                if(charA == 'a')
                {
                	game.bird1.flappy();
                }
                else if(charA == 'k')
                {
                	game.bird2.flappy();
                }
                }
            }); 
		game.requestFocusInWindow();
		game.action();
	}

}
