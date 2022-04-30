package com.itvillage;

import java.util.Arrays;
import java.util.List;

public class SampleData {
    public final static List<CryptoCurrency> cryptoCurrencies = Arrays.asList(
            new CryptoCurrency("Bitcoin",
                    CryptoCurrency.CurrencyUnit.BTC, 40_000_000),
            new CryptoCurrency("Ethereum",
                    CryptoCurrency.CurrencyUnit.ETH, 2_000_000),
            new CryptoCurrency("Polkadot",
                    CryptoCurrency.CurrencyUnit.DOT, 10_000),
            new CryptoCurrency("Cardano",
                    CryptoCurrency.CurrencyUnit.ADA, 1500),
            new CryptoCurrency("Solana",
                    CryptoCurrency.CurrencyUnit.SOL, 150_000)
    );
}
