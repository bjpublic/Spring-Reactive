package com.itvillage.mvc.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/mvc/books")
public class BookMvcController {
    private final BookMvcService bookMvcService;
    private final BookMvcMapper mapper;

    public BookMvcController(BookMvcService bookMvcService, BookMvcMapper mapper) {
        this.bookMvcService = bookMvcService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postBook(@RequestBody BookDto.Post requestBody) {
        Book book = bookMvcService.createBook(mapper.bookPostToBook(requestBody));
        return ResponseEntity.ok(mapper.bookToBookResponse(book));
    }

    @PatchMapping("/{book-id}")
    public ResponseEntity patchBook(@PathVariable("book-id") long bookId,
                                    @RequestBody BookDto.Patch requestBody) {
        requestBody.setBookId(bookId);
        Book book =
                bookMvcService.updateBook(mapper.bookPatchToBook(requestBody));
        return ResponseEntity.ok(mapper.bookToBookResponse(book));
    }

    @GetMapping("/{book-id}")
    public ResponseEntity getBook(@PathVariable("book-id") long bookId) {
        Book book = bookMvcService.findBook(bookId);
        return ResponseEntity.ok(mapper.bookToBookResponse(book));
    }
}
