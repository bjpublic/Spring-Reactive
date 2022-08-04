package com.itvillage.book.v3;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Component("bookHandlerV3")
public class BookHandler {
    private final BookMapper mapper;
    private final BookValidator validator;

    public BookHandler(BookMapper mapper, BookValidator validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return request.bodyToMono(BookDto.Post.class)
                .doOnNext(post -> validator.validate(post))
                .map(post -> mapper.bookPostToBook(post))
                .flatMap(book -> ServerResponse
                        .created(URI.create("/v3/books/" + book.getBookId()))
                        .build());
    }

    public Mono<ServerResponse> updateBook(ServerRequest request) {
        final long bookId = Long.valueOf(request.pathVariable("book-id"));
        return request
                .bodyToMono(BookDto.Patch.class)
                .doOnNext(patch -> validator.validate(patch))
                .map(patch -> {
                    patch.setBookId(bookId);
                    return mapper.bookPatchToBook(patch);
                })
                .flatMap(book -> ServerResponse.ok()
                        .bodyValue(mapper.bookToResponse(book)));
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        long bookId = Long.valueOf(request.pathVariable("book-id"));
        Book book =
                new Book(bookId,
                        "Java 고급",
                        "Advanced Java",
                        "Kevin",
                        "111-11-1111-111-1",
                        "Java 중급 프로그래밍 마스터",
                        "2022-03-22",
                        LocalDateTime.now(),
                        LocalDateTime.now());
        return ServerResponse
                .ok()
                .bodyValue(mapper.bookToResponse(book))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getBooks(ServerRequest request) {
        List<Book> books = List.of(
                new Book(1L,
                        "Java 고급",
                        "Advanced Java",
                        "Kevin",
                        "111-11-1111-111-1",
                        "Java 중급 프로그래밍 마스터",
                        "2022-03-22",
                        LocalDateTime.now(),
                        LocalDateTime.now()),
                new Book(2L,
                        "Kotlin 고급",
                        "Advanced Kotlin",
                        "Kevin",
                        "222-22-2222-222-2",
                        "Kotlin 중급 프로그래밍 마스터",
                        "2022-05-22",
                        LocalDateTime.now(),
                        LocalDateTime.now())
        );
        return ServerResponse
                .ok()
                .bodyValue(mapper.booksToResponse(books));
    }
}
