package com.itvillage;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;

import static com.itvillage.CryptoCurrency.CurrencyUnit;

/**
 * object :: instance method 형태의 메서드 레퍼런스 예제
 */
public class Example4_8 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = SampleData.cryptoCurrencies;
        int btcPrice = cryptoCurrencies.stream()
                .filter(cc -> cc.getUnit() == CurrencyUnit.BTC)
                .findFirst()
                .get()
                .getPrice();

        int amount = 2;

        PaymentCalculator calculator = new PaymentCalculator();
        cryptoCurrencies.stream()
                .filter(cc -> cc.getUnit() == CurrencyUnit.BTC)
                .map(cc -> new ImmutablePair(cc.getPrice(), amount))
//                .map(pair -> calculator.getTotalPayment(pair))
                .map(calculator::getTotalPayment)
                .forEach(System.out::println);
    }
}
