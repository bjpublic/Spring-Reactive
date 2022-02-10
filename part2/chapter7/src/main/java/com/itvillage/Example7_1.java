package com.itvillage;

import reactor.core.publisher.Flux;

import java.util.Arrays;

public class Example7_1 {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> coldFlux =
                Flux
                    .fromIterable(Arrays.asList("KOREA", "JAPAN", "CHINESE"))
                    .map(String::toLowerCase);

        coldFlux.subscribe(country -> System.out.println("# Subscriber1: " + country));
        System.out.println("-------------------------");
        Thread.sleep(2000L);
        coldFlux.subscribe(country -> System.out.println("# Subscriber2: " + country));
    }
}
