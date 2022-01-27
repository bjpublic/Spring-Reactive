package com.itvillage;

import java.util.List;

import static com.itvillage.CryptoCurrency.CurrencyUnit;

/**
 * object :: instance method 형태의 메서드 레퍼런스 예제
 */
public class Example4_9 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = SampleData.cryptoCurrencies;

        PaymentCalculator calculator = new PaymentCalculator(CurrencyUnit.BTC, 2);
        cryptoCurrencies.stream()
                .filter(cc -> cc.getUnit() == calculator.getUnit())
                .map(cc -> cc.getPrice())
//                .map(price -> calculator.getTotalPayment(price))
                .map(calculator::getTotalPayment)
                .forEach(System.out::println);
    }
}
