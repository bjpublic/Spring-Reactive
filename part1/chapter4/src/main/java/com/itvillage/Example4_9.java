package com.itvillage;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.Optional;

import static com.itvillage.CryptoCurrency.CurrencyUnit;

/**
 * ClassName :: new 형태의 메서드 레퍼런스 예제
 */
public class Example4_9 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = SampleData.cryptoCurrencies;

        int amount = 2;

        Optional<PaymentCalculator> optional =
                cryptoCurrencies.stream()
                                .filter(cc -> cc.getUnit() == CurrencyUnit.BTC)
                                .map(cc -> new ImmutablePair(cc.getPrice(), amount))
//                                            .map(pair -> new PaymentCalculator(pair))
                                .map(PaymentCalculator::new)
                                .findFirst();

        System.out.println(optional.get().getTotalPayment());
    }
}
