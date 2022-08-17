package com.itvillage.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
//@Configuration
public class WebClientExample01 {
    @Bean
    public ApplicationRunner examplesWebClient() {

        return (ApplicationArguments arguments) -> {
            exampleWebClient01();
            exampleWebClient02();
            exampleWebClient03();
            exampleWebClient04();
        };
    }

    private void exampleWebClient01() {
        BookDto.Post requestBody = new BookDto.Post("Java 중급",
                "Intermediate Java",
                "Java 중급 프로그래밍 마스터",
                "Kevin1", "222-22-2222-222-2",
                "2022-03-22");

        WebClient webClient = WebClient.create();
        Mono<ResponseEntity<Void>> response =
                webClient
                        .post()
                        .uri("http://localhost:8080/v10/books")
                        .bodyValue(requestBody)
                        .retrieve()
                        .toEntity(Void.class);

        response.subscribe(res -> {
           log.info("response status: {}", res.getStatusCode());
           log.info("Header Location: {}", res.getHeaders().get("Location"));
        });
    }

    private void exampleWebClient02() {
        BookDto.Patch requestBody =
                new BookDto.Patch.PatchBuilder().titleKorean("Java 고급")
                .titleEnglish("Advanced Java")
                .description("Java 고급 프로그래밍 마스터")
                .author("Tom")
                .build();

        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<BookDto.Response> response =
                webClient
                        .patch()
                        .uri("http://localhost:8080/v10/books/{book-id}", 20)
                        .bodyValue(requestBody)
                        .retrieve()
                        .bodyToMono(BookDto.Response.class);

        response.subscribe(book -> {
            log.info("bookId: {}", book.getBookId());
            log.info("titleKorean: {}", book.getTitleKorean());
            log.info("titleEnglish: {}", book.getTitleEnglish());
            log.info("description: {}", book.getDescription());
            log.info("author: {}", book.getAuthor());
        });
    }

    private void exampleWebClient03() {
        Mono<BookDto.Response> response =
                WebClient
                        .create("http://localhost:8080")
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/v10/books/{book-id}")
                                .build(21))
                                .retrieve()
                                .bodyToMono(BookDto.Response.class);

        response.subscribe(book -> {
            log.info("bookId: {}", book.getBookId());
            log.info("titleKorean: {}", book.getTitleKorean());
            log.info("titleEnglish: {}", book.getTitleEnglish());
            log.info("description: {}", book.getDescription());
            log.info("author: {}", book.getAuthor());
        });
    }

    private void exampleWebClient04() {
        Flux<BookDto.Response> response =
                WebClient
                        .create("http://localhost:8080")
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/v10/books")
                                .queryParam("page", "1")
                                .queryParam("size", "10")
                                .build())
                        .retrieve()
                        .bodyToFlux(BookDto.Response.class);

        response
                .map(book -> book.getTitleKorean())
                .subscribe(bookName -> log.info("book name: {}", bookName));
    }
}
