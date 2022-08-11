package com.itvillage;

import com.itvillage.example.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class WebTestClientExample02 {
    @Autowired
    private WebTestClient client;

    @Test
    public void autoConfigurePostBookTest() {
        BookDto.Post post = new BookDto.Post("Java 중급",
                "Intermediate Java",
                "Java 중급 프로그래밍 마스터",
                "Kevin1", "222-22-2222-222-2",
                "2022-03-22");
        client
                .post()
                .uri("/v10/books")
                .bodyValue(post)
                .exchange()
                .expectStatus().isCreated()
                .expectBody();
    }

    @Test
    public void autoConfigureGetBookTest01() {
        client
                .get()
                .uri("/v10/books/{book-id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.bookId").isNotEmpty()
                .jsonPath("$.bookId").isEqualTo(1L)
                .jsonPath("$.titleKorean").exists()
                .jsonPath("$.isbn").isEqualTo("111-11-1111-111-1");
    }

    @Test
    public void autoConfigureGetBookTest02() {
        client
                .get()
                .uri("/v10/books/{book-id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.Response.class);
    }

    @Test
    public void autoConfigureGetBooksTest() {
        client
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v10/books")
                                .queryParam("page", "1")
                                .queryParam("size", "10").build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookDto.Response.class);
    }
}
