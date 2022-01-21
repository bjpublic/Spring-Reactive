package com.itvillage;

import com.itvillage.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalTime;

/**
 * 본사 API 서버에 도서 정보를 요청하는 검색용 클라이언트 PC 역할을 한다.
 */
@Slf4j
@SpringBootApplication
public class SpringMvcHeadOfficeApplication {
    private URI baseUri = UriComponentsBuilder.newInstance().scheme("http")
            .host("localhost")
            .port(8080)
            .path("/v1/books")
            .build()
            .encode()
            .toUri();

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcHeadOfficeApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return (String... args) -> {
            // 서버쪽으로 대량의 호출 및 시간 측정을 한다.
            StopWatch stopWatch = new StopWatch("client -> server");

            log.info("# 요청 시작 시간: {}", LocalTime.now());
            stopWatch.start("# 도서 조회");
            for (int i = 1; i <= 30; i++) {
                Book book = this.getBook(i);
            }
            stopWatch.stop();

            log.info(stopWatch.prettyPrint());
            log.info("# 응답 완료 시간: {}", LocalTime.now());
            log.info("# 전체 처리 시간: {} ms", stopWatch.getTotalTimeMillis());
        };
    }

    private Book getBook(long bookId) {
        RestTemplate restTemplate = new RestTemplate();

        URI getBooksUri = UriComponentsBuilder.fromUri(baseUri)
                .path("/{book-id}")
                .build()
                .expand(bookId)
                .encode()
                .toUri(); // http://localhost:8080/v1/books/{book-id}

        ResponseEntity<Book> response =
                restTemplate.getForEntity(getBooksUri, Book.class);
        Book book = response.getBody();

        return book;
    }
}
