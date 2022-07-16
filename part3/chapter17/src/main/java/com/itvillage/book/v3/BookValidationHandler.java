package com.itvillage.book.v3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Slf4j
public abstract class BookValidationHandler {
    private final Validator validator;

    public BookValidationHandler(Validator validator) {
        this.validator = validator;
    }

    abstract protected Mono<ServerResponse> createBook(Object body,
                                                       final ServerRequest request);
    abstract protected Mono<ServerResponse> updateBook(Object body,
                                                       final ServerRequest request);

    public final Mono<ServerResponse> handlePost(final ServerRequest request) {
        Class<BookDto.Post> target = BookDto.Post.class;
        return request.bodyToMono(target)
                .flatMap(body ->
                        validate(body, target, request,
                                (Object obj, ServerRequest req) -> createBook(obj, req)));
    }

    public final Mono<ServerResponse> handlePatch(final ServerRequest request) {
        Class<BookDto.Patch> target = BookDto.Patch.class;
        return request.bodyToMono(target)
                .flatMap(body ->
                        validate(body, target, request,
                                (Object obj, ServerRequest req) -> updateBook(obj, req)));
    }

    protected Mono<ServerResponse> onValidationErrors(Errors errors) {
        log.error(errors.getAllErrors().toString());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getAllErrors()
                .toString());
    }

    private Mono<ServerResponse> validate(
                 Object body,
                 Class target,
                 ServerRequest request,
                 BiFunction<Object, ServerRequest, Mono<ServerResponse>> function) {
        Errors errors =
                new BeanPropertyBindingResult(body, target.getName());
        this.validator.validate(body, errors);

        if (errors == null || errors.getAllErrors().isEmpty()) {
            return function.apply(body, request);
        } else {
            return onValidationErrors(errors);
        }
    }
}
