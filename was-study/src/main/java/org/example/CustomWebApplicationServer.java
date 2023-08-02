package org.example;

import org.example.calculator.QueryStrings;
import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CustomWebApplicationServer {
    private final int port;

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {        //이 소스들은 모두 다 Http 요청 메시지를 만드는 작업이고, 이러한 작업을 해주는 것이 tomcat이다.
        //이 http 요청 메시지를 어떻게 파싱할 것인지를 살펴보도록 하자.
        try (ServerSocket serverSocket = new ServerSocket(port)) {      //
            logger.info("[org.example.CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[org.example.CustomWebApplicationServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null) {        //클라이언트가 연결되었다는 의미
                logger.info("[org.example.CustomWebApplicationServer] client connected!");

                // Step3 - Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다
                executorService.execute(new ClientRequestHandler(clientSocket));
            }

        }
    }
}
