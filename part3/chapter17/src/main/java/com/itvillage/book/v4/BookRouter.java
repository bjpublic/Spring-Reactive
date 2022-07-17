package com.itvillage.book.v4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.reactive.function.server.RouterFunction;

import javax.validation.Validator;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration("bookRouterV4")
public class BookRouter {
    @Bean
    public RouterFunction<?> routeBookV4(BookHandler handler) {
        return route()
                .POST("/v4/books", handler::createBook)
                .PATCH("/v4/books/{book-id}", handler::updateBook)
                .GET("/v4/books", handler::getBooks)
                .GET("/v4/books/{book-id}", handler::getBook)
                .build();
    }

    @Bean
    public Validator javaxValidator() {
        return new LocalValidatorFactoryBean();
    }
}
