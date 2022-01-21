package com.itvillage;

import com.itvillage.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 본사 서버에서 들어오는 요청을 처리하는 Spring MVC 기반
 * 지점 API Server
 */
@Slf4j
@RequestMapping(path = "/v1/books", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*")
@RestController
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
        Thread.sleep(200);

        Book book = bookMap.get(bookId);

        return ResponseEntity.ok(book);
    }
}
