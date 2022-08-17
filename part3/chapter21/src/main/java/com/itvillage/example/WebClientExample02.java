package com.itvillage.example;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
//@Configuration
public class WebClientExample02 {
    @Bean
    public ApplicationRunner examplesWebClient02() {

        return (ApplicationArguments arguments) -> {
            exampleWebClient01();
//            exampleWebClient02();
        };
    }

    private void exampleWebClient01() {
        HttpClient httpClient =
                HttpClient
                        .create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 500)
                        .responseTimeout(Duration.ofMillis(500))
                        .doOnConnected(connection ->
                            connection
                                    .addHandlerLast(
                                            new ReadTimeoutHandler(500,
                                                                TimeUnit.MILLISECONDS))
                                    .addHandlerLast(
                                            new WriteTimeoutHandler(500,
                                                                TimeUnit.MILLISECONDS)));

        Flux<BookDto.Response> response =
                WebClient
                        .builder()
                        .baseUrl("http://localhost:8080")
                        .clientConnector(new ReactorClientHttpConnector(httpClient))
                        .build()
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
                .subscribe(bookName -> log.info("book name2: {}", bookName));
    }

    private void exampleWebClient02() {
        BookDto.Post post = new BookDto.Post("Java 중급",
                "Intermediate Java",
                "Java 중급 프로그래밍 마스터",
                "Kevin1", "333-33-3333-333-3",
                "2022-03-22");
        WebClient webClient = WebClient.create();
        webClient
                .post()
                .uri("http://localhost:8080/v10/books")
                .bodyValue(post)
                .exchangeToMono(response -> {
                    if(response.statusCode().equals(HttpStatus.CREATED))
                        return response.toEntity(Void.class);
                    else
                        return response
                                .createException()
                                .flatMap(throwable -> Mono.error(throwable));
                })
                .subscribe(res -> {
                    log.info("response status2: {}", res.getStatusCode());
                    log.info("Header Location2: {}", res.getHeaders().get("Location"));
                    },
                    error -> log.error("Error happened: ", error));
    }
}
