package com.itvillage;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.itvillage.CryptoCurrency.CurrencyUnit;

public class Example4_4 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = Arrays.asList(
                new CryptoCurrency("Bitcoin", CurrencyUnit.BTC),
                new CryptoCurrency("Ethereum", CurrencyUnit.ETH),
                new CryptoCurrency("Polkadot", CurrencyUnit.DOT),
                new CryptoCurrency("Cardano", CurrencyUnit.ADA),
                new CryptoCurrency("Solana", CurrencyUnit.SOL)
        );

        Collections.sort(cryptoCurrencies,
                (cc1, cc2) -> cc1.getUnit().name().compareTo(cc2.getUnit().name()));

        for(CryptoCurrency cryptoCurrency : cryptoCurrencies)
            System.out.println("암호 화폐명: " + cryptoCurrency.getName() +
                    ", 가격: " + cryptoCurrency.getUnit());
    }
}
