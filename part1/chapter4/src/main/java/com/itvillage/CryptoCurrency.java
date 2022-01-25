package com.itvillage;

public class CryptoCurrency {
    private String name;
    private CurrencyUnit unit;

    public CryptoCurrency(String name, CurrencyUnit unit) {
        this.name = name;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public CurrencyUnit getUnit() {
        return unit;
    }

    public enum CurrencyUnit {
        BTC,
        ETH,
        DOT,
        ADA,
        SOL
    }
}
