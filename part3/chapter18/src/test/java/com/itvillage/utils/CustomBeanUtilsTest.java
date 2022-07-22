package com.itvillage.utils;

import com.itvillage.book.v5.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CustomBeanUtilsTest {
    @Test
    public void copyNonNullPropertiesTest() {
        Book src = new Book();
        src.setBookId(1L);
        src.setTitleKorean("Java 고급");
        src.setTitleEnglish("Advanced Java");

        Book dest = new Book(1L,
                "Java 중급",
                "Intermediate Java",
                "Kevin",
                "111-11-1111-111-1",
                "Java 중급 프로그래밍 마스터",
                "2022-03-22",
                LocalDateTime.now(),
                LocalDateTime.now());

        CustomBeanUtils<Book> beanUtils = new CustomBeanUtils<>();
        Book result = beanUtils.copyNonNullProperties(src, dest);

        assertThat(result.getTitleKorean(), is(src.getTitleKorean()));
        assertThat(result.getTitleEnglish(), is(src.getTitleEnglish()));
    }
}
