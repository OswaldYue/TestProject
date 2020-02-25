package com.mgw.two.chapter16;

import java.io.IOException;

public class AppServerClient {

    public static void main(String[] args) throws InterruptedException, IOException {

        AppServer server = new AppServer(13345);
        server.start();

        Thread.sleep(45_000);
        server.shutdown();

    }
}
