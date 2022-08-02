package com.itvillage.book.v9;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration("bookRouterV9")
public class BookRouter {
    @Bean
    public RouterFunction<?> routeBookV9(BookHandler handler) {
        return route()
                .POST("/v9/books", handler::createBook)
                .PATCH("/v9/books/{book-id}", handler::updateBook)
                .GET("/v9/books", handler::getBooks)
                .GET("/v9/books/{book-id}", handler::getBook)
                .build();
    }
}
