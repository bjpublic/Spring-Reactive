package com.itvillage;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.itvillage.CryptoCurrency.CurrencyUnit;

public class Example4_8 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = Arrays.asList(
                new CryptoCurrency("Bitcoin", CurrencyUnit.BTC, 40_000_000, 2),
                new CryptoCurrency("Ethereum", CurrencyUnit.ETH, 2_000_000, 1),
                new CryptoCurrency("Polkadot", CurrencyUnit.DOT, 10_000, 5),
                new CryptoCurrency("Cardano", CurrencyUnit.ADA, 1500, 3),
                new CryptoCurrency("Solana", CurrencyUnit.SOL, 150_000, 2)
        );

        PaymentCalculator calculator = new PaymentCalculator(CurrencyUnit.BTC, 2);
        cryptoCurrencies.stream()
                .filter(cc -> cc.getUnit() == calculator.getUnit())
                .map(cc -> cc.getPrice())
//                .map(price -> calculator.getTotalPayment(price))
                .map(calculator::getTotalPayment)
                .forEach(System.out::println);
    }
}
