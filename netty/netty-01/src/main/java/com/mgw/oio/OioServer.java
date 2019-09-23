package com.mgw.oio;



import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OioServer {

    public static void main(String[] args) throws Exception{


        ServerSocket serverSocket = new ServerSocket(10101);

        System.out.println("服务器启动...");

        while (true) {

            Socket socket = serverSocket.accept();

            System.out.println("来了一个新客户端...");

            handler(socket);

        }

    }

    public static void handler(Socket socket) {

        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();

            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {

                    System.out.println(new String(bytes,0,read));

                }else {
                    break;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("socket关闭...");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

}
