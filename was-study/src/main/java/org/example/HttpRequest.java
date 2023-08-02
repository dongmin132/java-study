package org.example;

import org.example.calculator.QueryStrings;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private final RequestLine requestLine;

    public HttpRequest(BufferedReader br) throws IOException {

        this.requestLine = new RequestLine((br.readLine()));        //Http 요청 메시지에 첫번째 라인이 RequestLine이므로 첫번쨰라인을 전달 해준다.

    }

    public QueryStrings getQueryString() {
        return requestLine.getQueryStrings();
    }

    public boolean isGetRequest() {
        return requestLine.isGetRequest();
    }

    public boolean matchPath(String path) {
        return requestLine.matchPath(path);
    }
}
