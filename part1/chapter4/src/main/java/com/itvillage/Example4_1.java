package com.itvillage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 기존에 사용되던 단 하나의 추상 메서드를 가지는 인터페이스 예제
 */
public class Example4_1 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = SampleData.cryptoCurrencies;

        Collections.sort(cryptoCurrencies, new Comparator<CryptoCurrency>() {
            @Override
            public int compare(CryptoCurrency cc1, CryptoCurrency cc2) {
                return cc1.getUnit().name().compareTo(cc2.getUnit().name());
            }
        });

        for(CryptoCurrency cryptoCurrency : cryptoCurrencies)
            System.out.println("암호 화폐명: " + cryptoCurrency.getName() +
                    ", 가격: " + cryptoCurrency.getUnit());
    }
}
