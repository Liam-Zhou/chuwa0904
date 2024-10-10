package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.service.impl.HttpClientService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HttpClientService.class)
public class HttpClientServiceTest {
    @Mock
    private HttpURLConnection mockConnection;
    @Mock
    private InputStream mockInputStream;
    @Mock
    private BufferedReader mockBufferedReader;

    private final String mockResponse = "{\"key\": \"value\"}";

    @Before
    public void setUp() throws Exception {
        // Mocking URL and HttpURLConnection behavior
        URL mockUrl = PowerMockito.mock(URL.class);
        HttpURLConnection mockConnection = PowerMockito.mock(HttpURLConnection.class);
        
        PowerMockito.stub(PowerMockito.method(URL.class, "openConnection")).toReturn(mockConnection);
        
        // Now you can stub the behavior of the mockConnection object
        when(mockConnection.getInputStream()).thenReturn(mockInputStream);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        whenNew(BufferedReader.class).withAnyArguments().thenReturn(mockBufferedReader);
        // Mocking the input stream to return a sample JSON response
        when(mockInputStream.read(any(byte[].class), anyInt(), anyInt())).thenReturn(-1); // End of stream
        when(mockBufferedReader.readLine())
                .thenReturn(mockResponse)
                .thenReturn(null); // To simulate the end of reading
    }

    @Test
    public void testGetHttpResponse() throws Exception {
        // Mocking ObjectMapper behavior to parse the mock response
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        JsonNode expectedJsonNode = new ObjectMapper().readTree(mockResponse);
        when(objectMapper.readTree(mockResponse)).thenReturn(expectedJsonNode);
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        // Calling the method to test
        JsonNode result = HttpClientService.getHttpResponse(
                "https://api.github.com",
                "/users/testuser/repos?page=1",
                map,
                5000,
                5000
        );

        // Verifying that the returned result is as expected
        assertEquals(expectedJsonNode, result);
    }

}
