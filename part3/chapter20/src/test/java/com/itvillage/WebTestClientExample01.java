package com.itvillage;

import com.itvillage.example.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = Chapter20Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTestClientExample01 {
    @LocalServerPort
    private int port;

    @Test
    public void bindToServerTest() {
        WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:" + port)
                .build()
                .get()
                .uri("/v10/books/{book-id}", 1L)
                .exchange()
                .expectBody(BookDto.Response.class);
    }
}
