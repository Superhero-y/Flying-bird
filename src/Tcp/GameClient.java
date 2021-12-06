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
    public static void main(String[] args) throws Exception {
        try {
//            Socket socket = new Socket("10.161.100.222", 4700);
            Socket socket = new Socket("10.161.29.49", 4700);
//            Socket socket = new Socket("127.0.0.1", 4700);

//            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
//            System.out.println(is.readLine());

            while (true) {
                PrintWriter os = new PrintWriter(socket.getOutputStream());
                BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                Scanner scanner = new Scanner(System.in);
//                System.out.println(is.readLine());
                GameApp game = new GameApp();
//                os.println(frame.myPaint.myHero.getX() + " " + frame.myPaint.myHero.getY());

//                String s = is.readLine();
//                String[] s1 = s.split(" ");
//                int a = Integer.parseInt(s1[0]);
//                int b = Integer.parseInt(s1[1]);
//                frame.myPaint.p2.setX(a);
//                frame.myPaint.p2.setY(b);

                
                while (true) {
                	os.println(game.c1);
                    os.flush();
                    String s = is.readLine();
                    String[] s1 = s.split(" ");
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//    public static void main(String args[]) {
//        try{
//            Socket socket=new Socket("127.0.0.1",4700);
//            // �򱾻���4700���ڷ����ͻ�����
//            BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
//            // ��ϵͳ��׼�����豸����BufferedReader����
//            PrintWriter os=new PrintWriter(socket.getOutputStream());
//            // ��Socket����õ��������������PrintWriter����
//            BufferedReader is=new BufferedReader(
//                    new InputStreamReader(socket.getInputStream()));
//            // ��Socket����õ�����������������Ӧ��BufferedReader����
//            String readline;
//            readline = is.readLine();
//            while(true) {
//                MainFrame frame = new MainFrame();
//                frame.myPaint.setPlayState(2);
//                StringTokenizer st = new StringTokenizer(readline);
//                frame.myPaint.setP2X((Integer) st.nextElement());
//                frame.myPaint.setP2Y((Integer) st.nextElement());
//                frame.myPaint.action();
//
//                while (true) {
//                    String osLine = frame.myPaint.myHero.getX() + " " + frame.myPaint.myHero.getY();
//                    os.println(osLine);
//                    os.flush();
//                    st = new StringTokenizer(readline);
//                    frame.myPaint.setP2X((Integer) st.nextElement());
//                    frame.myPaint.setP2Y((Integer) st.nextElement());
//                }
//
//                // ����ϵͳ��׼����õ����ַ���Ϊ��bye����ֹͣѭ��
////                os.println(readline);
////                // ����ϵͳ��׼����������ַ��������Server
////                os.flush();
//                // ˢ���������ʹServer�����յ����ַ���
//                // System.out.println("Client:"+readline);
//                // ��ϵͳ��׼����ϴ�ӡ������ַ���
////                System.out.println("Server:"+is.readLine());
//                // ��Server����һ���ַ���������ӡ����׼�����
////                readline=sin.readLine(); // ��ϵͳ��׼�����һ���ַ���
//            }
////            os.close(); // �ر�Socket�����
////            is.close(); // �ر�Socket������
////            socket.close(); // �ر�Socket
//        }catch(Exception e) {
//            System.out.println("Error"+e); // �������ӡ�����Ϣ
//
//        }
//    }

//    public static void main(String[] args) {
//        try {
//            Socket socket = new Socket("10.161.32.161", 4700);
////            Socket socket = new Socket("127.0.0.1", 4700);
//
//            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
//            PrintWriter os = new PrintWriter(socket.getOutputStream());
//            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            while (true) {
//                Scanner scanner = new Scanner(System.in);
//                MainFrame frame = new MainFrame();
//                frame.myPaint.action();
//                while (true) {
//                    os.println(frame.myPaint.myHero.getX() + " " + frame.myPaint.myHero.getY());
////                    os.println(frame.myPaint.myHero.getY());
//                    os.flush();
//                }
//            }
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

//public class GameClient {
//    private static Socket socket;
//    public static boolean connection_state = false;
//
//    public static void main(String[] args) {
//        while (!connection_state) {
//            connect();
//            try {
//                Thread.sleep(10000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private static void connect(){
//        try {
//            socket = new Socket("10.161.32.161",4700);
//            connection_state = true;
//            PrintWriter oos = new PrintWriter(socket.getOutputStream());
//            InputStreamReader ois = new InputStreamReader(socket.getInputStream());
//            new Thread(new Client_listen(socket,ois)).start();
//            new Thread(new Client_send(socket,oos)).start();
////            new Thread(new Client_heart(socket,oos)).start();
//        }catch (Exception e){
//            e.printStackTrace();
//            connection_state = false;
//        }
//    }
//
//    public static void reconnect(){
//        while (!connection_state){
//            System.out.println("���ڳ�����������.....");
//            connect();
//            try {
//                Thread.sleep(10000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
//class Client_listen implements Runnable{
//    private Socket socket;
//    private InputStreamReader ois;
//
//    Client_listen(Socket socket,InputStreamReader ois){
//        this.socket = socket;
//        this.ois = ois;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true){
//                System.out.println(ois.read());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
//
//class Client_send implements Runnable{
//    private Socket socket;
//    private PrintWriter oos;
//
//    Client_send(Socket socket, PrintWriter oos){
//        this.socket = socket;
//        this.oos = oos;
//    }
//
//    @Override
//    public void run() {
//        try {
//            Scanner scanner = new Scanner(System.in);
////            while (true){
//            //����������󣺵��ô���Ĺ��췽������������
//            MainFrame frame = new MainFrame();
//            frame.myPaint.action();
//            //���������󣺵������Ĺ��췽�����������
////            MyPaint panel =new MyPaint(frame);//�������������Ӽ��̼�����
////            //����������Ϸ�ķ���
////            panel.action();
////            //�������뵽������
////            frame.add(panel);
////            //��ʾ���� true ��ʾ�� false ����
////            frame.setVisible(false);
//
//            while (true) {
//                oos.flush();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                socket.close();
//                GameClient.connection_state = false;
//                GameClient.reconnect();
//            }catch (Exception ee){
//                ee.printStackTrace();
//            }
//        }
//    }
//}