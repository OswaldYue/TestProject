package com.mgw.two.chapter16;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        System.out.println("接受连接.");
        this.socket = socket;
    }



    @Override
    public void run() {
        try(InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(outputStream)) {

            while (this.running) {
                String message = br.readLine();
                if (message == null) {
                    break;
                }
                System.out.println("Come from client > " + message);
                printWriter.write("echo " + message + "\n");
                printWriter.flush();
            }
        } catch (IOException e) {
            this.running = false;
        }finally {
            this.stop();
        }

    }

    public void stop() {
        System.out.println("连接关闭.");
        if (!this.running) {
            return;
        }

        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            // 关闭失败
            System.out.println("关闭失败");
        }
    }
}
