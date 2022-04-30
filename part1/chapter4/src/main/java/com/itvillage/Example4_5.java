package com.itvillage;

import java.util.List;

import static com.itvillage.CryptoCurrency.CurrencyUnit;

/**
 * 람다 캡처링 예제
 */
public class Example4_5 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = SampleData.cryptoCurrencies;

        String korBTC = "비트코인";
//        korBTC = "빗코인";
        cryptoCurrencies.stream()
                .filter(cc -> cc.getUnit() == CurrencyUnit.BTC)
                .map(cc -> cc.getName() + "(" + korBTC + ")" )
                .forEach(cc -> System.out.println(cc));
    }
}
