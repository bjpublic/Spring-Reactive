package com.itvillage;

import reactor.core.publisher.Flux;

public class Example2_5 {
    public static void main(String[] args) {
        Flux
                .just(1, 2, 3, 4, 5, 6)
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .subscribe(System.out::println);
    }
}
