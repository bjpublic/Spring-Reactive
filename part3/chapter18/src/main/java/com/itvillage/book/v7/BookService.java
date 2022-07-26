package com.itvillage.book.v7;

import com.itvillage.book.exception.BusinessLogicException;
import com.itvillage.book.exception.ExceptionCode;
import com.itvillage.utils.CustomBeanUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 페이지네이션 적용
 */
@Slf4j
@Service("bookServiceV7")
@RequiredArgsConstructor
public class BookService {
    private final @NonNull BookRepository bookRepository;
    private final @NonNull CustomBeanUtils<Book> beanUtils;

    public Mono<Book> saveBook(Book book) {
        return verifyExistIsbn(book.getIsbn())
                .then(bookRepository.save(book));
    }

    public Mono<Book> updateBook(Book book) {
        return findVerifiedBook(book.getBookId())
                .map(findBook -> beanUtils.copyNonNullProperties(book, findBook))
                .flatMap(updatingBook -> bookRepository.save(updatingBook));
    }

    public Mono<Book> findBook(long bookId) {
        return findVerifiedBook(bookId);
    }

    public Mono<List<Book>> findBooks() {
        // TODO
        return bookRepository.findAll().collectList();
    }

    private Mono<Void> verifyExistIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .flatMap(findBook -> {
                    if (findBook != null) {
                        return Mono.error(new BusinessLogicException(
                                                    ExceptionCode.BOOK_EXISTS));
                    }
                    return Mono.empty();
                });
    }

    private Mono<Book> findVerifiedBook(long bookId) {
        return bookRepository
                .findById(bookId)
                .switchIfEmpty(Mono.error(new BusinessLogicException(
                                                    ExceptionCode.BOOK_NOT_FOUND)));
    }
}
