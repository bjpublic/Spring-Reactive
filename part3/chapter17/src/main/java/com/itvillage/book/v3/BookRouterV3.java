package com.itvillage.book.v3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouterV3 {
    @Bean
    public RouterFunction<?> routeBookV3(BookHandlerV3 handler) {
        return route()
                .POST("/v3/books", handler::handlePost)
                .PATCH("/v3/books/{book-id}", handler::handlePatch)
                .GET("/v3/books", handler::getBooks)
                .GET("/v3/books/{book-id}", handler::getBook)
                .build();
    }

//    @Bean
//    @Primary
//    public Validator springValidator() {
//        return new LocalValidatorFactoryBean();
//    }
}
