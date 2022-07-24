package com.itvillage.book.v6;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository("bookRepositoryV6")
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
    Mono<Book> findByIsbn(String isbn);
}
