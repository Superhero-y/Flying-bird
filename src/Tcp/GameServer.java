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
			System.out.println("�����������ɹ����������ӡ���");
			client = server.accept();
			System.out.println("�ͻ������ӳɹ���");
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
				System.out.println("�ͻ��˷�����Ϣ: "+ data);
				
				OutputStream put = client.getOutputStream();
				String putText = "�յ��ˣ�";
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