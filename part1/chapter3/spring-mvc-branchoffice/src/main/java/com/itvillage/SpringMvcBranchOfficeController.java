package com.itvillage;

import com.itvillage.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 본사 서버에서 들어오는 요청을 처리하는 Spring MVC 기반
 * 지점 API Server
 */
@Slf4j
@RestController
@RequestMapping("/v1/books")
public class SpringMvcBranchOfficeController {
    private Map<Long, Book> bookMap;

    @Autowired
    public SpringMvcBranchOfficeController(Map<Long, Book> bookMap) {
        this.bookMap = bookMap;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public ResponseEntity<Book> getBook(@PathVariable("book-id") long bookId)
            throws InterruptedException {
        Thread.sleep(5000);

        Book book = bookMap.get(bookId);

        return ResponseEntity.ok(book);
    }
}
