package com.itvillage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Function 사용 예제
 */
public class Example4_15 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = SampleData.cryptoCurrencies;
        List<CryptoCurrency> filtered =
                filter(cryptoCurrencies,
                        cc -> cc.getUnit() == CryptoCurrency.CurrencyUnit.BTC ||
                        cc.getUnit() == CryptoCurrency.CurrencyUnit.ETH);

        int totalPayment = calculatePayment(filtered, cc -> cc.getPrice() * 2);

        System.out.println("# 구매 비용: " + totalPayment);
    }

    private static List<CryptoCurrency> filter(List<CryptoCurrency> cryptoCurrencies,
                                               Predicate<CryptoCurrency> p) {
        List<CryptoCurrency> result = new ArrayList<>();
        for (CryptoCurrency cc : cryptoCurrencies) {
            if (p.test(cc)) {
                result.add(cc);
            }
        }
        return result;
    }

    private static int calculatePayment(List<CryptoCurrency> cryptoCurrencies,
                                        Function<CryptoCurrency, Integer> f) {
        int totalPayment = 0;
        for (CryptoCurrency cc : cryptoCurrencies) {
            totalPayment += f.apply(cc);
        }
        Supplier s = () -> "";
        return totalPayment;
    }
}
