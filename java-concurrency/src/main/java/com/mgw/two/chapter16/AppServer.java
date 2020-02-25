package com.mgw.two.chapter16;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppServer extends Thread{

    private int port;

    private static final int DEFAULT_PORT = 12722;

    private volatile boolean start = true;

    private List<ClientHandler> clientHandlers = new ArrayList<>();

    private final  ExecutorService executor = Executors.newFixedThreadPool(10);

    private ServerSocket server;

    public AppServer() {

        this(DEFAULT_PORT);
    }

    public AppServer(int port) {

        this.port = port;
    }



    @Override
    public void run() {
        try {
            this.server = new ServerSocket(port);
            System.out.println("server start.");
            while (start) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executor.submit(clientHandler);
                this.clientHandlers.add(clientHandler);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            this.dispose();
        }
    }

    private void dispose() {

        clientHandlers.stream().forEach(ClientHandler::stop);
        this.executor.shutdown();
        System.out.println("server stop.");
    }

    public void shutdown() throws IOException {

        this.start = false;
        this.interrupt();
        this.server.close();

    }
}
