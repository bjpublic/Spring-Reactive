package com.itvillage.mvc.book;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMvcMapper {
    Book bookPostToBook(BookDto.Post requestBody);
    Book bookPatchToBook(BookDto.Patch requestBody);
    BookDto.Response bookToBookResponse(Book book);
}
