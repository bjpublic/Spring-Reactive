package com.itvillage;

import com.itvillage.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringReactiveHeadOfficeApplication {
	private URI baseUri = UriComponentsBuilder.newInstance().scheme("http")
			.host("localhost")
			.port(8080)
			.path("/v1/books")
			.build()
			.encode()
			.toUri();
	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveHeadOfficeApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader() {
		return (String... args) -> {
			// 서버쪽으로 대량의 호출 및 시간 측정을 한다.
			StopWatch stopWatch = new StopWatch("client -> server");

			for (int i = 1; i <= 30; i++) {
				stopWatch.start("# 도서 목록 조회");
					this.getBook(i)
							.subscribe(
									book -> {
										// 전달 받은 도서를 처리.
									},
									error -> {},
									() -> log.info("# 도서 처리 완료")
							);
				stopWatch.stop();
			}



			log.info(stopWatch.prettyPrint());
			log.info("# 전체 조회 시간: {} ms", stopWatch.getTotalTimeMillis());
		};
	}

	private Mono<Book> getBook(long bookId) {
		URI getBooksUri = UriComponentsBuilder.fromUri(baseUri)
				.path("/{book-id}")
				.build()
				.expand(bookId)
				.encode()
				.toUri(); // http://localhost:8080/v1/books/{book-id}



		return WebClient.create()
				.get()
				.uri(getBooksUri)
				.retrieve()
				.bodyToMono(Book.class);
	}
}
