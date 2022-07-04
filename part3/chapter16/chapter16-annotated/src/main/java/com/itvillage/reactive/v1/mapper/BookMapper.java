package com.itvillage.reactive.v1.mapper;

import com.itvillage.reactive.v1.book.Book;
import com.itvillage.reactive.v1.book.BookDto;
import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookPostToBook(BookDto.Post requestBody);
    Book bookPatchToBook(BookDto.Patch requestBody);
    BookDto.Response bookToResponse(Book book);
    default Mono<BookDto.Response> bookToBookResponse(Mono<Book> mono) {
        return mono.flatMap(book -> Mono.just(bookToResponse(book)));
    }
}