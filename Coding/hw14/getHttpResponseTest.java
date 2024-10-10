package com.baeldung;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.baeldung.HttpClientService.getHttpResponse;

public class getHttpResponseTest {
    @Test
    void test() throws IOException {
        HttpClientService httpClientService = new HttpClientService();
        Map<String,String> headers = new HashMap<>();
        headers.put("name","Alice");
        JsonNode jsonNode=getHttpResponse("http://localhost:8080/spring-mvc-basics","/",headers,5000,5000);
        System.out.println(jsonNode.get("name"));
    }
}
