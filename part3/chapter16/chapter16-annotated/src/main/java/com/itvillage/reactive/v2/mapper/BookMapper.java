package com.itvillage.reactive.v2.mapper;

import com.itvillage.reactive.v2.book.Book;
import com.itvillage.reactive.v2.book.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        implementationName = "bookMapperV2")
public interface BookMapper {
    Book bookPostToBook(BookDto.Post requestBody);
    Book bookPatchToBook(BookDto.Patch requestBody);
    BookDto.Response bookToResponse(Book book);
}
