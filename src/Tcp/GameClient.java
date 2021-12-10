/*package Tcp;

import app.GameApp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;


import javax.swing.JFrame;

public class GameClient { //10.161.16.63
	//建立客户端函数
	public static void main(String args[]) {
	      try{
	        Socket socket=new Socket("10.161.32.26",4700); 
	        //
	        JFrame f = new JFrame();
			GameApp game = new GameApp();
			f.add(game);
			f.setSize(440,600);
			f.setLocationRelativeTo(null);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
			
	        PrintWriter os=new PrintWriter(socket.getOutputStream());
	        //
	        BufferedReader is=new BufferedReader(
	new InputStreamReader(socket.getInputStream()));
	        //
	        String readline;
	        readline = is.readLine(); //
	        while(true){ 
	        //
	          if(readline == "a") {
	        	  game.bird1.flappy();
	          }
	          os.println(game.c1); 
	          //
	          os.flush(); 
	          //
	          System.out.println("Client:"+readline); 
	          //
	          System.out.println("Server:"+is.readLine()); 
	          //
	          readline=is.readLine(); 
	        } 
	      }catch(Exception e) {
	        System.out.println("Error"+e); //
	      }
	  }
2021/12/10 19:40
}*/

package Tcp;

import app.GameApp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;


import javax.swing.JFrame;

public class GameClient { //10.161.16.63

	//建立客户端函数
	public static char ch;
	public static String state = "0";
	public static void main(String args[]) {

	      try {
			  Socket socket = new Socket("10.161.178.123", 5700);
			  //
			  JFrame f = new JFrame();
			  GameApp game = new GameApp();
			  f.add(game);
			  f.setSize(440, 600);
			  f.setLocationRelativeTo(null);
			  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			  f.setVisible(true);
			  game.requestFocusInWindow();
			  game.actioner();
			  System.out.println("!!!!!!mc");

//			  game.addMouseListener(new MouseAdapter() {
//					public void mouseClicked(MouseEvent e){
//						game.state = 0;
//						state = "1";
//					}
//			  });

			  game.addKeyListener(new KeyAdapter() {
				  public void keyPressed(KeyEvent e){
					  ch = e.getKeyChar();
					  System.out.println("你输入了啥啊？ --"+ ch);
					  if(ch == 'a')
					  {
						  game.bird2.flappy();
					  }
				  }
			  });



	        PrintWriter os=new PrintWriter(socket.getOutputStream());
	        //
	        BufferedReader is=new BufferedReader(
	new InputStreamReader(socket.getInputStream()));
	        //
	        String readline;
	        //readline = is.readLine(); //
	        while(true){
				System.out.println("进来while循环了！" +ch);
//				os.println(state);

	          os.println(ch);
			  ch = '#';
	          //
	          os.flush();
				readline = is.readLine();
				System.out.println("readline: "+readline);

//				if(readline.equals("1")){
//					game.state = game.START;
//				}
//				if( readline.equals("1")){
//					System.out.println("我进来啦 开始游戏！！");
//					game.state = game.RUNNING;
//				}else{
					String cut = " ";
					String[] newStr = readline.split(cut);
				for(String string:newStr){
					System.out.println(string);
				}

					if( (newStr[0]).equals("a")) {
						System.out.println("yes 让鸟1飞翔");
						game.bird1.flappy();
					}
					game.column1.x = Integer.parseInt(newStr[1]);
					game.column1.y = Integer.parseInt(newStr[2]);
					game.column2.x = Integer.parseInt(newStr[3]);
					game.column2.y = Integer.parseInt(newStr[4]);
					game.bos.x = Integer.parseInt(newStr[5]);
					game.bos.y = Integer.parseInt(newStr[6]);
					if(newStr[7].equals("1")){
						game.state = game.RUNNING;
					}
//				}



	          //
	          System.out.println("Client:"+readline);
	          //
	          System.out.println("Server:"+readline);
	          //
	         // readline=is.readLine();
	        }
	      }catch(Exception e){
			  System.out.println(e);
		  }}


}

