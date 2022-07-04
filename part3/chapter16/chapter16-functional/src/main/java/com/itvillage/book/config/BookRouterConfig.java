package com.itvillage.book.config;

import com.itvillage.book.handler.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouterConfig {
    @Bean
    public RouterFunction<?> routeBook(BookHandler handler) {
        return route(POST("/v1/books"), handler::createBook)
                .andRoute(GET("/v1/books/{book-id}"), handler::getBook)
                .andRoute(PATCH("/v1/books/{book-id}"), handler::patchBook)
                .andRoute(GET("/v1/books"), handler::getBooks);
    }
}