package com.itvillage.book.v7;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration("bookRouterV7")
public class BookRouter {
    @Bean
    public RouterFunction<?> routeBookV5(BookHandler handler) {
        return route()
                .POST("/v5/books", handler::createBook)
                .PATCH("/v5/books/{book-id}", handler::updateBook)
                .GET("/v5/books", handler::getBooks)
                .GET("/v5/books/{book-id}", handler::getBook)
                .build();
    }
}
