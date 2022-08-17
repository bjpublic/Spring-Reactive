package com.itvillage.v11;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouter {
    @Bean
    public RouterFunction<?> routeBook(BookHandler handler) {
        return route()
                .POST("/v11/books", handler::createBook)
                .PATCH("/v11/books/{book-id}", handler::updateBook)
                .GET("/v11/books", handler::getBooks)
                .GET("/v11/books/{book-id}", handler::getBook)
                .build();
    }

    @Bean
    public RouterFunction<?> routeStreamingBook(BookService bookService,
                                                BookMapper mapper) {
        return route(RequestPredicates.GET("/v11/streaming-books"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(bookService
                                        .streamingBooks()
                                        .map(book -> mapper.bookToResponse(book))
                                ,
                                BookDto.Response.class));
    }
}
