package com.itvillage.book.v8;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        implementationName = "bookMapperV8")
public interface BookMapper {
    Book bookPostToBook(BookDto.Post requestBody);
    Book bookPatchToBook(BookDto.Patch requestBody);
    BookDto.Response bookToResponse(Book book);
    List<BookDto.Response> booksToResponse(List<Book> books);
}