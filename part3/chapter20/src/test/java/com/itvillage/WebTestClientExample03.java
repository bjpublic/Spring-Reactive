package com.itvillage;

import com.itvillage.example.BookDto;
import com.itvillage.v10.Book;
import com.itvillage.v10.BookMapper;
import com.itvillage.v10.BookService;
import com.itvillage.v10.BookValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class WebTestClientExample03 {
    @Autowired
    private WebTestClient client;

    @MockBean
    private BookValidator validator;

    @MockBean
    private BookMapper mapper;

    @MockBean
    private BookService bookService;

    @Test
    public void PostBookMockTest() {
        BookDto.Post post = new BookDto.Post("Java 중급",
                "Intermediate Java",
                "Java 중급 프로그래밍 마스터",
                "Kevin1", "222-22-2222-222-2",
                "2022-03-22");

        Book book = new Book();
        book.setBookId(1L);

        doNothing().when(validator).validate(post);
        given(mapper.bookPostToBook(Mockito.any(com.itvillage.v10.BookDto.Post.class))).willReturn(new Book());
        given(bookService.createBook(Mockito.any(Book.class))).willReturn(Mono.just(book));

        client
                .post()
                .uri("/v10/books")
                .bodyValue(post)
                .exchange()
                .expectStatus().isCreated()
                .expectBody();
    }

    // TODO Mockito 적용
    @Test
    public void GetBookMockTest01() {
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

    // TODO Mockito 적용
    @Test
    public void GetBooksMockTest() {
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
