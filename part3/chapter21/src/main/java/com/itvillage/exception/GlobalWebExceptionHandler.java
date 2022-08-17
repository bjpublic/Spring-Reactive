package com.itvillage.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itvillage.v11.ErrorResponse;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(-2)
@Configuration
public class GlobalWebExceptionHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper objectMapper;

    public GlobalWebExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange,
                             Throwable throwable) {
        return handleException(serverWebExchange, throwable);
    }

    private Mono<Void> handleException(ServerWebExchange serverWebExchange,
                                       Throwable throwable) {
        ErrorResponse errorResponse = null;
        DataBuffer dataBuffer = null;

        DataBufferFactory bufferFactory =
                                serverWebExchange.getResponse().bufferFactory();
        serverWebExchange.getResponse().getHeaders()
                                        .setContentType(MediaType.APPLICATION_JSON);

        if (throwable instanceof BusinessLogicException) {
            BusinessLogicException ex = (BusinessLogicException) throwable;
            ExceptionCode exceptionCode = ex.getExceptionCode();
            errorResponse = ErrorResponse.of(exceptionCode.getStatus(),
                                                exceptionCode.getMessage());
            serverWebExchange.getResponse()
                        .setStatusCode(HttpStatus.valueOf(exceptionCode.getStatus()));
        } else if (throwable instanceof ResponseStatusException) {
            ResponseStatusException ex = (ResponseStatusException) throwable;
            errorResponse = ErrorResponse.of(ex.getStatus().value(), ex.getMessage());
            serverWebExchange.getResponse().setStatusCode(ex.getStatus());
        } else {
            errorResponse = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                            throwable.getMessage());
            serverWebExchange.getResponse()
                                    .setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            dataBuffer =
                    bufferFactory.wrap(objectMapper.writeValueAsBytes(errorResponse));
        } catch (JsonProcessingException e) {
            bufferFactory.wrap("".getBytes());
        }

        return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
    }
}
