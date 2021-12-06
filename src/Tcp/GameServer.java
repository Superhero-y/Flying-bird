//package Tcp;
//
//import main.Bird;
//import main.Bird2;
//import main.Column;
//import main.Ground;
//import main.booster;
//import app.GameApp;
//
//import java.io.*;
//import java.net.*;
//import java.util.Scanner;
//import java.util.StringTokenizer;
//
//public class GameServer {
//    public static void main(String[] args) throws Exception {
//        try {
//            ServerSocket server = null;
//            Socket socket = null;
//
//            try {
//                server = new ServerSocket(4700);
//                try {
//                    socket = server.accept();
////                    BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
////                    PrintWriter os = new PrintWriter(socket.getOutputStream());
//                    while (true) {
//                        BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                        PrintWriter os = new PrintWriter(socket.getOutputStream());
////                        Scanner scanner = new Scanner(System.in);
//                        GameApp game = new GameApp();
//                        
////                        String s = is.readLine();
////                        String[] s1 = s.split(" ");
////                        int a = Integer.parseInt(s1[0]);
////                        int b = Integer.parseInt(s1[1]);
//////
////                        frame.myPaint.p2.setX(a);
////                        frame.myPaint.p2.setY(b);
//                     
//                        while (true) {
//                            os.println(game.c1);
//                            os.flush();
//                            String s = is.readLine();
//                            String[] s1 = s.split(" ");
//                            
//                        }
//                    }
//                } catch (IOException e) {
//                    System.out.println("Error." + e);
//                }
//            } catch (IOException e) {
//                System.out.println("Error." + e);
//            }
//        } finally {
//            System.out.println("Byebye");
//        }
//    }
//}