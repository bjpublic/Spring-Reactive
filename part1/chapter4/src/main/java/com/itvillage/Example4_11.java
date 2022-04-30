package com.itvillage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate 사용 예제
 */
public class Example4_11 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = SampleData.cryptoCurrencies;
        List<CryptoCurrency> result = filter(cryptoCurrencies, cc -> cc.getPrice() > 500_000);

        for (CryptoCurrency cc : result) {
            System.out.println(cc.getName());
        }
    }

    private static List<CryptoCurrency> filter(List<CryptoCurrency> cryptoCurrencies,
                                               Predicate<CryptoCurrency> p){
        List<CryptoCurrency> result = new ArrayList<>();
        for (CryptoCurrency cc : cryptoCurrencies) {
            if (p.test(cc)) {
                result.add(cc);
            }
        }
        return result;
    }
}
