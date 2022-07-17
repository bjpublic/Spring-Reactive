package com.itvillage.book.v3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration("bookRouterV3")
public class BookRouter {
    @Bean
    public RouterFunction<?> routeBookV3(BookHandler handler) {
        return route()
                .POST("/v3/books", handler::createBook)
                .PATCH("/v3/books/{book-id}", handler::updateBook)
                .GET("/v3/books", handler::getBooks)
                .GET("/v3/books/{book-id}", handler::getBook)
                .build();
    }

    @Bean
    public Validator springValidator() {
        return new LocalValidatorFactoryBean();
    }
}
