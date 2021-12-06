package Tcp;

import main.Bird;
import main.Bird2;
import main.Column;
import main.Ground;
import main.booster;
import app.GameApp;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.net.Socket;

public class GameServer {
	private ServerSocket server;
	private Socket client;
	
	void getServer() {
		try {
			server = new ServerSocket(1101);
			System.out.println("服务器建立成功！正在连接……");
			client = server.accept();
			System.out.println("客户端连接成功！");
			getClientMessage();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	void getClientMessage() {
		try {
			while(true) {
				InputStream is = client.getInputStream();
				byte[] b = new byte[1024];
				int len = is.read(b);
				String data = new String(b,0,len);
				System.out.println("客户端发来消息: "+ data);
				
				OutputStream put = client.getOutputStream();
				String putText = "收到了！";
				put.write(putText.getBytes());
			}
		}catch(Exception e) {
			
		}
		
		try {
			if(server != null) {
				server.close();
			}
		}catch(Exception e) {
			
		}
	}
	
    public static void main(String[] args) throws Exception {
    	GameServer server =  new GameServer();
    	server.getServer();
       
    }
}