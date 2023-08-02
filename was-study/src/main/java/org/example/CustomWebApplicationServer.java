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


public class CustomWebApplicationServer {
    private final int port;

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


                //Step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.

                try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    DataOutputStream dos = new DataOutputStream(out);

//                    String line;
//                    while ((line = br.readLine()) != "") {
//                        System.out.println(line);
//                    }
                    HttpRequest httpRequest = new HttpRequest(br);

                    // GET /calculate?operand1=11&operator=*&operand2=55
                    if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) { // GET 메소드 이면서 /calculate 요청인가?
                        QueryStrings queryStrings = httpRequest.getQueryString();

                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                        String operator = queryStrings.getValue("operator");
                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                        int result = Calculator.calculate((new PositiveNumber(operand1)), operator, new PositiveNumber(operand2));
                        byte[] body = String.valueOf(result).getBytes();

                        HttpResponse response = new HttpResponse(dos);
                        response.response200Header("application/json", body.length);
                        response.responseBody(body);
                    }
                }

            }

        }
    }
}
