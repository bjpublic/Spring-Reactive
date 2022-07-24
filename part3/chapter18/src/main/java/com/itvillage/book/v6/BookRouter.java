package com.itvillage.book.v6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration("bookRouterV6")
public class BookRouter {
    @Bean
    public RouterFunction<?> routeBookV6(BookHandler handler) {
        return route()
                .POST("/v6/books", handler::createBook)
                .PATCH("/v6/books/{book-id}", handler::updateBook)
                .GET("/v6/books", handler::getBooks)
                .GET("/v6/books/{book-id}", handler::getBook)
                .build();
    }
}
