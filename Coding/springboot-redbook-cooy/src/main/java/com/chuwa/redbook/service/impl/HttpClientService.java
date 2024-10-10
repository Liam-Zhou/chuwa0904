package com.chuwa.redbook.service.impl;

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
    private static final Logger LOG = LoggerFactory.getLogger(HttpClientService.class);

    public static JsonNode getHttpResponse(String baseUrl, String path, Map<String, String> headers, int connectTimeout, int readTimeout) throws IOException {
        if (baseUrl == null || path == null) {
            throw new IllegalArgumentException("Base URL and path must not be null");
        }
        HttpURLConnection connection = null;
        String url = baseUrl + path;
        try {
            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            connection.setRequestMethod("GET");
            LOG.info("GetBalance Call URL" + url);
            for(Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
            LOG.info("GetBalance Call Headers" + connection.getRequestProperties());
            StringBuffer response = new StringBuffer();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null)
                    response.append(line);
            }

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(response.toString());
                System.out.println(node);
                return node;
            }else
                throw new IOException("HTTP request failed with reponse code " + connection.getResponseCode());
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
