package com.itvillage.book.v3;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class BookHandlerV3 extends BookValidationHandler {
    private final BookMapper mapper;

    public BookHandlerV3(Validator validator, BookMapper mapper) {
        super(validator);
        this.mapper = mapper;
    }

    @Override
    protected Mono<ServerResponse> createBook(Object body, ServerRequest request) {
        return Mono
                .just((BookDto.Post)body)
                .map(post -> mapper.bookPostToBook(post))
                .flatMap(book->
                        ServerResponse
                                .created(URI.create("/v3/books/" + book.getBookId()))
                                .build());
    }

    @Override
    protected Mono<ServerResponse> updateBook(Object body, ServerRequest request) {
        final long bookId = Long.valueOf(request.pathVariable("book-id"));
        return Mono
                .just((BookDto.Patch)body)
                .map(patch -> {
                    patch.setBookId(bookId);
                    return mapper.bookPatchToBook(patch);
                })
                .flatMap(book ->
                        ServerResponse.ok().bodyValue(mapper.bookToResponse(book)));
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
