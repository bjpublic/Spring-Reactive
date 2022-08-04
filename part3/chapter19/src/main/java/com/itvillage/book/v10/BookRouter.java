package com.itvillage.book.v10;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration("bookRouterV10")
public class BookRouter {
    @Bean
    public RouterFunction<?> routeBookV10(BookHandler handler) {
        return route()
                .POST("/v10/books", handler::createBook)
                .PATCH("/v10/books/{book-id}", handler::updateBook)
                .GET("/v10/books", handler::getBooks)
                .GET("/v10/books/{book-id}", handler::getBook)
                .build();
    }
}
