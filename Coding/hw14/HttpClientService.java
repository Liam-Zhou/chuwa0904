package com.baeldung;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpClientService {
    private static final Logger LOG= LoggerFactory.getLogger(HttpClientService.class);
    public static JsonNode getHttpResponse(String baseurl, String path, Map<String,String> headers, int connectTimeout,
                                           int readTimeout) throws IOException {
        HttpURLConnection connection = null;
        String url=baseurl+path;
        try{
            URL requestUrl = new URL(url);
            connection=(HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            LOG.info("GetBalance Call URL: "+url);
            for(Map.Entry<String,String> entry: headers.entrySet()){
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            LOG.info("GetBalance Call Headers: "+connection.getHeaderFields());
            StringBuilder response=new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                System.out.println("hello");
                while((line= reader.readLine())!=null){
                    System.out.println("test"+line);
                    response.append(line);
                }
            }
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readTree(response.toString());
            }else{
                throw new IOException("HTTP request failed with response code: "+connection.getResponseCode());
            }
        }finally{
            if(connection!=null){
                connection.disconnect();
            }
        }
    }
}
