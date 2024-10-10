package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpClientService.class, URL.class})
public class HttpClientServiceTest {

   @Mock
   private HttpURLConnection mockConnection;

   @Mock
   private BufferedReader mockBufferedReader;

   private ObjectMapper objectMapper;

   @Before
   public void setUp() throws Exception {
      objectMapper = new ObjectMapper();

      // Mock URL and HttpURLConnection classes
      PowerMockito.mockStatic(URL.class);
      URL mockURL = PowerMockito.mock(URL.class);
      when(mockURL.openConnection()).thenReturn(mockConnection);
      when(new URL(anyString())).thenReturn(mockURL);

      // Prepare a mock response for InputStream
      String mockResponse = "{\"name\":\"repo1\"}";
      InputStream inputStream = new ByteArrayInputStream(mockResponse.getBytes());
      when(mockConnection.getInputStream()).thenReturn(inputStream);
      when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
   }

   @Test
   public void testGetHttpResponse_Success() throws Exception {
      // Arrange
      String baseUrl = "https://api.github.com";
      String path = "/users/sampleUser/repos";
      Map<String, String> headers = new HashMap<>();
      int connectTimeout = 5000;
      int readTimeout = 5000;

      // Mocking objectMapper readTree
      JsonNode mockJsonNode = objectMapper.readTree("{\"name\":\"repo1\"}");
      PowerMockito.whenNew(ObjectMapper.class).withNoArguments().thenReturn(objectMapper);
      when(objectMapper.readTree(anyString())).thenReturn(mockJsonNode);

      // Act
      JsonNode response = HttpClientService.getHttpResponse(baseUrl, path, headers, connectTimeout, readTimeout);

      // Assert
      assertEquals("repo1", response.get("name").asText());
   }
}
