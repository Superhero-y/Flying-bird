package Tcp;

import main.Bird;


import main.Bird2;
import main.Column;
import main.Ground;
import main.booster;
import app.GameApp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import main.Bird2;
import main.Column;
import main.Ground;
import main.booster;
import app.GameApp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFrame;

public class GameClient { //10.161.16.63
	//建立客户端函数
	public static void main(String args[]) {
	      try{
	        Socket socket=new Socket("10.161.16.63",4700); 
	        //
	        JFrame f = new JFrame();
			GameApp game = new GameApp();
			f.add(game);
			f.setSize(440,600);
			f.setLocationRelativeTo(null);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
			
			game.addKeyListener(new KeyAdapter(){  
	            public void keyPressed(KeyEvent e){  
	                char charA= e.getKeyChar();  
	                System.out.println("你按了《"+charA+"》键");
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
	        
	        
	        BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
	        //
	        PrintWriter os=new PrintWriter(socket.getOutputStream());
	        //
	        BufferedReader is=new BufferedReader(
	new InputStreamReader(socket.getInputStream()));
	        //
	        String readline;
	        readline=sin.readLine(); //
	        while(!readline.equals("bye")){ 
	        //
	          os.println(readline); 
	          //
	          os.flush(); 
	          //
	          System.out.println("Client:"+readline); 
	          //
	          System.out.println("Server:"+is.readLine()); 
	          //
	          readline=sin.readLine(); //
	        } 
	        os.close(); //
	        is.close(); //
	        socket.close(); //
	      }catch(Exception e) {
	        System.out.println("Error"+e); //
	      }
	  }

}