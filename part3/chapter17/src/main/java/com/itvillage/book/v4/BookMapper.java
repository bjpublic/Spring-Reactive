package com.itvillage.book.v4;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        implementationName = "bookMapperV4")
public interface BookMapper {
    Book bookPostToBook(BookDto.Post requestBody);
    Book bookPatchToBook(BookDto.Patch requestBody);
    com.itvillage.book.v1.BookDto.Response bookToResponse(Book book);
    List<BookDto.Response> booksToResponse(List<Book> books);
}
