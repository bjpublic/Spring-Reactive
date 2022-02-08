package com.itvillage;


import reactor.core.publisher.Mono;

/**
 * Mono 기본 개념 예제
 *  - 원본 데이터의 emit 없이 onComplete signal 만 emit 한다.
 */
public class Example5_3 {
    public static void main(String[] args) {
        Mono.empty()
//                .map(data -> {
//                    System.out.println("# call map operator");
//                    return data;
//                })
                .subscribe(
                        none -> System.out.println("# emitted onNext signal"),
                        error -> {},
                        () -> System.out.println("# emitted onComplete signal")
                );
    }
}
