package com.itvillage.reactive.v1.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final BookService bookService;
    private final BookMapper mapper;

    public BookController(BookService bookService, BookMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono postBook(@RequestBody BookDto.Post requestBody) {
        Mono<Book> book =
                bookService.createBook(mapper.bookPostToBook(requestBody));

        Mono<BookDto.Response> response = mapper.bookToBookResponse(book);
        return response;
    }

    @PatchMapping("/{book-id}")
    public Mono patchBook(@PathVariable("book-id") long bookId,
                                    @RequestBody BookDto.Patch requestBody) {
        requestBody.setBookId(bookId);
        Mono<Book> book =
                bookService.updateBook(mapper.bookPatchToBook(requestBody));

        return mapper.bookToBookResponse(book);
    }

    @GetMapping("/{book-id}")
    public Mono getBook(@PathVariable("book-id") long bookId) {
        Mono<Book> book = bookService.findBook(bookId);

        return mapper.bookToBookResponse(book);
    }
}