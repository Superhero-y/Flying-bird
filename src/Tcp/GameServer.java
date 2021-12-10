package Tcp;

import main.Bird;


import main.Bird2;
import main.Column;
import main.Ground;
import main.booster;
import app.GameApp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import java.net.Socket;

public class GameServer {
	public char ch;
	public String state = "0";
	public static void main(String[] args) {
		new GameServer();
	}
	public GameServer() {
		initial();
		return;
	}
	
	public void initial() {
		try{
	        ServerSocket server=null;
	        try{ 
	          //创建一个端口监听客户请求
	          server=new ServerSocket(5700);        
	        }catch(Exception e) {
	          System.out.println("can not listen to:"+e); 
	        //出错打印出错信息
	        }
	        
	        Socket socket=null;
	        try {
	            socket=server.accept();   
	            //阻塞等待客户请求，有客户到来则产生一个Socket对象
	            JFrame f = new JFrame();
		  		GameApp game = new GameApp();
		  		f.add(game);
		  		f.setSize(440,600);
		  		f.setLocationRelativeTo(null);
		  		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  		f.setVisible(true);
		  		game.requestFocusInWindow();
		  		System.out.println("OK");
		  		game.actioner();
		  		
		  		game.addMouseListener((MouseListener) new MouseAdapter() {
		  			public void mouseClicked(MouseEvent e) {
		  				game.state = game.RUNNING;
		  				state = "1";
		  			}
		  		});	
		  		
		  		//本地控制玩家1跳跃
		  		game.addKeyListener(new KeyAdapter() {
		  			 public void keyPressed(KeyEvent e){  
		                 ch= e.getKeyChar();  
		                 System.out.println("你输入了："+ ch);
		                 if(ch == 'a')
		                 {
		                 	game.bird1.flappy();
		                 }
		            }
		  		});
		  		
		  		//game.action();
		  		
	        
	  		String line;
	        BufferedReader is=new BufferedReader(
	        new InputStreamReader(socket.getInputStream()));
	         //
	        PrintWriter os=new PrintWriter(socket.getOutputStream());
	         //
	     
	        BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
	         //
	        
//	        System.out.println("Client:"+is.readLine()); 
//	        //
//	        line=is.readLine(); 
	        	System.out.println("yes!!!!!!!!!");
	        //
	        while(true){ 
	          //String c = ""+ch;
	          System.out.println("coming!"); 
//	          if(state.equals("1"))
//	          {
	        	  System.out.println("传过去啦！！"+state);
	        	  	        	  
	          
	        	  String str = ch + " " + game.column1.x + " " + game.column1.y + " ";
	        	  str += game.column2.x + " " + game.column2.y + " " + game.bos.x + " " + game.bos.y + " " +state;
	        	  System.out.println(str);
	        	  os.println(str);
	        	  ch = '#';
	        	  state = "0";
	        	  
	          
	          //
	          os.flush(); 
	          line=is.readLine();
	          //读入的字符串，需要服务端界面红色小鸟跳一次
	          System.out.println("读入数据line是:"+line); 
	          
	          
	          
	          if((line.trim()).equals("a")) {
	        	  System.out.println("进入条件了！鸟2飞翔"); 
	        	  game.bird2.flappy();
	          }
	          //
	          System.out.println("Client:"+line);
	          //
//	          line=is.readLine(); 
	          
	          
	          //
	        }  
	        
		}catch(Exception e) {
			System.out.println("Error: " + e);
		}
		}catch(Exception e) {
			System.out.println("Error: " + e);}
		
	}

	
}
