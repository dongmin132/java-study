package org.example;

import org.example.calculator.QueryStrings;
import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class ClientRequestHandler implements Runnable {  //Thread 관련 인터페이스
    private static final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);
    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        //Step2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리
        //하도록 한다
        logger.info("[ClientRequestHandler] new Client {} started ", Thread.currentThread().getName());

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
        } catch(IOException e) {
            logger.info(e.getMessage());
        }
    }
}
