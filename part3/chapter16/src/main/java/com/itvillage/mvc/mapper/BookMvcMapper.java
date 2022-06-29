package com.itvillage.mvc.mapper;

import com.itvillage.mvc.book.Book;
import com.itvillage.mvc.book.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMvcMapper {
    Book bookPostToBook(BookDto.Post requestBody);
    Book bookPatchToBook(BookDto.Patch requestBody);
    BookDto.Response bookToBookResponse(Book book);
}
