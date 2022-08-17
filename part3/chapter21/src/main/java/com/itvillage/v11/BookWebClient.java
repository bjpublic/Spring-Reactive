package com.itvillage.v11;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class BookWebClient {
    @Bean
    public ApplicationRunner streamingBooks() {
        return (ApplicationArguments arguments) -> {
            WebClient webClient = WebClient.create("http://localhost:8080");
            Flux<BookDto.Response> response =
                    webClient
                            .get()
                            .uri("http://localhost:8080/v11/streaming-books")
                            .retrieve()
                            .bodyToFlux(BookDto.Response.class);

            response.subscribe(book -> {
                        log.info("bookId: {}", book.getBookId());
                        log.info("titleKorean: {}", book.getTitleKorean());
                        log.info("titleEnglish: {}", book.getTitleEnglish());
                        log.info("description: {}", book.getDescription());
                        log.info("author: {}", book.getAuthor());
                        log.info("isbn: {}", book.getIsbn());
                        log.info("publishDate: {}", book.getPublishDate());
                        log.info("=======================================");
                    },
                    error -> log.error("# error happened: ", error));
        };
    }
}
