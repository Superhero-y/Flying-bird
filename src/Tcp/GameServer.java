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

import javax.swing.JFrame;

import java.net.Socket;

public class GameServer {
	 public static void main(String args[]) {
	      try{
	        ServerSocket server=null;
	        try{ 
	          //
	          server=new ServerSocket(4700);        
	}catch(Exception e) {
	          System.out.println("can not listen to:"+e); 
	        //
	        }
	        Socket socket=null;
	        try{
	          socket=server.accept();   //  
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
	                  System.out.println("Äã°´ÁË¡¶"+charA+"¡·¼ü");
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
	        }catch(Exception e) {
	          System.out.println("Error."+e);  //

	                 }
	        String line;
	        BufferedReader is=new BufferedReader(
	new InputStreamReader(socket.getInputStream()));
	         //
	        PrintWriter os=new PrintWriter(socket.getOutputStream());
	         //
	        BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
	         //
	        System.out.println("Client:"+is.readLine()); 
	        //
	        line=sin.readLine(); 
	        //
	        while(!line.equals("bye")){ 
	        //
	          os.println(line); 
	          //
	          os.flush(); 
	          //
	          System.out.println("Server:"+line); 
	          //
	          System.out.println("Client:"+is.readLine());
	          //
	          line=sin.readLine(); 
	          //
	        }  
	        os.close(); //
	        is.close(); //
	        socket.close(); //
	        server.close(); //
	      }catch(Exception e){
	        System.out.println("Error:"+e); 
	        //
	      }
	    }

}