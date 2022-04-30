package com.itvillage;

import java.util.Collections;
import java.util.List;

/**
 * Comparator 함수형 인터페이스의 람다 표현 예제
 */
public class Example4_4 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = SampleData.cryptoCurrencies;

        Collections.sort(cryptoCurrencies,
                (cc1, cc2) -> cc1.getUnit().name().compareTo(cc2.getUnit().name()));

        for(CryptoCurrency cryptoCurrency : cryptoCurrencies)
            System.out.println("암호 화폐명: " + cryptoCurrency.getName() +
                    ", 가격: " + cryptoCurrency.getUnit());
    }
}
