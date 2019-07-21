package protocol.http;

import framework.Invocation;
import framework.Protocol;
import framework.URL;

public class HttpProtocol implements Protocol {

    public void start(URL url) {

        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(),url.getPort());

    }

    public String send(URL url, Invocation invocation) {

        HttpClient httpClient = new HttpClient();
        return httpClient.send(url.getHostname(),url.getPort(),invocation);
    }
}
