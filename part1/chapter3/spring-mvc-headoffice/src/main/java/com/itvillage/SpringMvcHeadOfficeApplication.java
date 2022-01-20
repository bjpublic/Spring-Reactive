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

            for (int i = 1; i <= 30; i++) {
                stopWatch.start("# 도서 조회");
                Book book = this.getBook(i);
                log.info("# 도서 처리 완료");
                stopWatch.stop();
            }


            log.info(stopWatch.prettyPrint());
            log.info("# 전체 조회 시간: {} ms", stopWatch.getTotalTimeMillis());
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

        ResponseEntity<Book> response = restTemplate.getForEntity(getBooksUri, Book.class);
        Book book = response.getBody();

        return book;
    }
}
