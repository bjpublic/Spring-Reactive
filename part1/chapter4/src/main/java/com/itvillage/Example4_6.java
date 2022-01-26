package com.itvillage;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

import static com.itvillage.CryptoCurrency.CurrencyUnit;

public class Example4_6 {
    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = Arrays.asList(
                new CryptoCurrency("Bitcoin", CurrencyUnit.BTC),
                new CryptoCurrency("Ethereum", CurrencyUnit.ETH),
                new CryptoCurrency("Polkadot", CurrencyUnit.DOT),
                new CryptoCurrency("Cardano", CurrencyUnit.ADA),
                new CryptoCurrency("Solana", CurrencyUnit.SOL)
        );

        cryptoCurrencies.stream()
                .map(cc -> cc.getName())
//                .map(name -> StringUtils.upperCase(name))
                .map(StringUtils::upperCase)
                .forEach(name -> System.out.println(name));
    }
}
