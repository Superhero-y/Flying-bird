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

public class GameClient {
private Socket client;	//����ͻ����׽���
	
	//�����ͻ��˺���
	void getClient()
	{
		try {
			client = new Socket("10.161.16.63", 1101);	//�����ͻ��ˣ�ʹ�õ�IPΪ127.0.0.1���˿ںͷ�����һ��Ϊ1100
			System.out.println("�ͻ��˽����ɹ���");
			
			setClientMessage();		//���ÿͻ�����Ϣд�뺯��
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//����ͻ�����Ϣд�뺯��
	void setClientMessage()
	{
		try {		
			OutputStream pt = client.getOutputStream();		//�����ͻ�����Ϣ�����
			String printText = "��������ã����ǿͻ��ˣ�";	
			pt.write(printText.getBytes());		//�Զ����Ƶ���ʽ����Ϣ�������
			
			InputStream input = client.getInputStream();	//�����ͻ�����Ϣ������
			byte [] b = new byte[1024];		//�����ֽ�����
			int len = input.read(b);	//��ȡ���յĶ�������Ϣ��
			String data = new String(b, 0,len);
			System.out.println("�յ���������Ϣ��" + data);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			//����ͻ�����Ϣ����Ϊ�գ���˵���ͻ����Ѿ��������ӣ��رտͻ���
			if (client != null) {
				client.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
    public static void main(String[] args) throws Exception {
       GameClient client = new GameClient();
       client.getClient();
    }
}