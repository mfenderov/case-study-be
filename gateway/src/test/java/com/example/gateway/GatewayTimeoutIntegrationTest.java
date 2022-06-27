package com.example.gateway;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GatewayTimeoutIntegrationTest {

    public static MockWebServer mockBackEnd;

    @LocalServerPort
    private int port;

    private HttpClient httpClient;
    private HttpRequest request;

    @BeforeEach
    void setUp() {
        httpClient = HttpClient.newBuilder()
                .build();

        request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://localhost:%s/savings/a/balance", port)))
                .GET()
                .build();
    }

    @BeforeAll
    static void startMockWebServer() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start(8081);
    }

    @AfterAll
    static void stopMockWebServer() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void testTimeout() throws IOException, InterruptedException {
        mockBackEnd.enqueue(new MockResponse().setHeadersDelay(5500, TimeUnit.MILLISECONDS));

        int statusCode = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
                .statusCode();

        assertEquals(504, statusCode);
    }

    @Test
    void test200WithSomeDelay() throws IOException, InterruptedException {
        mockBackEnd.enqueue(new MockResponse().setBody("123.45")
                .setHeadersDelay(4500, TimeUnit.MILLISECONDS));

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        assertAll(() -> assertEquals(200, response.statusCode()),
                () -> assertEquals("123.45", body));
    }

    @Test
    void test200WithoutDelay() throws IOException, InterruptedException {
        mockBackEnd.enqueue(new MockResponse().setBody("678.90"));

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        assertAll(() -> assertEquals(200, response.statusCode()),
                () -> assertEquals("678.90", body));
    }

}
