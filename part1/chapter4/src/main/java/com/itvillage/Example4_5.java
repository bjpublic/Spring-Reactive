package com.itvillage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.itvillage.CryptoCurrency.CurrencyUnit;

public class Example4_5 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = Arrays.asList(
                new CryptoCurrency("Bitcoin", CurrencyUnit.BTC),
                new CryptoCurrency("Ethereum", CurrencyUnit.ETH),
                new CryptoCurrency("Polkadot", CurrencyUnit.DOT),
                new CryptoCurrency("Cardano", CurrencyUnit.ADA),
                new CryptoCurrency("Solana", CurrencyUnit.SOL)
        );

        String korBTC = "비트코인";
//        korBTC = "빗코인";
        cryptoCurrencies.stream()
                .filter(cc -> cc.getUnit() == CurrencyUnit.BTC)
                .map(cc -> cc.getName() + "(" + korBTC + ")" )
                .forEach(cc -> System.out.println(cc));
    }
}
