package com.itvillage.book.v6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Component("BookHandlerV6")
public class BookHandler {
    private final BookMapper mapper;
    private final BookValidator validator;
    private final BookService bookService;

    public BookHandler(BookMapper mapper, BookValidator validator, BookService bookService) {
        this.mapper = mapper;
        this.validator = validator;
        this.bookService = bookService;
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return request.bodyToMono(BookDto.Post.class)
                .doOnNext(post -> validator.validate(post))
                .flatMap(post -> bookService.saveBook(mapper.bookPostToBook(post)))
                .flatMap(book -> ServerResponse
                        .created(URI.create("/v6/books/" + book.getBookId()))
                        .build());
    }

    public Mono<ServerResponse> updateBook(ServerRequest request) {
        final long bookId = Long.valueOf(request.pathVariable("book-id"));
        return request
                .bodyToMono(BookDto.Patch.class)
                .doOnNext(patch -> validator.validate(patch))
                .flatMap(patch -> {
                    patch.setBookId(bookId);
                    return bookService.updateBook(mapper.bookPatchToBook(patch));
                })
                .flatMap(book -> ServerResponse.ok()
                                        .bodyValue(mapper.bookToResponse(book)));
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        long bookId = Long.valueOf(request.pathVariable("book-id"));

        return bookService.findBook(bookId)
                        .flatMap(book -> ServerResponse
                                .ok()
                                .bodyValue(mapper.bookToResponse(book)));
    }

    public Mono<ServerResponse> getBooks(ServerRequest request) {
        return bookService.findBooks()
                .flatMap(books -> ServerResponse
                        .ok()
                        .bodyValue(mapper.booksToResponse(books)));
    }
}
