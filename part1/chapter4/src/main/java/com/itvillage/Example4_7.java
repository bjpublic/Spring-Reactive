package com.itvillage;

import java.util.List;

/**
 * ClassName :: instance method 형태의 메서드 레퍼런스 예제
 */
public class Example4_7 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = SampleData.cryptoCurrencies;

        cryptoCurrencies.stream()
                .map(cc -> cc.getName())
//                .map(name -> name.toUpperCase())
                .map(String::toUpperCase)
                .forEach(name -> System.out.println(name));
    }
}
