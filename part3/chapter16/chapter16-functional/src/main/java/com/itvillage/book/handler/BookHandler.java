package com.itvillage.book.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BookHandler {
    public Mono<ServerResponse> createBook(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> getBook(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> patchBook(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> getBooks(ServerRequest request) {
        return null;
    }
}
