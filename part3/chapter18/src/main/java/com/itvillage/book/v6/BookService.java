package com.itvillage.book.v6;

import com.itvillage.utils.CustomBeanUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service("bookServiceV6")
@RequiredArgsConstructor
public class BookService {
    private final @NonNull CustomBeanUtils<Book> beanUtils;

    public Mono<Book> saveBook(Book book) {
        // TODO
        return null;
    }

    public Mono<Book> updateBook(Book book) {
        // TODO
        return null;
    }

    public Mono<Book> findBook(long bookId) {
        return findVerifiedBook(bookId);
    }

    public Mono<List<Book>> findBooks() {
        // TODO
        return null;
    }

    private Mono<Void> verifyExistIsbn(String isbn) {
        // TODO
        return null;
    }

    private Mono<Book> findVerifiedBook(long bookId) {
        // TODO
        return null;
    }
}
