package com.itvillage.book.v8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration("bookRouterV8")
public class BookRouter {
    @Bean
    public RouterFunction<?> routeBookV8(BookHandler handler) {
        return route()
                .POST("/v8/books", handler::createBook)
                .PATCH("/v8/books/{book-id}", handler::updateBook)
                .GET("/v8/books", handler::getBooks)
                .GET("/v8/books/{book-id}", handler::getBook)
                .build();
    }
}
